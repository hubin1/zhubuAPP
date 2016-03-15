package com.ss.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ss.pojo.Baseinfo;
import com.ss.pojo.Status;
import com.ss.service.BaseinfoService;
import com.ss.util.CreateValidateCode;
import com.ss.util.DefaultVariables;
import com.ss.util.SendMsgToPlatform;


/**
 * 该类用于进行注册的流程
 * 
 * @author louis
 *
 */
@Controller
public class RegistController {

	Logger logger = Logger.getLogger(RegistController.class);
	@Autowired
	private BaseinfoService baseinfoService;

	/**
	 * 用于进行新用户的注册 注意：再次请求注册的时候 无需携带 手机号
	 * 
	 * @param info
	 *            基本的用户注册信息 不包含手机号
	 * @param validcode
	 *            发送到手机上的验证码
	 * @param session
	 *            会话区间
	 * 
	 * @return status 0 表示成功
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	@ResponseBody
	public Status regist(@ModelAttribute Baseinfo info,
			@RequestParam(value = "vcode", required = true) String validcode, HttpSession session) {
		logger.debug("传递过来的sessionid："+session.getId());
		
		String ori_vaildcode = (String) session.getAttribute(DefaultVariables.ONCECODE);
		// 没有 任何问题进行
		if (null == ori_vaildcode || !ori_vaildcode.equals(validcode)) {
			logger.debug("原始验证码不存在或者验证码不正确");
			return new Status("status", "2", null);
		}

		// 开始保存信息
		String phone = (String) session.getAttribute(DefaultVariables.ORIPHONE);
		info.setPhone(phone);
		info.setRegister(new Date());
		// 把详细信息也建立起来
		this.baseinfoService.insertWithChildren(info);
		
		logger.debug("OK send back");
		// 成功
		return new Status("status", "0", null);
	}

	/**
	 * 查验用户的手机号是否 已经使用
	 * 
	 * @param phone
	 *            客户端传递来的手机号
	 * @return 查询的状态  0 表示成功   1 电话号码存在
	 */
	@RequestMapping(value = "/sendcode", params = { "phone" }, method = RequestMethod.POST)
	@ResponseBody
	public Status sendCode(@RequestParam(value = "phone") String phone, HttpSession session) {
		
		Baseinfo info = this.baseinfoService.getInfoByPhone(phone);
		if (null == info) {
			// 如果可以， 立即向 平台发起验证码请求
			String code = CreateValidateCode.create4BitValidateCode();
			logger.debug("=======================");
			logger.debug("Valid code:"+code);
			SendMsgToPlatform.sender(phone, "您的验证码为" + code);
			// 保存验证码
			session.setAttribute(DefaultVariables.ONCECODE, code);
//			session.setAttribute(DefaultVariables.ONCECODE, "1111");
			session.setAttribute(DefaultVariables.ORIPHONE, phone);
			logger.debug("下发的sessionid:"+session.getId());
			return new Status("status", "0", session.getId());
		}
		return new Status("status", "1", null);
	}

}
