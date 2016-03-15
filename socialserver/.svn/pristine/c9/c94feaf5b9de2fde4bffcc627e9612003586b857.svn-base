package com.ss.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ss.dao.IBaseinfoDAO;
import com.ss.dao.IImageCommentDAO;
import com.ss.dao.IMultiDynamicActivityDAO;
import com.ss.dao.IUploadContentExamineDAO;
import com.ss.pojo.Baseinfo;
import com.ss.pojo.Baseinfo_Sample;
import com.ss.pojo.ImageComment;
import com.ss.pojo.MultiDynamicActivity;
import com.ss.pojo.UploadContentExamine;
import com.ss.service.ImageCommentService;
import com.ss.util.DefaultVariables;
import com.ss.util.ShrinkObject;

/**
 * 专门用于进行 图片说说 操纵的 服务
 * 
 * @author louis
 *
 */
@Service("imageCommentServiceImpl")
@Transactional
public class ImageCommentServiceImpl implements ImageCommentService {

	Logger log = Logger.getLogger(ImageCommentServiceImpl.class);

	@Resource
	private IImageCommentDAO idao;

	@Resource
	private IMultiDynamicActivityDAO dao;

	@Resource
	private IUploadContentExamineDAO udao;

	@Resource
	private IBaseinfoDAO bdao;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class, Exception.class })
	public int save(ImageComment comm) {
		// 准备时间等信息
		Date cur = new Date();
		comm.setUploadtime(cur);
		comm.setIspass(0);
		// 子表
		int back_a = idao.insert(comm);
		// 主表
		MultiDynamicActivity act = new MultiDynamicActivity();
		act.setBuid(comm.getBuid());

		act.setType(DefaultVariables.IMAGE);
		act.setUpdatetime(cur);
		act.setRefid(comm.getIcid());
		int back_b = dao.insert(act);

		// 插入待审核的信息
		UploadContentExamine exam = new UploadContentExamine();
		exam.setType(1);
		exam.setRefid(comm.getIcid());
		exam.setUploadtime(cur);
		int back_c = this.udao.insertSelective(exam);

		return back_a + back_b + back_c == 3 ? 1 : 0;
	}

	@Override
	public int update4zan_add(int zid) {
		ImageComment ic = this.idao.selectByPrimaryKey(zid);
		int n = ic.getZan() + 1;
		ic.setZan(n);
		return this.idao.update4zan(ic);
	}

	@Override
	public int update4zan_remove(int zid) {
		ImageComment ic = this.idao.selectByPrimaryKey(zid);
		int n = ic.getZan() - 1;
		ic.setZan(n);
		return this.idao.update4zan(ic);
	}

	@Override
	public Map<String, Object> getAll(int typeA, int typeB, int pageNum) {
		PageHelper.startPage(pageNum, DefaultVariables.PAGE_SIZE);
		// 默认让已经审核过的内容先展示
		PageHelper.orderBy("uploadtime desc");

		List<ImageComment> list = this.idao.select();
		PageInfo<ImageComment> pageinfo = new PageInfo<>(list);

		for (ImageComment vc : list) {
			int buid = vc.getBuid();
			Baseinfo base = this.bdao.selectByPrimaryKey(buid);
			Baseinfo_Sample sample = ShrinkObject.shrinkBaseInfo(base);
			vc.setBaseinfosample(sample);
		}

		Map<String, Object> contents = new HashMap<String, Object>();
		contents.put("total", pageinfo.getTotal());
		contents.put("currentpage", pageinfo.getPageNum());
		contents.put("list", list);
		return contents;
	}

	/**
	 * 
	 */
	@Override
	public Map<String, Object> approximate(int typeA, int typeB, int pageNum, String title) {
		return null;
	}

}
