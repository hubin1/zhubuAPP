package com.ss.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ss.pojo.Status;
import com.ss.service.FollowImageCommentService;
import com.ss.service.FollowVideoCommentService;

/**
 * 用于获取视频或者图像的 回复信息
 * 
 * @author louis
 *
 */
@Controller
public class GetFollowCommentController {

	private Logger logger = Logger.getLogger(GetFollowCommentController.class);

	@Resource
	private FollowImageCommentService icservice;

	@Resource
	private FollowVideoCommentService vcservice;

	@RequestMapping(value = "/getimagecomment", method = RequestMethod.POST)
	@ResponseBody
	public Status getImageComment(@RequestParam(value = "icid", required = true) int icid,
			@RequestParam(value = "pageNum") int page) {
		logger.debug("======================");
		logger.debug("icid=" + icid + ";  page=" + page);

		Map<String, Object> result = this.icservice.getImageCommentWithPage(icid, page);
		logger.debug("result total:"+result.get("total"));
		logger.debug("======================");
		return new Status("status", "0", result);
	}

	@RequestMapping(value = "/getvideocomment", method = RequestMethod.POST)
	@ResponseBody
	public Status getVideoComment(@RequestParam(value = "vid", required = true) int vid,
			@RequestParam(value = "pageNum") int page) {
		logger.debug("======================");
		logger.debug("icid=" + vid + ";  page=" + page);

		Map<String, Object> result = this.vcservice.getVideoCommentWithPage(vid, page);
		logger.debug("result total:"+result.get("total"));
		logger.debug("======================");
		return new Status("status", "0", result);

	}
}
