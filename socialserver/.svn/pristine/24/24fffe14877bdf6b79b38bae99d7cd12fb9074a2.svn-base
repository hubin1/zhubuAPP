package com.ss.serviceImpl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ss.dao.IHotListDAO;
import com.ss.dao.IBaseinfoDAO;
import com.ss.dao.IVideoCommentDAO;
import com.ss.pojo.Baseinfo;
import com.ss.pojo.HotList;
import com.ss.pojo.VideoComment;
import com.ss.service.HotListService;
import com.ss.util.ShrinkObject;

/**
 * 获取 热门信息的接口的实现
 * 
 * @author Administrator
 *
 */
@Service("hotListServiceImpl")
@Transactional
public class HotListServiceImpl implements HotListService {
	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(HotListServiceImpl.class);

	@Autowired
	private IHotListDAO hdao;

	@Autowired
	private IVideoCommentDAO vdao;

	@Autowired
	private IBaseinfoDAO bdao;

	/**
	 * 获取热门信息并 将详细信息获取到
	 */
	@Override
	@Transactional
	public Page<HotList> getHotListLimit(int start, int size) {
	
		PageHelper.startPage(start, size);
		
		Page<HotList> list = (Page<HotList>)hdao.selectAll();
		// 遍历 list 并获得内容
		Iterator<HotList> itr = list.iterator();
		while(itr.hasNext()){
			HotList one = itr.next();
			
			// 获取视频信息id
			int id = one.getVid();
			// 逐个查询每一个视频的详细
			VideoComment vc = this.vdao.selectByPrimaryKey(id);
			logger.debug("------------------------");
			logger.debug(vc.getIpos());
			logger.debug("------------------------");
			
			// 逐个查询用户的基本信息
			Baseinfo base = this.bdao.selectByPrimaryKey(vc.getBuid());
			
			// 获取后进行收缩
			vc.setBaseinfosample(ShrinkObject.shrinkBaseInfo(base));
			one.setVideo(vc);
		}
		
		
		return list;
	}

}
