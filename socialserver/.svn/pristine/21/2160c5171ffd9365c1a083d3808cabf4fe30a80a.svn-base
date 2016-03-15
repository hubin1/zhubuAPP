package com.ss.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ss.pojo.Baseinfo;
import com.ss.pojo.MultiDynamicActivity;
import com.ss.pojo.Status;
import com.ss.service.MultiDynamicActivityService;
import com.ss.util.DefaultVariables;

/**
 * 
 * 
 * @author louis
 *
 */
@Controller
public class UpdateActivityController {

	private Logger logger = Logger.getLogger(UpdateActivityController.class);

	private MultiDynamicActivityService service;

	/**
	 * 删除自己的动态
	 * 
	 * @param session
	 * @param type
	 *            动态类型
	 * @param aid
	 *            类型所在的id
	 * @param mdid
	 *            动态id
	 * @return
	 */
	@RequestMapping(value = "/delmyact", method = RequestMethod.POST)
	public Status deleteMyActivity(HttpSession session, @ModelAttribute MultiDynamicActivity record) {
		
		Baseinfo baseinfo = (Baseinfo)session.getAttribute(DefaultVariables.USERINFO);
		
		record.setBuid(baseinfo.getBuid());
		
		int result = this.service.deleteActivity(record);
		Status status = new Status();
		status.setKey("status");
		
		if(1 == result){
			status.setValue("0");
		}else{
			status.setValue("2");
		}
		
		return status;

	}
}
