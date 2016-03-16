package com.org.inspection.http_inferface;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import com.haocom.util.security.SHA;
import com.org.inspection.server.info.Status;
import com.org.inspection.util.Json2ObjectUtil;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.xml.internal.ws.util.StringUtils;

/**
 * 基本请求处理器. <br>
 * <br>
 * 所有请求处理器的基类，其他请求处理器需继承该类，支持WebService；<br>
 * 通常处理Http请求需要实现{@link BaseRequestHandle#dealReq(Document)}方法；<br>
 * 当需要处理空请求时需要实现{@link BaseRequestHandle#dealEmptyRequest(Headers, URI)}方法;<br>
 * 系统停止时会调用{@link BaseRequestHandler#stopService()}方法。
 * <p>
 * Copyright: Copyright (c) 2009-4-29 下午05:06:44
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: gaowei
 * <p>
 * Version: 1.0
 * <p>
 */
public class BaseRequestHandler implements HttpHandler {

	/** XML构建器. */
	protected SAXBuilder builder;

	/** Logger. */
	protected Logger logger;

	/** 系统配置. */
	protected SysManager sysManager;


	/**
	 * 构造器
	 */
	public BaseRequestHandler() {
		logger = Logger.getLogger(this.getClass());
		sysManager = SysManager.getInstance();

		builder = new SAXBuilder();
		// 设置不重用Parser
		builder.setReuseParser(false);
	}

	

	/**
	 * 处理请求
	 * 
	 * @param reqDoc
	 *            请求XML
	 * @return 响应的XML文档
	 * @throws Exception
	 *             异常
	 */
	protected byte[] dealReq(Map<String, String> map) throws Exception {
		return "".getBytes("UTF-8");
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		
		byte[] resp = null;

		// 获取输入流
		InputStream inputStream = exchange.getRequestBody();
		
		String getParams = exchange.getRequestURI().getQuery();
		
		try {
			Map<String, String> paramMap = getParamMap(getParams);
			resp = dealReq(paramMap);
		}catch(Exception e){
			logger.error("解析请求失败", e);
		}finally {
			inputStream.close();
		}
			
		// 获取输出流
		OutputStream outputStream = exchange.getResponseBody();
		try {
			// 返回响应
			if (resp != null) {
				// 处理正常时
				exchange.sendResponseHeaders(200, 0);

			} else {
				// 异常时设为500
				exchange.sendResponseHeaders(500, 0);
				resp = Json2ObjectUtil.status2Json(new Status("1","服务器异常",null)).getBytes("utf-8");;

			}
			outputStream.write(resp);
			outputStream.flush();
		}
		catch (Exception e) {
			logger.error("发送响应异常", e);
		}
		finally {
			outputStream.close();
		}
	}
	/**
	 * 本服务启动所需要执行的初始化操作
	 * 
	 * @throws Exception
	 *             异常
	 */
	public void startService() throws Exception {
	}

	/**
	 * 本服务停止所需要执行的停止操作
	 */
	public void stopService() {
	}
	
	
	/**
	 * 根据参数字符串获取参数map
	 * @param getParams
	 * @return
	 * @throws Exception
	 */
	private Map<String, String> getParamMap(String getParams) throws Exception {
		
		Map<String, String> paramsMap = new HashMap<String, String>();
		if(getParams!=null && !"".equals(getParams)){
			String[] tmpArr = getParams.split("&");
			for(String str : tmpArr){
				String[] strArr = str.split("=");
				paramsMap.put(strArr[0], strArr[1]);
			}
			
		}
		
		return paramsMap;
	}
}
