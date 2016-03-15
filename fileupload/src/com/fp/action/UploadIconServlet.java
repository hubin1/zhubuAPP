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

import com.fp.pojo.Status;
import com.fp.util.CreateDailyFolder;
import com.fp.util.GetRandoms;

public class UploadIconServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(HttpServlet.class);

	String global_position_temp = null;
	String global_position_actual = null;
	long file_size;
	// 文件格式范围
	private final String[] suffix = { ".jpg", ".png" };

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("----------------------");
		logger.debug("接收到请求");

		JSONObject obj = null;
		String ori_fileName = null;

		// 创建 工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置上传时候 的最大缓存 最大 1 mb
		factory.setSizeThreshold(1024000);
		factory.setRepository(new File(this.global_position_temp));

		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置文件最大尺寸
		upload.setFileSizeMax(this.file_size);

		// 开始解析 请求中携带的 所有文件类字段
		try {
			// 创建当日文件夹
			String pos = CreateDailyFolder.createCurrentDayFolder(this.global_position_actual, 4);
			List<FileItem> list = upload.parseRequest(req);
			// 只允许一张，只读取一张
			FileItem icon = list.get(0);
			String fileName = null;
			if (!icon.isFormField()) {
				// 获取文件名称
				ori_fileName = icon.getName().toLowerCase();

				// 在限定的后缀之内
				if (ori_fileName.endsWith(this.suffix[0]) || ori_fileName.endsWith(this.suffix[1])) {
					// 组装新的文件名
					fileName = GetRandoms.getUUID() + ori_fileName.substring(ori_fileName.lastIndexOf("."));
					// 组装目标地址
					String t = this.global_position_actual + File.separator + pos + File.separator + fileName;
					// 将内容写入 实际内容地址
					icon.write(new File(t));
					logger.debug("写入成功");
				} else {
					// 文件类型不支持
					resp.getWriter().println(new JSONObject(new Status(4)));
					logger.debug("后缀名不正确");
					return;
				}
			} else {
				resp.getWriter().println(new JSONObject(new Status(3)));
				logger.debug("读取到的不是 file ");
				return;
			}
			// 成功
			obj = new JSONObject(new Status(0, pos, new String[] { fileName }));

		} catch (Exception ex) {
			logger.error("文件的尺寸过大:" + ori_fileName);
			obj = new JSONObject(new Status(2));
		}

		logger.debug("----------------------");
		resp.getWriter().println(obj.toString());
	}

	@Override
	public void init() throws ServletException {
		logger.info("-------------------------");
		logger.info("检查图片上传所需的参数");

		// 获取参数 计算 文件尺寸
		long size = Integer.parseInt(this.getServletContext().getInitParameter("max_icon_size"));
		this.file_size = size * 1024 * 1024;

		global_position_temp = this.getServletContext().getInitParameter("icon_position_temp");
		global_position_actual = this.getServletContext().getInitParameter("icon_position_actual");

		if (!new File(global_position_temp).isDirectory()) {
			logger.info("存放临时头像图片的文件夹未能找到，建立中.....");
			new File(global_position_temp).mkdirs();
			logger.info("文件夹建立成功");
		}

		if (!new File(global_position_actual).isDirectory()) {
			logger.info("存放头像图片实体的文件夹未能找到，建立中...");
			new File(global_position_actual).mkdirs();
			logger.info("文件夹建立成功");
		}

		logger.info("检查完毕");
	}

}
