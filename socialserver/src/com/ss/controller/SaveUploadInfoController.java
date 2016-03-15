package com.ss.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ss.pojo.Baseinfo;
import com.ss.pojo.ImageComment;
import com.ss.pojo.Status;
import com.ss.pojo.VideoComment;
import com.ss.service.ImageCommentService;
import com.ss.service.VideoCommentService;
import com.ss.util.DefaultVariables;

/**
 * 
 * 视频和图片的动态信息保存 的控制器
 * 
 * @author louis
 * 
 *         1 token 失效
 * 
 */
@Controller
public class SaveUploadInfoController {

	private Logger logger = Logger.getLogger(SaveUploadInfoController.class);

	@Autowired
	private VideoCommentService vservice;

	@Autowired
	private ImageCommentService iservice;

	@RequestMapping(value = "/savevideo")
	@ResponseBody
	public Status saveVideo(@ModelAttribute VideoComment comm, HttpSession session) {
		Baseinfo info = (Baseinfo) session.getAttribute(DefaultVariables.USERINFO);
//		if (null == info) {
//			// 1 表示 token 失效
//			return new Status("status", "1", null);
//		}
		Date uploadtime = new Date();
		// 补全必要的信息
		comm.setUploadtime(uploadtime);
		comm.setBuid(info.getBuid());
		comm.setIspass(0);
		
		int result = this.vservice.save(comm);

		logger.debug("result:" + result);
		logger.debug("--------------");

		if (result == 0) {
			// 2 表示存储并未成功
			return new Status("status", "2", null);
		}

		return new Status("status", "0", null);
	}

	@RequestMapping(value = "/saveimage")
	@ResponseBody
	public Status saveImage(@ModelAttribute ImageComment comm, HttpSession session) {
		Baseinfo info = (Baseinfo) session.getAttribute(DefaultVariables.USERINFO);
		
//		logger.debug("token into :"+session.getId());
//		logger.debug("ori info : "+info);
//		if (null == info) {	
//			return new Status("status", "1", null);
//		}

		comm.setBuid(info.getBuid());
		
		int result = this.iservice.save(comm);
		logger.debug("image save result : " + result);
		logger.debug("---------------------------");

		if (result == 0) {
			// 2 表示存储并未成功
			return new Status("status", "2", null);
		}
		return new Status("status", "0", null);

	}

}
