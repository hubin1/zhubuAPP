package com.ss.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ss.pojo.Baseinfo;
import com.ss.pojo.Notification;
import com.ss.pojo.Status;
import com.ss.service.NotificationService;
import com.ss.util.DefaultVariables;

/**
 * 用于获取通知的控制器
 * 
 * @author louis
 *
 */
@Controller
public class NotificationController {
	private Logger logger = Logger.getLogger(NotificationController.class);

	@Resource
	private NotificationService nservice;

	@RequestMapping(value = "/mynote", method = RequestMethod.POST)
	@ResponseBody
	public Status getNotifications(HttpSession session, @RequestParam(value = "page", required = true) int page) {
		Baseinfo original = (Baseinfo) session.getAttribute(DefaultVariables.USERINFO);
		Map<String, Object> map = this.nservice.getNotificationsByBUID(original.getBuid(), page);
		return new Status("status", "0", map);
	}
	
	@RequestMapping(value = "/delnote", method = RequestMethod.POST)
	@ResponseBody
	public Status delNotification(HttpSession session, @RequestParam(value = "nid", required = true) int nid) {
		Baseinfo original = (Baseinfo) session.getAttribute(DefaultVariables.USERINFO);
		Notification temp = new Notification();
		temp.setBuid(original.getBuid());
		temp.setNid(nid);
		
		this.nservice.delete(temp);
		
		temp = null;
		return new Status("status", "0");
	}
	
}
