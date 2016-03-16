package com.org.inspection.server.server;

import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.org.inspection.http_inferface.BaseRequestHandler;
import com.org.inspection.server.common.ServerSysManager;
import com.org.inspection.server.info.Status;
import com.org.inspection.util.Json2ObjectUtil;
import com.org.inspection.util.MapToStringUtil;

public class RequestSignin extends BaseRequestHandler{

	/**
	 * 成功 +6的Logger.
	 */
	private final Logger loggerOrderSuccess;
	
	/** 打印日志的字符串缓冲. */
	private final StringBuilder logBuffer;

	/** Logger. */
	private static Logger logger = Logger.getLogger(RequestSignin.class);

	/** 系统管理器. */
	private ServerSysManager serverSysManager;
	
	public RequestSignin() throws Exception {
		super();
		loggerOrderSuccess = Logger.getLogger("OrderSuccess");
		logBuffer = new StringBuilder(200);
	}
	
	
	@Override
	protected byte[] dealReq(Map<String, String> map) throws Exception {
		String reqString = MapToStringUtil.map2String(map);
		logger.info("签到请求：");
		logger.info(reqString);
		if(map.size() == 0){
			System.out.println("请求参数错误");
			return Json2ObjectUtil.status2Json(new Status("1","请求参数错误",null)).getBytes("utf-8");
		}
		String userId = map.get("userid");
		
		if(checkSignin(userId)){
			return Json2ObjectUtil.status2Json(new Status("2","已签到",null)).getBytes("utf-8");
		}
		String longitude = map.get("longitude");
		String latitude = map.get("latitude");
		String status = map.get("stats");
		
		insertSignin(userId, longitude, latitude, status);
		return Json2ObjectUtil.status2Json(new Status("0","success",null)).getBytes("utf-8");
	}

	
	private void insertSignin(String userId, String longitude, String latitude, String status) throws Exception{
		
		synchronized (logBuffer) {
			logBuffer.setLength(0);
			logBuffer.append("insertSignin:").append(userId);
			loggerOrderSuccess.info(logBuffer.toString());
		}
		serverSysManager.insertintoSignin(userId, longitude, latitude, status);
	}


	/**
	 * 一天只能签到一次
	 * @return
	 * @throws Exception 
	 */
	private boolean checkSignin(String userId) throws Exception{
		synchronized (logBuffer) {
			logBuffer.setLength(0);
			logBuffer.append("checkSignin:").append(userId);
			loggerOrderSuccess.info(logBuffer.toString());
		}
		boolean flag = false;
		flag = serverSysManager.checkSignin(userId);
		return flag;
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
	
	private String status2Json(Status st) throws Exception {
		ObjectMapper mapper = new ObjectMapper();  
        String json =  mapper.writeValueAsString(st);
        return json;
	}
	
	
}
