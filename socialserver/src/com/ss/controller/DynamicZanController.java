package com.ss.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.service.ImageCommentService;
import com.ss.service.VideoCommentService;
import com.ss.util.DefaultVariables;

/**
 * 动态的点赞控制器
 * 
 * @author louis
 *
 */
@Controller
public class DynamicZanController {

	private Logger logger = Logger.getLogger(DynamicZanController.class);

	@Autowired
	public VideoCommentService vc_service;
	@Autowired
	public ImageCommentService ic_service;

	/**
	 * 点赞  
	 * 
	 * 
	 * 
	 * 
	 */
	@RequestMapping(value = "/zan", method = RequestMethod.POST)
	public void add(@RequestParam(value = "zid", required = true) int zid,
			@RequestParam(value = "type", required = true) int type) {
		logger.debug("===================");
		logger.debug("点赞请求收到：类型" + type);

		if (type == DefaultVariables.VIDEO) {
			
			this.vc_service.update4zan_add(zid);
		} else {
			this.ic_service.update4zan_add(zid);
		}
	}

	/**
	 * 取消点赞
	 */
	@RequestMapping(value = "/unzan", method = RequestMethod.POST)
	public void remove(@RequestParam(value = "zid", required = true) int zid,
			@RequestParam(value = "type", required = true) int type) {
		logger.debug("===================");
		logger.debug("取消赞请求收到：类型" + type);

		if (type == DefaultVariables.VIDEO) {
			this.vc_service.update4zan_remove(zid);
		} else {
			this.ic_service.update4zan_remove(zid);
		}
	}
}
