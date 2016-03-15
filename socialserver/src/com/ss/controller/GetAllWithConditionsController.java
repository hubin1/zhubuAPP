package com.ss.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ss.pojo.Baseinfo;
import com.ss.pojo.Status;
import com.ss.service.MultiDynamicActivityService;
import com.ss.util.DefaultVariables;

/**
 * 用于获取我和好友的所有动态（不区分视频或者图片动态）
 * 
 * @author louis
 *
 */
@Controller
public class GetAllWithConditionsController {

	@Resource
	private MultiDynamicActivityService mdservice;

	/**
	 * 获得 我的 所有动态
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/myallcomment", method = RequestMethod.POST)
	@ResponseBody
	public Status getMine(HttpSession session, @RequestParam(value = "page", required = true) int page) {
		Baseinfo base = (Baseinfo) session.getAttribute(DefaultVariables.USERINFO);
		
		int buid = base.getBuid();
		Map<String, Object> map = this.mdservice.getActivities(buid, page);

		return new Status("status", "0", map);
	}

	/**
	 * 获得 我朋友的 所有动态
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/myfriends", method = RequestMethod.POST)
	@ResponseBody
	public Status getMyFriends(HttpSession session, @RequestParam(value = "page", required = true) int page) {
		 // 1. 获得我的朋友的集合
		Baseinfo base = (Baseinfo) session.getAttribute(DefaultVariables.USERINFO);
		
		if (null == base) {
			return new Status("status", "1");
		}

		int buid = base.getBuid();
		Map<String, Object> map = this.mdservice.getFriendsActivities(buid, page);
		
		return new Status("status", "0", map);

	}
}
