package com.org.inspection.http_inferface;

import java.net.InetSocketAddress;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.sun.net.httpserver.HttpServer;

/**
 * 服务. <br>
 * 服务的入口，调用{@link Service#startService()}方法启动服务.
 * <p>
 * Copyright: Copyright (c) 2008-11-24
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: gaowei
 * <p>
 * Version: 1.0
 */
public class Service {

	/** Http服务. */
	private static HttpServer httpServer;

	/** Logger. */
	private static Logger logger = Logger.getLogger(Service.class);

	/** 连接池. */
	private static ThreadPoolExecutor processThreadPool;

	/**
	 * 启动Http服务
	 * 
	 * @throws Exception
	 *             异常
	 */
	private static void startHttpService() throws Exception {

		LocalServiceInfo localServiceInfo = SysManager.getInstance().getLocalServiceInfo();
		if (localServiceInfo == null) {
			if (logger.isInfoEnabled()) {
				logger.info("尚未配置服务类，本地Http服务功能禁用");
			}
			return;
		}
		if (logger.isInfoEnabled()) {
			logger.info(localServiceInfo.getName() + "开始启动");
		}

		// 设置系统参数，com.sun.net.httpserver.HttpServerProvider
		// com.cplatform.http.server4.DefaultHttpServerProvider为此版本的通讯组件
		// 如果不设置则使用Java自带的HttpServer：sun.net.httpserver.DefaultHttpServerProvider
		
		String httpServerProvider = "com.haocom.http.server_jetty.JettyHttpServerProvider";
		//String httpServerProvider = "com.haocom.http.server4.DefaultHttpServerProvider";
		System.setProperty("com.sun.net.httpserver.HttpServerProvider", httpServerProvider);

		if (logger.isInfoEnabled()) {
			logger.info("部署的端口是" + localServiceInfo.getPort());
		}

		// 创建HttpServer实例
		httpServer = HttpServer.create(new InetSocketAddress(localServiceInfo.getPort()), 5000);

		// 遍历所有处理器
		for (HttpHandlerInfo httpHandlerInfo : localServiceInfo.getHandlerInfos()) {

			if (logger.isInfoEnabled()) {
				logger.info("加载处理器:" + httpHandlerInfo.requestHandler.getClass());
				logger.info("URL:" + httpHandlerInfo.dealUrl);
			}
			// 创建处理器实例
			httpServer.createContext(httpHandlerInfo.dealUrl, httpHandlerInfo.requestHandler);
		}

		// 创建工作任务队列
		LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(5000);

		// 创建工作线程池
		processThreadPool = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, workQueue);

		// 设置处理线程数量
		httpServer.setExecutor(processThreadPool);

		// 启动HttpServer
		httpServer.start();

		if (logger.isInfoEnabled()) {
			logger.info(localServiceInfo.getName() + "启动成功");
		}
	}

	/**
	 * 启动服务
	 * 
	 * @throws Exception
	 *             异常
	 */
	public static void startService() throws Exception {
		// 初始化系统配置
		SysManager.init();

		// 启动Http服务
		startHttpService();

	}

	/**
	 * 停止服务
	 * 
	 * @throws Exception
	 *             异常
	 */
	public static void stopService() {
		if (processThreadPool != null) {
			processThreadPool.shutdown();
		}
		if (httpServer != null) {
			httpServer.stop(0);
		}

	}

}
