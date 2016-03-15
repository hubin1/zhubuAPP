package com.ss.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ss.pojo.Status;

/**
 * 获取静态信息
 * 
 * @author louis
 *
 */
@Controller
public class GetStaticInfomation {

	private Date last = new Date();

	private List<String> slider = new ArrayList<String>();

	private Logger logger = Logger.getLogger(GetStaticInfomation.class);

	public GetStaticInfomation() {
		repeat();
	}

	private void repeat() {
		this.logger.info("=====开始读取主页流动展示=====");

		InputStream input = this.getClass().getResourceAsStream("/topslider.txt");

		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		this.slider.clear();

		while (true) {
			String temp = null;
			try {
				temp = reader.readLine();
			} catch (IOException e) {
				logger.error(e);
			}
			if (null == temp) {
				break;
			} else {
				this.slider.add(temp);
			}
		}

		this.logger.info("=====读取主页流动展示成功=====");
	}

	/**
	 * 获取展示在主页上的广告信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/gettop", method = RequestMethod.POST)
	@ResponseBody
	public synchronized Status getTopSlider() {
		Date cur = new Date();

		if (cur.getTime() - this.last.getTime() >= (1000 * 60 * 60 * 5)) {
			this.last = cur;
			repeat();
		}

		return new Status("status", "0", this.slider);

	}
}
