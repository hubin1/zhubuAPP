package com.ss.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ss.pojo.Status;
import com.ss.service.VideoCommentService;
import com.ss.util.DefaultVariables;

/**
 * 用于获得所有视频信息的内容，包含条件
 * 
 * @author louis
 *
 */
@Controller
public class GetVideoWithConditionsController {

	private Logger logger = Logger.getLogger(GetVideoWithConditionsController.class);

	@Autowired
	private VideoCommentService service;

	/**
	 * 内容中 将未审核通过的内容最后放映
	 * 
	 * @return
	 */
	@RequestMapping(value = "/videoinfoall", method = RequestMethod.POST)
	@ResponseBody
	public Status getAllVideo(@RequestParam(value = "type", required = false) int type,
			@RequestParam(value = "pageNum", required = false) int pageNum) {
		Map<String, Object> pageinfo = this.service.getAll(0, type, pageNum, DefaultVariables.PAGE_SIZE);
		Status status = new Status("status", "0", pageinfo);
		return status;
	}

}
