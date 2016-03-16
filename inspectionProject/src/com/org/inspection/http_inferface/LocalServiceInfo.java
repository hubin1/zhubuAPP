package com.org.inspection.http_inferface;

import java.util.ArrayList;
import java.util.List;

/**
 * 本地服务器信息. <br>
 * 对本地服务器信息的封装，包括服务监听的端口和请求处理者等.
 * <p>
 * Copyright: Copyright (c) 2009-4-29 下午05:19:59
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: gaowei
 * <p>
 * Version: 1.0
 * <p>
 */
class LocalServiceInfo {

	/** 处理者信息. */
	private List<HttpHandlerInfo> handlerList;

	/** 名称. */
	private String name;

	/** 端口. */
	private int port;

	/**
	 * 构造器.
	 */
	LocalServiceInfo() {
		handlerList = new ArrayList<HttpHandlerInfo>();
	}

	/**
	 * 添加处理者信息
	 * 
	 * @param handlerInfo
	 *            处理者信息
	 */
	void addHandlerInfo(HttpHandlerInfo handlerInfo) {
		handlerList.add(handlerInfo);
	}

	/**
	 * 获取处理者信息
	 * 
	 * @return 处理者信息数组
	 */
	HttpHandlerInfo[] getHandlerInfos() {
		return handlerList.toArray(new HttpHandlerInfo[0]);
	}

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	String getName() {
		return name;
	}

	/**
	 * 获取端口
	 * 
	 * @return 端口
	 */
	int getPort() {
		return port;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */
	void setName(String name) {
		this.name = name;
	}

	/**
	 * 设置端口
	 * 
	 * @param port
	 *            端口
	 */
	void setPort(int port) {
		this.port = port;
	}

}
