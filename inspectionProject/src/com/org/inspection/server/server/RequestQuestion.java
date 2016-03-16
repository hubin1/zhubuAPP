package com.org.inspection.server.server;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;

import com.org.inspection.http_inferface.BaseRequestHandler;
import com.org.inspection.server.common.ServerSysManager;
import com.org.inspection.server.info.InspectionResult;
import com.org.inspection.server.info.Status;
import com.org.inspection.util.Json2ObjectUtil;
import com.org.inspection.util.MapToStringUtil;
import com.sun.net.httpserver.HttpExchange;

public class RequestQuestion extends BaseRequestHandler{

	/**
	 * 成功 +6的Logger.
	 */
	private final Logger loggerOrderSuccess;

	/** Logger. */
	private static Logger logger = Logger.getLogger(RequestQuestion.class);

	/** 打印日志的字符串缓冲. */
	private final StringBuilder logBuffer;

	/** 系统管理器. */
	private ServerSysManager serverSysManager;
	
	public RequestQuestion() throws Exception {
		super();
		loggerOrderSuccess = Logger.getLogger("OrderSuccess");
		logBuffer = new StringBuilder(200);
	}
	

	@Override
	protected byte[] dealReq(Map<String, String> map) throws Exception {
		
		String reqString = MapToStringUtil.map2String(map);
		logger.info("位置定位查找user请求：");
		logger.info(reqString);
		if(map.size() == 0){
			System.out.println("请求参数错误");
			return Json2ObjectUtil.status2Json(new Status("1","请求参数错误",null)).getBytes("utf-8");
		}
		String path = map.get("picpath");
		String userId = map.get("userid");
		String results = map.get("results");
		String optionName = map.get("optionname");
		String pointNo = map.get("pointno");
		
		int point_id = getPointId(pointNo);
		if(point_id == -1){
			logger.info("RequestQuestion------pointNo错误");
			throw new Exception();
		}
		int optionId = insertOption(optionName,point_id);
		if(optionId == -1){
			logger.info("RequestQuestion---------数据库提取optionid错误 ");
			throw new Exception();
		}
		InspectionResult re = new InspectionResult();
		re.setInspection_result(results);
		re.setPic_path(path);
		re.setUpdate_user_id(Integer.parseInt(userId));
		re.setStatus(0);
		re.setOption_id(optionId);
		
		insertResult(re);
		
		return Json2ObjectUtil.status2Json(new Status("0","success",null)).getBytes("utf-8");
	}

	private int insertOption(String optionName,int pointId) throws Exception{
		synchronized (logBuffer) {
			logBuffer.setLength(0);
			logBuffer.append("insertOption:");
			loggerOrderSuccess.info(logBuffer.toString());
		}
		return serverSysManager.insertOption(optionName, pointId);
	}

	private int getPointId(String pointNo) throws Exception{
		synchronized (logBuffer) {
			logBuffer.setLength(0);
			logBuffer.append("getPointId:");
			loggerOrderSuccess.info(logBuffer.toString());
		}
		return serverSysManager.getPointId(pointNo);
	}

	
	private void insertResult(InspectionResult re) throws Exception{
		synchronized (logBuffer) {
			logBuffer.setLength(0);
			logBuffer.append("insertResult:");
			loggerOrderSuccess.info(logBuffer.toString());
		}
		serverSysManager.insertResult(re);
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
