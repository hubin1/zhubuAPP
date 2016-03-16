package com.org.inspection.server.server;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.log4j.Logger;

import com.org.inspection.http_inferface.BaseRequestHandler;
import com.org.inspection.server.common.ServerSysManager;
import com.org.inspection.server.info.Status;
import com.org.inspection.util.Json2ObjectUtil;
import com.sun.net.httpserver.HttpExchange;

public class RequestImageSave extends BaseRequestHandler{

	/**
	 * 成功 +6的Logger.
	 */
	private final Logger loggerOrderSuccess;

	/** Logger. */
	private static Logger logger = Logger.getLogger(RequestImageSave.class);


	/** 系统管理器. */
	private ServerSysManager serverSysManager;
	
	/** 图片存储路径*/
	private final String savePath = "D:/savepath/";
	
	private String path;
	
	
	public RequestImageSave() throws Exception {
		super();
		loggerOrderSuccess = Logger.getLogger("OrderSuccess");
	}
	
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		
		if(!new File(savePath).isDirectory()){
			new File(savePath).mkdirs();
		}
		
		File file = new File(savePath);
		int leng = file.list().length + 1;
		String imageName = (nowdate2string() + leng + ".png").trim();
		
		boolean resp = false;
		Status st = null;
		
		// 获取输入流
		InputStream inputStream = exchange.getRequestBody();
		
		try{
			BufferedImage  image = ImageIO.read(inputStream);
			ImageIO.write(image, "png", new File(savePath + imageName));
			resp = true;
			st = new Status("0","success",savePath + imageName);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("RequestImageSave---解析请求失败", e);
		}finally{
			inputStream.close();
		}
		
		
		// 获取输出流
		OutputStream outputStream = exchange.getResponseBody();
		try {
			// 返回响应
			if (resp) {
				// 处理正常时
				exchange.sendResponseHeaders(200, 0);

			} else {
				// 异常时设为500
				exchange.sendResponseHeaders(500, 0);
				st = new Status("1","fail",null);

			}
			outputStream.write(Json2ObjectUtil.status2Json(st).getBytes("utf-8"));
			outputStream.flush();
		}
		catch (Exception e) {
			logger.error("RequestImageSave---发送响应异常", e);
		}
		finally {
			outputStream.close();
		}
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
	
	private String nowdate2string(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		long time = System.currentTimeMillis();
		Date date = new Date(time);
		return sdf.format(date);
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public String getSavePath() {
		return savePath;
	}
	
	
	
}
