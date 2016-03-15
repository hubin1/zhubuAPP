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

/**
 * 获取其余的信息
 * 
 * @author louis
 *
 */
@Controller
public class RedirectOtherFiles {
	
	private int buffer = 102400;
	private Logger logger = Logger.getLogger(RedirectImageDetailController.class);
	
	
	@RequestMapping(value = "/getofiles", method = RequestMethod.POST)
	public void getOtherFiles(@RequestParam(value = "oname", required = true) String oname,
			HttpServletResponse response) {
		StringBuilder builder = new StringBuilder(oname);
		builder.append(File.separator);
		builder.append(oname);
		
		File file = new File(builder.toString());
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

			inbuffer = new BufferedInputStream(input, this.buffer);
			outbuffer = new BufferedOutputStream(output, this.buffer);

			int count = 0;
			byte[] buf = new byte[10240];

			while ((count = inbuffer.read(buf)) != -1) {
				// 写内容
				outbuffer.write(buf, 0, count);
			}
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
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
			}

		}
		logger.debug("流输出完毕");
	}
}
