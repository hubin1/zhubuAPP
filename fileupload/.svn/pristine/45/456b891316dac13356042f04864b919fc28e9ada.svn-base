package com.fp.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.fp.pojo.VStatus;
import com.fp.util.CreateDailyFolder;
import com.fp.util.GetRandoms;

/**
 * 用于进行文件上传的类
 * 
 * @author louis
 *
 */
/*
 * mac ffmpeg 指令 ./ffmpeg -i /Users/louis/Desktop/shaai.mp4 -f image2 -ss 5
 * -vframes 1 /Users/louis/Desktop/shaai.jpeg
 */
public class UploadVideoServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(UploadVideoServlet.class);

	String global_position_temp = null;
	String global_position_actual = null;
	String global_videoimg = null;
	// 最大的文件上载容量 mb 为单位
	long file_size;
	private final String[] suffix = { ".mp4", ".mov", ".m4v",".3gp" };

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		JSONObject obj = null;
		logger.debug("------------------------------");
		logger.debug("get info");
		// 创建 工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置上传时候 的最大缓存
		factory.setSizeThreshold(5 * 1024);
		factory.setRepository(new File(this.global_position_temp));

		ServletFileUpload upload = new ServletFileUpload(factory);

		logger.debug("max video size:" + this.file_size);
		upload.setSizeMax(this.file_size);

		// 开始 获取请求中携带的所有文件
		try {
			List<FileItem> files = upload.parseRequest(req);

			// 一次只能上传一个视频
			FileItem item = files.get(0);
			logger.debug(item);
			if (item != null && !item.isFormField()) {
				String ori_fileName = item.getName().toLowerCase();
				
				// 获取并判断后缀
				String suff = ori_fileName.substring(ori_fileName.lastIndexOf("."));
				logger.debug("获取文件的后缀："+suff);
				
				int posin = -1;
				
				for(int i = 0; i< suffix.length; i++){
					if(suffix[i].equals(suff)){
						posin = i;
						break;
					}
				}
				
				if (posin != -1) {
					// 生成 UUID
					String fileName = GetRandoms.getUUID() + ori_fileName.substring(ori_fileName.lastIndexOf("."));
					String target = CreateDailyFolder.createCurrentDayFolder(this.global_position_actual, 2);
					String folder = this.global_position_actual + File.separator + target;

					logger.debug("uploaded video size:" + item.getSize());

					logger.debug(folder + File.separator + fileName);
					item.write(new File(folder + File.separator + fileName));
					// 此处 处理 截取帧数
					String[] imp = CreateDailyFolder.createImage(folder + File.separator, fileName,
							this.global_videoimg + File.separator);
					// 收到了信息
					if (null == imp) {
						logger.debug("ffmpeg 未能传回视频截图信息");
						obj = new JSONObject(new VStatus(3));
					} else {
						logger.debug("ffmpeg 完成，准备返回");

						obj = new JSONObject(new VStatus(0, target, posin, imp[0], imp[1]));
					}
				} else {
					// 文件的后缀不正确
					logger.debug("视频文件 后缀不支持");
					obj = new JSONObject(new VStatus(4));
				}
			} else {
				// 文件未上传 或者 传递的内容 不包含 上传的内容
				logger.debug("请求中没有 包含视频文件");
				obj = new JSONObject(new VStatus(1));
			}
		} catch (Exception e) {
			// 文件的大小 超过限制导致失败
			logger.debug("文件过大");
			obj = new JSONObject(new VStatus(2));
		}
		logger.debug("------------------------------");
		logger.debug("sent:");
		logger.debug(obj.toString());
		response.getWriter().println(obj.toString());

	}

	@Override
	public void init() throws ServletException {
		// 获取参数 计算 文件尺寸
		int size = Integer.parseInt(this.getServletContext().getInitParameter("max_video_size"));
		this.file_size = size * 1024 * 1024;

		logger.info("-------------------------");
		logger.info("检查视频上传所需的参数");

		global_position_temp = this.getServletContext().getInitParameter("video_position_temp");
		global_position_actual = this.getServletContext().getInitParameter("video_position_actual");

		// videoimg_position
		this.global_videoimg = this.getServletContext().getInitParameter("video_img_position");
		File f1 = new File(global_position_temp);
		if (!f1.isDirectory()) {
			logger.info("存放临时视频文件的文件夹未能找到，建立中...");
			f1.mkdirs();
			logger.info("文件夹建立成功");
		}

		File f2 = new File(global_position_actual);
		if (!f2.isDirectory()) {
			logger.info("存放视频实体的文件夹未能找到，建立中...");
			f2.mkdirs();
			logger.info("文件夹建立成功");
		}

		File f3 = new File(global_videoimg);
		if (!f3.isDirectory()) {
			logger.info("存放视频帧数截图的文件夹未能找到，建立中...");
			f3.mkdirs();
			logger.info("文件夹建立成功");
		}

		logger.info("检查完毕");
	}
}
