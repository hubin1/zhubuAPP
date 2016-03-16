package com.org.inspection.log;

import java.io.IOException;

import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

import com.haocom.util.TimeStamp;


/**
 * @Title: 支持含时间通配符文件名的日志记录器
 * @Description: 支持含时间通配符文件名的日志记录器。<br>
 *               %yyyy:4位的年<br>
 *               %yy:2位的年<br>
 *               %mm:2位的月<br>
 *               %dd:2位的日<br>
 *               %hh:2位的小时<br>
 * @Copyright: Copyright (c) 2007-2-25
 * @Company: 
 * @Author: ChengFan
 * @Version: 1.0
 */
public class DatedFileAppender extends RollingFileAppender {

	/** 下一次检测文件的时间(毫秒) */
	private long nextCheck = 0;

	/** 初始的文件名 */
	private String originFilename = null;

	/**
	 * 构造器
	 */
	public DatedFileAppender() {
		// 如够设置为只有一个文件，则修改为200
		if (maxBackupIndex == 1) {
			maxBackupIndex = 200;
		}
	}

	/**
	 * 修正文件名（按照年月日来保存）
	 * 
	 * @return 修正后的文件名
	 */
	private String getDatedFilename() {
		String str = originFilename;
		String time = TimeStamp.getTime(TimeStamp.YYYYMMDDhh);
		str = str.replaceAll("%yyyy", time.substring(0, 4)); // 年
		str = str.replaceAll("%yy", time.substring(0, 4)); // 年(2位)
		str = str.replaceAll("%mm", time.substring(4, 6)); // 月
		str = str.replaceAll("%dd", time.substring(6, 8)); // 日
		str = str.replaceAll("%hh", time.substring(8, 10)); // 小时
		return str;
	}

	@Override
	public void setFile(String file) {

		originFilename = file;
		// System.out.println(originFilename);
		super.setFile(getDatedFilename());
	}

	@Override
	protected void subAppend(LoggingEvent event) {
		// 每30秒检查一次文件名
		long n = System.currentTimeMillis();
		if (n >= nextCheck) {
			nextCheck = n + 1000 * 30;
			try {
				switchFilename();
			}
			catch (IOException ioe) {
				LogLog.error("switchFilename() failed.", ioe);
			}
		}
		super.subAppend(event);
	}

	/**
	 * 切换文件
	 * 
	 * @throws IOException
	 */
	private void switchFilename() throws IOException {
		String datedFilename = getDatedFilename();
		if (!datedFilename.equals(fileName)) {
			try {
				setFile(datedFilename, getAppend(), getBufferedIO(), getBufferSize());
			}
			catch (IOException ex) {
				errorHandler.error("setFile(" + datedFilename + ", false) call failed.");
			}
			fileName = datedFilename;
		}
	}
}
