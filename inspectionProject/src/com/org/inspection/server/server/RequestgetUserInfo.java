package com.org.inspection.server.server;

import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.org.inspection.http_inferface.BaseRequestHandler;
import com.org.inspection.http_inferface.Service;
import com.org.inspection.server.common.ServerSysManager;
import com.org.inspection.server.info.Status;
import com.org.inspection.server.info.UserInfo;
import com.org.inspection.util.Json2ObjectUtil;
import com.org.inspection.util.MapToStringUtil;

/**
 * @author hubin
 * 交互请求处理器. <br>
 * 处理来自交互请求.
 * 当前位置定位结果 （地理位置定位）
 */
public class RequestgetUserInfo extends BaseRequestHandler{

	/**
	 * 成功 +6的Logger.
	 */
	private final Logger loggerOrderSuccess;
	
	/** Logger. */
	private static Logger logger = Logger.getLogger(RequestgetUserInfo.class);


	/** 系统管理器. */
	private ServerSysManager serverSysManager;
	
	public RequestgetUserInfo() throws Exception {
		super();
		loggerOrderSuccess = Logger.getLogger("OrderSuccess");
	}


	@Override
	protected byte[] dealReq(Map<String, String> map) throws Exception {
		String reqString = MapToStringUtil.map2String(map);
		logger.info("位置定位查找user请求：");
		logger.info(reqString);
		if(map.size() == 0){
			System.out.println("请求参数错误");
			logger.info("请求参数错误：");
			return Json2ObjectUtil.status2Json(new Status("1","请求参数错误",null)).getBytes("utf-8");
		}
		String longitude = map.get("longitude");
		String latitude = map.get("latitude");
		UserInfo userInfo = selectUserByPosition(longitude, latitude);
		if(userInfo == null){
			System.out.println("没有数据");
			logger.info("没有查询到数据");
			return Json2ObjectUtil.status2Json(new Status("1","请输入正确的坐标",null)).getBytes("utf-8");
		}
		Status sta = new Status("0","success",userInfo);
		return Json2ObjectUtil.status2Json(sta).getBytes("utf-8");
	}
	
	private UserInfo selectUserByPosition(String longitude, String latitude) throws Exception {
		
		UserInfo userInfo = serverSysManager.getUserInfoByPositon(longitude,latitude);
		
		return userInfo;
	}

	@Override
	public void startService() throws Exception {
		super.startService();
		ServerSysManager.init();
		serverSysManager = ServerSysManager.getInstance();
	}

	@Override
	public void stopService() {
		super.stopService();
		serverSysManager.shutdown();
	}

}
