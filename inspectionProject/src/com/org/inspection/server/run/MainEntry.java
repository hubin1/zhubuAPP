package com.org.inspection.server.run;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.codehaus.jackson.map.ObjectMapper;

import com.org.inspection.http_inferface.Service;
import com.org.inspection.server.info.Status;


/**
 * 程序入口. <br>
 * 程序入口.
 * <p>
 * Copyright: Copyright (c) 2009-9-9 下午01:21:08
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: gaowei@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class MainEntry {

	/** Logger. */
	private static Logger logger = Logger.getLogger(MainEntry.class);

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {
		DOMConfigurator.configure("config/log4j.xml");
		try {

			Service.startService();

		}
		catch (Exception e) {
			logger.fatal("启动异常", e);
			System.exit(1);
		}
	}

}
