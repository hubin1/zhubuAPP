package com.ss.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * 找回密码功能 需要注意 更新密码 和找回 密码 都是用 这个类。但是更新密码 不需要 发送 手机验证码也就是说不需要调用 CheckwithPhone
 * 这个方法。但是 找回密码 必须调用 checkWithPhone 这个方法
 * 
 * @author louis
 *
 */
@Controller
public class FindBackController {
	private static final Logger log = Logger.getLogger(FindBackController.class);
	@Autowired
	private BaseinfoService baseinfoService;

	@RequestMapping(value = "/reset", params = { "phone" }, method = RequestMethod.POST)
	@ResponseBody
	public Status checkWithPhone(@RequestParam(value = "phone") String phone, HttpSession session) {
		// 通过手机号找到 这个用户的信息
		Baseinfo info = this.baseinfoService.getInfoByPhone(phone);
		if (null == info) {
			// 手机号不存在的时候 无法找回密码
			return new Status("status", "2", null);
		}

		// info.setPassword(null);
		session.setAttribute(DefaultVariables.ORGINFO, info);
		// 生成验证码准备
		String code = CreateValidateCode.create4BitValidateCode();
		session.setAttribute(DefaultVariables.ONCECODE, code);
		// session.setAttribute(DefaultVariables.ONCECODE, "1111");
		SendMsgToPlatform.sender(phone, "您的验证码为" + code);

		return new Status("status", "0", session.getId());
	}

	@RequestMapping(value = "/updatepassword", method = RequestMethod.POST)
	@ResponseBody
	public Status updatePassword(@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "npassword") String npassword,
			@RequestParam(value = "vcode", required = false) String vcode, HttpSession session) {
		Baseinfo info = (Baseinfo) session.getAttribute(DefaultVariables.ORGINFO);
		// 防止不带 session 的非法访问
//		if (null == info) {
//			log.error("有未携带 sessionid 的请求进入！");
//			return new Status("status", "3", null);
//		}
		// 修改密码线路
		if (vcode == null) {
			// 旧密码不一致
			if (!info.getPassword().equals(password)) {
				// 提供的密码与当前 密码 不一致
				log.debug("密码是" + password);
				return new Status("status", "3", null);
			}
		} else {
			// 找回密码线路
			String code;
			try {
				code = session.getAttribute(DefaultVariables.ONCECODE).toString();
			} catch (NullPointerException ex) {
				log.error("有未执行先发送验证码业务 的请求进入！");
				return new Status("status", "4", null);
			}
			// 如果验证码不正确
			if (code.equals(vcode) == false) {
				// 验证码 即刻失效
//				session.removeAttribute(DefaultVariables.ONCECODE);
				session.invalidate();
				log.debug("验证码是" + vcode + ", 原来存的是" + code);
				return new Status("status", "2", null);
			}
		}
		// 更新密码 写入 数据库
		info.setPassword(npassword);
		this.baseinfoService.updatePassword(info);
		// 写入成功 重新登录
		session.invalidate();
		return new Status("status", "0", null);
	}
}
