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

@Controller
public class ImageVideoCommentController {

	private Logger logger = Logger.getLogger(ImageVideoCommentController.class);

	@Autowired
	private VideoCommentService videoService;
	
	
	@RequestMapping(value="/getImageAndVideoByzan",method=RequestMethod.GET)
	@ResponseBody
	public Status getAllImageAndVideo(@RequestParam(value = "pageNum", required = false) int pageNum){
		Map<String, Object> pageinfo = videoService.getVideoContainsImage(0, pageNum, DefaultVariables.PAGE_SIZE);
		Status status = new Status("status", "0", pageinfo);
		return status;
		
	}
	
}
