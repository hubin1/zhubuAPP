package com.org.inspection.http_inferface;

import com.org.inspection.http_inferface.BaseRequestHandler;

/**
 * 处理者信息. <br>
 * 对Http请求处理信息的封装.
 * <p>
 * Copyright: Copyright (c) 2009-4-29 下午05:21:10
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: gaowei
 * <p>
 * Version: 1.0
 * <p>
 */
class HttpHandlerInfo {

	/** 处理的Url. */
	final String dealUrl;

	/** 处理者实例. */
	final BaseRequestHandler requestHandler;

	/**
	 * 构造器
	 * 
	 * @param dealUrl
	 *            处理的地址
	 * @param requestHandle
	 *            处理者实例
	 */
	HttpHandlerInfo(String dealUrl, BaseRequestHandler requestHandler) {
		this.requestHandler = requestHandler;
		this.dealUrl = dealUrl;
	}
}