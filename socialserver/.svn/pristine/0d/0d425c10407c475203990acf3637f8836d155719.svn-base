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
import com.ss.dao.IFollowImageCommentDAO;
import com.ss.pojo.Baseinfo;
import com.ss.pojo.Baseinfo_Sample;
import com.ss.pojo.FollowImageComment;
import com.ss.service.FollowImageCommentService;
import com.ss.util.DefaultVariables;
import com.ss.util.ShrinkObject;

/**
 * 获取图片动态的评论服务
 * 
 * @author louis
 *
 */
@Service("followImageCommentServiceImpl")
public class FollowImageCommentServiceImpl implements FollowImageCommentService {

	private Logger logger = Logger.getLogger(FollowImageCommentServiceImpl.class);

	@Autowired
	private IFollowImageCommentDAO ifdao;

	@Autowired
	private IBaseinfoDAO ibdao;

	@Override
	public Map<String, Object> getImageCommentWithPage(int icid, int pageNum) {
		// 开始查询
		PageHelper.startPage(pageNum, DefaultVariables.PAGE_SIZE);

		List<FollowImageComment> list = this.ifdao.selectByIcid(icid);
		long total = ((Page<FollowImageComment>) list).getTotal();

		for (FollowImageComment one : list) {
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
	public int save(FollowImageComment comment) {
		return this.ifdao.insertSelective(comment);
	}

}
