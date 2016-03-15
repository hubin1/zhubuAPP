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
import com.ss.service.ImageCommentService;

@Controller
public class GetImageWithConditionsController {

	private Logger logger = Logger.getLogger(GetImageWithConditionsController.class);
	
	@Autowired
	private ImageCommentService service;
	
	@RequestMapping(value = "/imageinfoall", method = RequestMethod.POST)
	@ResponseBody
	public Status getAllImages(@RequestParam(value = "pageNum", required = false) int pagenum){
		Map<String, Object> pageinfo = this.service.getAll(0, 0, pagenum);
		Status status = new Status("status", "0", pageinfo);
		return status;
	}
	
}
