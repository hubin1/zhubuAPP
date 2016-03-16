package com.org.inspection.http_inferface;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;

import com.haocom.config.Config;
import com.haocom.config.XMLConfig;



/**
 * 系统管理器. <br>
 * 提供对系统运行状态的监控和对配置文件的访问，配置文件位置为config/sys_config.xml.
 * <p>
 * Copyright: Copyright (c) 2009-5-5 下午01:52:11
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: gaowei
 * <p>
 * Version: 1.0
 * <p>
 */
public class SysManager {

	/** 对象实例 */
	private static SysManager instance;

	/** 获取对象实例 */
	public static SysManager getInstance() {
		return instance;
	}

	/**
	 * 初始化对象实例
	 * 
	 * @throws Exception
	 */
	synchronized static void init() throws Exception {
		if (instance != null) {
			return;
		}
		instance = new SysManager();
		instance.loadServiceInfo();
	}

	/** 本地服务信息. */
	private LocalServiceInfo localServiceInfo;

	/** Logger. */
	private final Logger logger;

	/** 系统运行状态. */
	private boolean run = true;

	/** 系统配置. */
	private Config sysConfig;

	/**
	 * 对象创建
	 */
	private SysManager() throws Exception {
		logger = Logger.getLogger(SysManager.class);
		loadSysConfig();

		// 启动一个定时检查停止标记的线程（停止标记的文件名.stop）
		new Thread(new Runnable() {

			@Override
			public void run() {
				doStop();
			}

		}, "CheckStopThread").start();

	}

	/**
	 * 停止操作
	 */
	private void doStop() {
		File file = new File(".stop");
		while (true) {
			// 如果文件存在表示要停止系统
			if (file.exists()) {
				run = false;

				for (HttpHandlerInfo handerInfo : localServiceInfo.getHandlerInfos()) {
					handerInfo.requestHandler.stopService();
				}

				// 删除标记文件
				file.delete();

				// 停止Http服务
				Service.stopService();

				if (logger.isInfoEnabled()) {
					logger.info("系统将要停止");
				}
				break;
			} else {
				// 5秒检查一次
				try {
					Thread.sleep(15000);
				}
				catch (InterruptedException e) {
				}
			}
		}
	}

	/**
	 * 获得通用错误文件的地址
	 * 
	 * @return 通用错误文件的地址
	 */
	String getCommonErrorFile() {
		return sysConfig.getValue("common_error_file");
	}

	/**
	 * 取得系统的字符集
	 * 
	 * @return 系统的字符集
	 */
	public String getEncoding() {
		return sysConfig.getValue("encoding");
	}

	/**
	 * 获得对方服务的地址
	 * 
	 * @return 通用错误文件的地址
	 */
	public String getRemoteUrl() {
		return sysConfig.getValue("remote_url");
	}

	/**
	 * 获取本地服务信息
	 * 
	 * @return 本地服务信息
	 */
	LocalServiceInfo getLocalServiceInfo() {
		return localServiceInfo;
	}

	/**
	 * 得到XML输出格式.<br>
	 * (根据配置文件中的encoding产生，默认为GB18030)
	 * 
	 * @param encoding
	 *            编码格式
	 * @return XML输出格式
	 */
	public Format getXmlFormat() {
		Format format = Format.getPrettyFormat();
		String encoding = getEncoding();
		if (encoding == null || encoding.trim().length() == 0) {
			// 默认使用GB18030编码
			encoding = "GB18030";
		}
		format.setEncoding(encoding);
		return format;
	}

	/**
	 * 获取系统运行状态
	 * 
	 * @return 系统运行状态
	 */
	public boolean isRun() {
		return run;
	}

	/**
	 * 加载服务信息
	 * 
	 * @throws Exception
	 *             异常
	 */
	private void loadServiceInfo() throws Exception {
		SAXBuilder builder = new SAXBuilder();

		// 读取当前服务配置文件的路径并作为XML读入
		Document serviceInfoDoc = builder.build(new File(sysConfig.getValue("service_config_file")));

		Element root = serviceInfoDoc.getRootElement();

		// 读取本地服务信息
		Element localElement = root.getChild("local");

		// 利用classloader获取配置文件中配置的类的实例
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		String temp;
		if (localElement != null) {
			localServiceInfo = new LocalServiceInfo();
			localServiceInfo.setName(localElement.getChildText("name"));
			localServiceInfo.setPort(Integer.parseInt(localElement.getChildText("port")));

			List<?> handlerList = localElement.getChildren("handler_info");
			Element handlerInfoElement;
			BaseRequestHandler baseRequestHandler;
			for (Object object : handlerList) {
				handlerInfoElement = (Element) object;

				// 类路径
				temp = handlerInfoElement.getChildText("class");

				// 产生实例
				baseRequestHandler = (BaseRequestHandler) classLoader.loadClass(temp).newInstance();

				// 进行一些初始化的工作
				baseRequestHandler.startService();

				// url
				temp = handlerInfoElement.getChildText("url");

				// 产生一个新的Http处理器信息实例，存入本地服务信息中
				localServiceInfo.addHandlerInfo(new HttpHandlerInfo(temp, baseRequestHandler));

			}
		}

		// 读入远端服务配置信息
		Element remoteElement = root.getChild("remote");
		if (remoteElement != null) {
			// 暂未实现
		}
	}

	/**
	 * 加载系统配置
	 * 
	 * @throws Exception
	 *             异常
	 */
	private void loadSysConfig() throws Exception {
		// 基本系统配置
		sysConfig = new XMLConfig("基本系统配置", "config/sys_config.xml", true);
	}

}
