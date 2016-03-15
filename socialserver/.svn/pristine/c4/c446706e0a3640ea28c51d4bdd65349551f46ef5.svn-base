package com.ss.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.util.DefaultVariables;

/**
 * 用于获取 头像图片的控制器
 * 
 * @author louis
 *
 */
@Controller
public class RedirectIconDetailController {

	private Logger logger = Logger.getLogger(RedirectIconDetailController.class);

	@RequestMapping(value = "/geticon", method = RequestMethod.POST)
	public void redirectIcon(@RequestParam(value = "iname", required = true) String iname,
			@RequestParam(value = "ipos", required = true) String ipos, HttpServletResponse response) {
		logger.debug("=========================");

		// 获取 并组装地址

		StringBuilder build = new StringBuilder(DefaultVariables.icon_position_actual);
		build.append(File.separator);
		build.append(ipos);
		build.append(File.separator);
		build.append(iname);

		logger.debug(Thread.currentThread().getName() + " 线程的请求头像位置为：" + build.toString());
		
		File file = new File(build.toString());
		if (file.exists() == false) {
			try {
				response.getWriter().println("null");
			} catch (IOException e) {
				logger.error(e);
			}
		} else {
			// 向客户端输出流
			try {
				output(file, response.getOutputStream());
			} catch (IOException e) {
				logger.error(e);
			}
		}
		logger.debug("流输出完成");
		logger.debug("=========================");
	}

	private void output(File file, OutputStream output) {
		FileInputStream input = null;

		BufferedInputStream inbuffer = null;
		BufferedOutputStream outbuffer = null;
		try {
			input = new FileInputStream(file);

			inbuffer = new BufferedInputStream(input, 409600);
			outbuffer = new BufferedOutputStream(output, 409600);

			int count = 0;
			byte[] buf = new byte[10240];

			while ((count = inbuffer.read(buf)) != -1) {
				// 写内容
				outbuffer.write(buf, 0, count);
			}
		} catch (FileNotFoundException e) {
			logger.error(e);
			logger.debug("-----------------------------");
		} catch (IOException e) {
			logger.error(e);
			logger.debug("-----------------------------");
		} finally {
			// 处理异常
			try {
				if (null != inbuffer) {
					inbuffer.close();
				}
				if (null != outbuffer) {
					outbuffer.close();
				}
				if (null != output) {
					output.close();
				}
				if (null != input) {
					input.close();
				}

			} catch (IOException e) {
				logger.error(e);
				logger.debug("-----------------------------");
			}

		}
		logger.debug("流输出完毕");
	}
}
