package com.ss.controller;

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
import com.ss.pojo.Baseinfo_Detail;
import com.ss.pojo.Status;
import com.ss.service.BaseinfoService;
import com.ss.service.Baseinfo_DetailService;
import com.ss.util.DefaultVariables;

/**
 * []
 * <p>
 * [用于进行用户信息的更新 和 手机号码的更新]
 * <p>
 * 
 * @author Louis
 * @version 1.0, 2016年1月16日
 * @see
 * @since V1001
 * 
 *        可能出现的错误代码 0 成功 1 用户 token 失效 2 信息更
 */
@Controller
public class UpdateInfoController {

	private static final Logger log = Logger.getLogger(UpdateInfoController.class);
	@Autowired
	private Baseinfo_DetailService bdservice;

	private BaseinfoService bservice;

	/**
	 * 获取用户的详细信息
	 * 
	 * @param session
	 * @return
	 * 
	 * 		可能出现的错误代码 0 成功 1 详细信息未找到 2 token 失效
	 */
	@RequestMapping(value = "/getdetail")
	@ResponseBody
	public Status getBaseinfoDetail(HttpSession session) {
		Baseinfo bi = (Baseinfo) session.getAttribute(DefaultVariables.USERINFO);
//		if (bi == null) {
//			log.debug("session 失效，未能找到 baseinfo");
//			return new Status("status", "2", null);
//		}
		int buid = bi.getBuid();
		Baseinfo_Detail detail = this.bdservice.getDetailByBuid(buid);

		if (null == detail) {
			log.debug("未能找到 detail 信息");
			return new Status("status", "1", null);
		} else {
			log.debug("成功找到 并返回");
			return new Status("status", "0", null, detail);
		}

	}

	/**
	 * 更新 用户的详细信息（所有信息）
	 * 
	 * @param session
	 * @param detail
	 */
	@RequestMapping(value = "/updatedetail")
	@ResponseBody
	public Status updateBaseInfoDetail(HttpSession session, @ModelAttribute Baseinfo_Detail detail) {
		Baseinfo bi = (Baseinfo) session.getAttribute(DefaultVariables.USERINFO);

//		if (bi == null) {
//			log.debug("session 失效，未能找到 baseinfo");
//			return new Status("status", "1", null);
//		}

		int buid = bi.getBuid();
		detail.setBuid(buid);

		int result = this.bdservice.updateDetail(detail);

		if (0 == result) {
			log.debug("更新用户信息失败");
			return new Status("status", "2", null);
		}
		return new Status("status", "0", null);
	}

	@RequestMapping(value = "/updateicon", method = RequestMethod.POST)
	public Status updateIcon(HttpSession session, @RequestParam(value = "iname", required = true) String iname,
			@RequestParam(value = "ipos", required = true) String ipos) {

		Baseinfo bi = (Baseinfo) session.getAttribute(DefaultVariables.USERINFO);
//		if (bi == null) {
//			log.debug("session 失效，未能找到 baseinfo");
//			return new Status("status", "2", null);
//		}
		bi.setIcon(iname);
		bi.setIpos(ipos);

		int back = this.bservice.updateIcon(bi);

		return back == 1 ? new Status("status", "0") : new Status("status", "2");
	}
}
