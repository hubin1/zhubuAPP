package com.ss.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ss.pojo.Baseinfo;
import com.ss.pojo.FollowImageComment;
import com.ss.pojo.FollowVideoComment;
import com.ss.pojo.Status;
import com.ss.service.FollowImageCommentService;
import com.ss.service.FollowVideoCommentService;
import com.ss.util.DefaultVariables;

/**
 * 用于上载评论的控制器
 * 
 * @author louis
 *
 *         1 表示 token 失效
 */
@Controller
public class UploadFollowCommentController {
	private Logger logger = Logger.getLogger(UploadFollowCommentController.class);

	@Resource
	private FollowImageCommentService icservice;

	@Resource
	private FollowVideoCommentService vcservice;

	@RequestMapping(value = "/uploadimagecomment", method = RequestMethod.POST)
	@ResponseBody
	public Status uploadImageComment(@ModelAttribute FollowImageComment follow, HttpSession session) {
		Baseinfo base = (Baseinfo)session.getAttribute(DefaultVariables.USERINFO);
		// token 失效
//		if(null == base){
//			return new Status("status", "1");
//		}
		
		follow.setUploadtime(new Date());
		follow.setBuid(base.getBuid());
		
		int result = this.icservice.save(follow);
		
		if(0 == result){
			return new Status("status", "2");
		}
		return new Status("status", "0");
	}

	@RequestMapping(value = "/uploadvideocomment", method = RequestMethod.POST)
	@ResponseBody
	public Status uploadVideoComment(@ModelAttribute FollowVideoComment follow, HttpSession session) {
		Baseinfo base = (Baseinfo)session.getAttribute(DefaultVariables.USERINFO);
		// token 失效
//		if(null == base){
//			return new Status("status", "1");
//		}
		
		follow.setUploadtime(new Date());
		follow.setBuid(base.getBuid());
		
		int result = this.vcservice.save(follow);
		
		if(0 == result){
			return new Status("status", "2");
		}
		return new Status("status", "0");

	}
}
