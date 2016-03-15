package com.ss.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ss.pojo.Status;

/**
 * 用户退出系统的处理
 * @author louis
 *
 * status 返回说明  0 表示成功
 */

@Controller
public class ExitController {
	
	Logger logger = Logger.getLogger(ExitController.class);
	
	@RequestMapping(value="/exit")
	@ResponseBody
	public Status exit(HttpSession session){
		session.invalidate();
		return new Status("status", "0", null);
	}
	
}
