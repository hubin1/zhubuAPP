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
 * 用于进行 视频详细信息的测试
 * 
 * @author louis
 *
 */
@Controller
public class RedirectVideoDetailController {
	private int buffer = 409600;
	private Logger logger = Logger.getLogger(RedirectVideoDetailController.class);

	/**
	 * 重新定向到指定的文件内容
	 * 
	 * @param vname
	 *            不包含后缀 的文件名
	 * @param vpos
	 *            视频的文件位置
	 * @param vsuffix
	 *            视频的后缀类型 只能是 0，1，2, 3 四种
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/getvideo", method = RequestMethod.POST)
	public void redirectVideo(@RequestParam(value = "vname", required = true) String vname,
			@RequestParam(value = "vpos", required = true) String vpos,
			@RequestParam(value = "vsuffix", required = true) int vsuffix, HttpServletResponse response,
			HttpSession session) {
		logger.debug("=========================");

		// 获取 并组装地址
		// String basepath =
		// session.getServletContext().getInitParameter("video_position_actual");

		StringBuilder build = new StringBuilder(DefaultVariables.video_position_actual);
		build.append(File.separator);
		build.append(vpos);
		build.append(File.separator);
		build.append(vname);
		build.append(DefaultVariables.SUFFIX[vsuffix]);

		logger.debug("get path is: " + build.toString());

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

	/**
	 * 将视频转换成流 输出到 目标
	 * 
	 * @param file
	 *            目标文件
	 * @param output
	 *            输出流
	 */
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
