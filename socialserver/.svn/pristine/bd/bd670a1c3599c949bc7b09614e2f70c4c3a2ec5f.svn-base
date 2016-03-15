package com.ss.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.ss.util.DefaultVariables;

public class PreparingParametersListener implements ServletContextListener {

	Logger logger = Logger.getLogger(PreparingParametersListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// nothing now
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("=========开始读取位置参数=========");
		ServletContext context = arg0.getServletContext();
		// 准备读取位置
		DefaultVariables.icon_position_actual = context.getInitParameter("icon_position_actual");
		logger.info("read:" + DefaultVariables.icon_position_actual);

		DefaultVariables.image_position_actual = context.getInitParameter("image_position_actual");
		logger.info("read:" + DefaultVariables.image_position_actual);

		DefaultVariables.video_img_position = context.getInitParameter("video_img_position");
		logger.info("read:" + DefaultVariables.video_img_position);

		DefaultVariables.video_position_actual = context.getInitParameter("video_position_actual");
		logger.info("read:" + DefaultVariables.video_position_actual);

		logger.info("=========读取位置参数成功=========");
		logger.info("=========开始读取需要请求的参数=========");

		InputStream input = this.getClass().getResourceAsStream("/befilterrequest.txt");

		InputStreamReader reader = new InputStreamReader(input);

		BufferedReader buffer = new BufferedReader(reader);

		String content = "";
		try {
			while ((content = buffer.readLine()) != null) {
				logger.debug(content);
				DefaultVariables.SETS.add(content);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != buffer) {
					buffer.close();
				}
				if (null != reader) {
					reader.close();
				}
				if (null != input) {
					input.close();
				}
			} catch (Exception ex) {
				logger.error(ex);
			}
		}

		logger.info("=========需要过滤的请求参数读取成功=========");

		logger.info("=========创建通用文件的文件夹=========");

		String pos = context.getInitParameter("other_files_folder");

		File f = new File(pos);
		try {
			f.mkdirs();
			DefaultVariables.other_files_folder = pos;
		} catch (Exception e) {
			logger.error(e);
		}
		
		
		
		logger.info("=========创建通用文件的文件夹成功=========");

	}

}
