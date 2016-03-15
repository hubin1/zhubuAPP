package com.ss.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ss.pojo.HotList;
import com.ss.pojo.Status;
import com.ss.service.HotListService;
import com.ss.util.DefaultVariables;

/**
 * 首页热门信息的设置
 * 设置首页推荐视频的入口
 * 
 * @author louis
 *
 */
@Controller
public class GetRecommandController {

	private Logger logger = Logger.getLogger(GetRecommandController.class);

	@Autowired
	private HotListService service;

	
	@RequestMapping(value = "/hotlist", method = RequestMethod.POST)
	@ResponseBody
	public Status getRecommand(@RequestParam(value = "page", required = true) int currentpage, HttpSession session) {
		logger.debug("有请求进入，携带 cp 是：" + currentpage);
		logger.debug("------------------------");

		// 根据请求的页数 进行 计算 根据配置， 每页由全局变量设置
		List<HotList> hotlist = null;
		try {
			hotlist = service.getHotListLimit(currentpage, DefaultVariables.PAGE_SIZE);
		} catch (Exception ex) {
			logger.debug("查询 hotlist的时候出现异常问题未知");
			return new Status("status", "1");
		}
		if(null == hotlist|| 0 == hotlist.size()){
			logger.debug("没有搜索到任何hotlist信息");
			logger.debug("-------------------------");
			return new Status("status", "1");
		}else{
			logger.debug("采集到 hotlist "+hotlist.size());
			logger.debug("-------------------------");
			return new Status("status", "0", hotlist);
		}
		
	}
}
