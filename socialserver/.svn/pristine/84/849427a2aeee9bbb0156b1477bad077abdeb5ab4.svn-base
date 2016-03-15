package com.ss.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ss.dao.IBaseinfoDAO;
import com.ss.dao.IFollowVideoCommentDAO;
import com.ss.pojo.Baseinfo;
import com.ss.pojo.Baseinfo_Sample;
import com.ss.pojo.FollowVideoComment;
import com.ss.service.FollowVideoCommentService;
import com.ss.util.DefaultVariables;
import com.ss.util.ShrinkObject;

/**
 * 获得视频内容评论的请求
 * 
 * @author louis
 *
 */
@Service("followVideoCommentServiceImpl")
public class FollowVideoCommentServiceImpl implements FollowVideoCommentService {

	private Logger logger = Logger.getLogger(FollowImageCommentServiceImpl.class);

	@Autowired
	private IFollowVideoCommentDAO ifdao;

	@Autowired
	private IBaseinfoDAO ibdao;

	@Override
	public Map<String, Object> getVideoCommentWithPage(int vid, int pageNum) {
		// 开始查询
		PageHelper.startPage(pageNum, DefaultVariables.PAGE_SIZE);

		List<FollowVideoComment> list = this.ifdao.selectByVid(vid);
		long total = ((Page<FollowVideoComment>) list).getTotal();

		for (FollowVideoComment one : list) {
			Baseinfo base = this.ibdao.selectByPrimaryKey(one.getBuid());
			Baseinfo_Sample baseinfosample = ShrinkObject.shrinkBaseInfo(base);
			one.setBaseinfosample(baseinfosample);
		}
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("list", list);
		map.put("currentpage", pageNum);
		
		return map;
	}

	@Override
	public int save(FollowVideoComment comment) {
		return this.ifdao.insertSelective(comment);
	}

}
