package com.ss.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ss.dao.INotificationDAO;
import com.ss.pojo.Notification;
import com.ss.service.NotificationService;
import com.ss.util.DefaultVariables;

/**
 * 获取通知的服务
 * 
 * @author louis
 *
 */
@Service("notificationServiceImpl")
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private INotificationDAO dao;

	@Override
	public Map<String, Object> getNotificationsByBUID(int buid, int page) {
		PageHelper.startPage(page, DefaultVariables.PAGE_SIZE);
		
		List<Notification> list = this.dao.selectByBUID(buid);
		long total = ((Page<?>) list).getTotal();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("current", page);
		map.put("list", list);
		
		return map;
	}

	@Override
	public int delete(Notification record) {
		return this.dao.delete(record);
	}
	
	

}
