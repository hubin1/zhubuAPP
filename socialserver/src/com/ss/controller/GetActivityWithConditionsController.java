package com.ss.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ss.pojo.Status;
import com.ss.service.ImageCommentService;

/**
 * 获取所有活动的控制器
 * 
 * @author louis
 *
 */
@Controller
public class GetActivityWithConditionsController {

	@Autowired
	private ImageCommentService service;

	@RequestMapping(value = "/activeinfoall", method = RequestMethod.POST)
	@ResponseBody
	public Status getAllActive(@RequestParam(value = "typeA", required = false) int typeA,
			@RequestParam(value = "typeB", required = false) int typeB,
			@RequestParam(value = "page", required = false) int pageNum) {
		Map<String, Object> pageinfo = this.service.getAll(typeA, typeB, pageNum);
		Status status = new Status("status", "0", pageinfo);
		return status;
	}

	/**
	 * 模糊搜索
	 * 
	 * @param typeA
	 * @param typeB
	 * @param key
	 * @param pageNum
	 * @return
	 */
	@RequestMapping(value = "/searchactive", method = RequestMethod.POST)
	@ResponseBody
	public Status searchActive(@RequestParam(value = "typeA", required = false) int typeA,
			@RequestParam(value = "typeB", required = false) int typeB,
			@RequestParam(value = "key", required = false) int key,
			@RequestParam(value = "page", required = false) int pageNum) {
		Map<String, Object> pageinfo = this.service.getAll(typeA, typeB, pageNum);
		Status status = new Status("status", "0", pageinfo);
		return status;
	}

}
