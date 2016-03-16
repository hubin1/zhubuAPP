package com.org.inspection.server.server;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.org.inspection.http_inferface.BaseRequestHandler;
import com.org.inspection.server.common.ServerSysManager;
import com.org.inspection.server.info.InspectionResult;
import com.org.inspection.server.info.Status;
import com.org.inspection.util.Json2ObjectUtil;
import com.org.inspection.util.MapToStringUtil;

public class RequestQrcodeImage extends BaseRequestHandler{

	/**
	 * 成功 +6的Logger.
	 */
	private final Logger loggerOrderSuccess;
	
	/** 打印日志的字符串缓冲. */
	private final StringBuilder logBuffer;

	/** 系统管理器. */
	private ServerSysManager serverSysManager;
	
	/** Logger. */
	private static Logger logger = Logger.getLogger(RequestQrcodeImage.class);

	
	
	public RequestQrcodeImage() throws Exception {
		super();
		loggerOrderSuccess = Logger.getLogger("OrderSuccess");
		logBuffer = new StringBuilder(200);
	}
	
	@Override
	protected byte[] dealReq(Map<String, String> map) throws Exception {
		
		String reqString = MapToStringUtil.map2String(map);
		logger.info("二维码请求：");
		logger.info(reqString);
		
		String pointId = map.get("pointid");
		if(pointId == null || "".equals(pointId)){
			logger.info("二维码请求图片巡检点编号为空,pointId = " + pointId);
			return Json2ObjectUtil.status2Json(new Status("1","请输入正确的坐标",null)).getBytes("utf-8");
		}
		String userId = map.get("userid");
		
		InspectionResult re = new InspectionResult();
		re.setInspection_result(pointId+"");
		re.setUpdate_user_id(Integer.parseInt(userId));
		re.setStatus(1);
		
		serverSysManager.insertResult(re);
		return Json2ObjectUtil.status2Json(new Status("0","success",null)).getBytes("utf-8");
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
