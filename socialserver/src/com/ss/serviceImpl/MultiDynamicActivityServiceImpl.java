package com.ss.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ss.dao.IImageCommentDAO;
import com.ss.dao.IMultiDynamicActivityDAO;
import com.ss.dao.IVideoCommentDAO;
import com.ss.pojo.ImageComment;
import com.ss.pojo.MultiDynamicActivity;
import com.ss.pojo.VideoComment;
import com.ss.service.MultiDynamicActivityService;
import com.ss.util.DefaultVariables;

/**
 * 获取全部动态的服务（不区分视频 图片）
 * 
 * @author louis
 *
 */
@Service("multiDynamicActivityServiceImpl")
@Transactional
public class MultiDynamicActivityServiceImpl implements MultiDynamicActivityService {

	Logger logger = Logger.getLogger(MultiDynamicActivityServiceImpl.class);

	@Autowired
	private IMultiDynamicActivityDAO madao;

	@Autowired
	private IImageCommentDAO icdao;

	@Autowired
	private IVideoCommentDAO vcdao;

	/**
	 * 获得自己的动态
	 */
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Map<String, Object> getActivities(int buid, int page) {
		PageHelper.startPage(page, DefaultVariables.PAGE_SIZE);

		List<MultiDynamicActivity> list = this.madao.selectByBUID(buid);
		long total = ((Page<?>) list).getTotal();

		for (MultiDynamicActivity one : list) {
			// 遍历结果是图像 还是 视频
			if (one.getType() == DefaultVariables.IMAGE) {
				ImageComment comment = this.icdao.selectByPrimaryKey(one.getRefid());
				one.setContent(comment);
			} else {
				VideoComment comment = this.vcdao.selectByPrimaryKey(one.getRefid());
				one.setContent(comment);
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("list", list);
		map.put("currentpage", page);

		return map;
	}

	/**
	 * 获得朋友的动态
	 */
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Map<String, Object> getFriendsActivities(int buid, int page) {
		PageHelper.startPage(page, DefaultVariables.PAGE_SIZE);

		List<MultiDynamicActivity> list = this.madao.selectAllWithRelationShip(buid);
		long total = ((Page<?>) list).getTotal();

		for (MultiDynamicActivity one : list) {
			// 遍历结果是图像 还是 视频
			if (one.getType() == DefaultVariables.IMAGE) {
				ImageComment comment = this.icdao.selectByPrimaryKey(one.getRefid());
				one.setContent(comment);
			} else {
				VideoComment comment = this.vcdao.selectByPrimaryKey(one.getRefid());
				one.setContent(comment);
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("list", list);
		map.put("currentpage", page);

		return map;
	}

	/**
	 * 删除动态
	 */
	@Override
	@Transactional(readOnly = true)
	public int deleteActivity(MultiDynamicActivity record) {

		int type = record.getType();

		this.logger.debug("coming type=" + type);
		int result = 0;
		switch (type) {
		case DefaultVariables.IMAGE:
			result = this.icdao.deleteByPrimaryKey(record.getRefid());
			break;
		case DefaultVariables.VIDEO:
			result = this.vcdao.deleteByPrimaryKey(record.getRefid());
			break;
		}
		this.logger.debug("delete primary key is :" + record.getRefid() + "; result is " + result);

		result += this.madao.deleteByPrimaryKey(record.getMdid());

		return result == 2 ? 1 : 0;
	}

}
