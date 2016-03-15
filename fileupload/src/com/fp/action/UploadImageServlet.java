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

/**
 * 上传图片的 专用 servlet
 * 
 * @author louis
 *
 */
public class UploadImageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(UploadImageServlet.class);
	
	String global_position_temp = null;
	String global_position_actual = null;
	long file_size;
	// 文件格式范围
	private final String[] suffix = { ".jpg", ".png" };

	@Override
	public void init() throws ServletException {
		logger.info("-------------------------");
		logger.info("检查图片上传所需的参数");
		
		// 获取参数 计算 文件尺寸
		long size = Integer.parseInt(this.getServletContext().getInitParameter("max_image_size"));
		this.file_size = size * 1024 * 1024;

		global_position_temp = this.getServletContext().getInitParameter("image_position_temp");
		global_position_actual = this.getServletContext().getInitParameter("image_position_actual");
				
		if (!new File(global_position_temp).isDirectory()) {
			logger.info("存放临时图片文件的文件夹未能找到，建立中.....");
			new File(global_position_temp).mkdirs();
			logger.info("文件夹建立成功");
		}

		if (!new File(global_position_actual).isDirectory()) {
			logger.info("存放文件实体的文件夹未能找到，建立中...");
			new File(global_position_actual).mkdirs();
			logger.info("文件夹建立成功");
		}

		logger.info("检查完毕");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JSONObject obj = null;

		// 创建 工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置上传时候 的最大缓存
		factory.setSizeThreshold(1 * 1024);
		factory.setRepository(new File(this.global_position_temp));

		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置文件最大尺寸
		upload.setFileSizeMax(this.file_size);

		// 开始解析 请求中携带的 所有文件类字段
		try {
			List<FileItem> list = upload.parseRequest(req);
			// 为多个内容做准备
			String[] names = new String[list.size()];
			
			logger.debug("共计收到图片 "+list.size()+" 张");
			
			if(list.size() == 0){
				throw new IllegalArgumentException();
			}
			
			// 计数器
			int count = 0;
			// 创建统一的保存目录
			String pos = CreateDailyFolder.createCurrentDayFolder(this.global_position_actual, 1);
			// 遍历每一个
			for (FileItem item : list) {
				// 判断此表单有 file 类型的上传栏位
				if (!item.isFormField()) {
					String ori_fileName = item.getName().toLowerCase();
					// 在限定的后缀之内
					if (ori_fileName.endsWith(this.suffix[0]) || ori_fileName.endsWith(this.suffix[1])
							|| ori_fileName.endsWith(this.suffix[2])) {
						String fileName = GetRandoms.getUUID() + ori_fileName.substring(ori_fileName.lastIndexOf("."));
						names[count++] = fileName;
						String t = this.global_position_actual +File.separator + pos + File.separator + fileName;
						// 将内容写入 实际内容地址
						item.write(new File(t));
					} else {
						// 文件类型不支持
						obj = new JSONObject(new Status(3));
					}
				}
			}
			logger.debug("实际有效处理图片 "+count+" 个");
			// 成功
			obj = new JSONObject(new Status(0, pos, names));
		} catch (Exception ex) {
			// 文件尺寸错误
			obj = new JSONObject(new Status(4));
		}
		System.out.println(obj.toString());
		resp.getWriter().println(obj.toString());
	}

}
