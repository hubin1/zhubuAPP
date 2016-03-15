package com.ss.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.util.DefaultVariables;

/**
 * 处理 获取 video 的 image 请求
 * 
 * @author louis
 *
 */
@Controller
public class RedirectVideoImageDetailController {

	private int buffer = 102400;

	private Logger logger = Logger.getLogger(RedirectVideoImageDetailController.class);

	@RequestMapping(value = "/getvideoimage", method = RequestMethod.POST)
	public void redirectVideoImage(@RequestParam(value = "ipos", required = true) String ipos,
			@RequestParam(value = "iname", required = true) String iname,
			@RequestParam(value = "fromother", required = false) int isurl, HttpServletResponse response,
			HttpSession session) {
		
		
		logger.debug("-------------");
		
		logger.debug(iname+" : "+isurl);
		
		logger.debug("-------------");
		
		
		/**
		 * 组装文件路径
		 */
		StringBuilder builder = null;
		// 0 表示 使用 原来的图片动态所使用的常用位置
		// 1 表示 使用 视频截图所用的位置
		if(0 != isurl){
			builder = new StringBuilder(DefaultVariables.video_img_position);
		}else{
			builder = new StringBuilder(DefaultVariables.image_position_actual);
		}
		
		
		
		
		builder.append(File.separator);
		builder.append(ipos);
		builder.append(File.separator);
		builder.append(iname);

		File target = new File(builder.toString());
		logger.debug("path is: " + builder.toString());
		if (!target.exists()) {
			logger.error("No file be found at there!");
			try {
				response.getWriter().println("null");
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		} else {
			try {
				output(target, response.getOutputStream());
			} catch (IOException e) {
				logger.error(e);
			}
		}
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
