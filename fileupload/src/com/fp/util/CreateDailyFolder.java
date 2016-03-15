package com.fp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public abstract class CreateDailyFolder {

	private static Object locker = new Object();
	private static Logger logger = Logger.getLogger(CreateDailyFolder.class);

	/**
	 * 按照当前的日期创建文件夹
	 * 
	 * @return 文件夹的名称（包含分隔符 例如 /vvghhjj） type 1 图片 2 视频
	 * @throws IOException
	 */
	public static String createCurrentDayFolder(String basePath, int type) throws IOException {

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		String hex = Integer.toHexString((int) cal.getTimeInMillis());
		int hash = new Long(cal.getTimeInMillis()).hashCode();
		// hex = File.separator + hex + hash;
		hex += hash;
		if (type == 1) {
			hex += "Image".hashCode();
		} else if (type == 2) {
			hex += "Video".hashCode();
		} else if (type == 3) {
			hex += "VideoImage".hashCode();
		} else if (type == 4) {
			hex += "Icon".hashCode();
		}

		File file;
		synchronized (locker) {
			file = new File(basePath + File.separator + hex);
			file.mkdirs();
		}
		return hex;
	}

	/**
	 * 用于进行 视频的截取 默认截取 第5 帧数 格式为 jpg
	 * 
	 * @param target
	 */
	public static String[] createImage(String path, String name, String target_folder) {
		logger.debug("----------------------------");

		String folder;
		try {
			folder = createCurrentDayFolder(target_folder, 3);

			logger.debug("创建目录：" + folder);
		} catch (IOException e) {
			folder = "/lost";
			logger.debug("创建指定目录失败，建立 lost 目录");
		}
		StringBuilder sb = new StringBuilder(Variables.ffmepgpath);
		sb.append(" -i ");
		sb.append(path + name);
		sb.append(" -f image2");
		sb.append(" -ss " + Variables.time);
		sb.append(" -vframes 1 ");

		String fileName = name.substring(0, name.lastIndexOf(".")) + Variables.suffix;
		String tar = target_folder + folder + File.separator + fileName;
		// sb.append(target_folder);
		// sb.append(folder);
		// sb.append(File.separator + fileName);
		sb.append(tar);
		String cmd = sb.toString();

		logger.debug("ffmpeg 语句拼装完成");
		logger.debug(cmd);
		logger.debug("----------------------------");
		// 使用指令调用
		if (!captureFrame(cmd, tar)) {
			// 暂未考虑截图失败后 源文件的问题
			return null;
		}
		logger.debug("截图成功!");
		String[] imp = new String[2];
		imp[0] = folder;
		imp[1] = fileName;
		logger.debug("----------------------------");
		return imp;
	}

	public static synchronized boolean captureFrame(String cmd, String position) {
		BufferedReader reader = null;

		try {
			Process p = Runtime.getRuntime().exec(cmd);
			reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while (reader.readLine() != null) {
			}
		} catch (IOException e) {
			logger.debug("执行 ffmpeg 程序出错");
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.debug("执行后关闭读写流出错");
				}
			}
		}

		/*
		 * 等待 ffmpeg 工作完毕之后 ，检查生成的文件
		 */
		File f = new File(position);
		if (f.exists()) {
			logger.debug("ffmpeg 已经生成文件");
			return true;
		} else {
			logger.debug("ffmpeg 未能生成文件");
			return false;
		}

	}

}
