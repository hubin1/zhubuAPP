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
import com.ss.util.DefaultVariables;

/**
 * 用于登陆请求的类
 * 
 * @author louis
 * @since 2015-12-22
 * @version 1.0
 * 
 *          错误代码表示 0 成功 1 用户不存在 2 用户被锁定 3 用户名密码不正确 4 请求时未能按照规则访问
 */

@Controller
public class LoginController {
	
	private Logger logger = Logger.getLogger(LoginController.class);
	
	public LoginController() {
		logger.debug("-------------LoginController  finished --------------");
	}

	
	@Autowired
	private BaseinfoService baseinfoService;

	/**
	 * 检查 电话号码的合法性 并进行 预先查询
	 * 
	 * @param phone
	 * @param session
	 * @return
	 */

	@RequestMapping(value = "/find", params = { "phone" }, method = RequestMethod.POST)
	@ResponseBody
	public Status checkWithPhone(@RequestParam(value = "phone") String phone, HttpSession session) {
		logger.debug("开始按照电话号码查找"+phone);
		Baseinfo info = this.baseinfoService.getInfoByPhone(phone);
		if (null == info) {
			logger.debug(phone+" 没有找到");
			return new Status("status", "1", null);
		}
		// 将读取到的 userinfo 放入session 中保管
		session.setAttribute(DefaultVariables.USERINFO, info);
		return new Status("status", "0", session.getId());
	}

	/**
	 * 登陆入口
	 * 
	 * @return 处理之后的 用户基本信息
	 */
	@RequestMapping(value = "/login", params = { "phone", "password" }, method = RequestMethod.POST)
	@ResponseBody
	public Status login(@ModelAttribute Baseinfo info, HttpSession session) throws RuntimeException {
		Baseinfo original = (Baseinfo) session.getAttribute(DefaultVariables.USERINFO);
//		if (null == original) {
//			logger.error("有没有按照要求先查找 find 路线的请求 或者没有携带 sessionid 的请求出现");
//			return new Status("status", "4", null);
//		}
		// 进行密码的比较
		String o_phone = original.getPhone();
		String o_password = original.getPassword();

		logger.debug("--------------------------------------");
		logger.debug(o_phone + "  " + o_password);
		logger.debug(info.getPhone() + "  " + info.getPassword());
		logger.debug("--------------------------------------");

		if (o_phone.equals(info.getPhone()) && o_password.equals(info.getPassword())) {
			if (0 == original.getUsed()) {
				return new Status("status", "3", null);
			}

			// 更新登陆时间
			Date date = new Date();
			original.setLasttime(date);

			this.baseinfoService.updateLastTime(original);

			return new Status("status", "0", null, original);
		}
		return new Status("status", "2", null);
	}
}
