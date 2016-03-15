package com.ss.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ss.dao.IBaseinfoDAO;
import com.ss.dao.IBaseinfo_DetailDAO;
import com.ss.pojo.Baseinfo;
import com.ss.pojo.Baseinfo_Detail;
import com.ss.service.BaseinfoService;

/**
 * 用户基本信息服务接口的实现类
 * 
 * @author louis
 * @since 2015-12-22
 * @version 1.0
 */
@Service("baseinfoService")
@Transactional
public class BaseinfoServiceImpl implements BaseinfoService {

	@Resource
	private IBaseinfoDAO baseinfoMapper;

	@Resource
	private IBaseinfo_DetailDAO detailMapper;

	@Override
	public Baseinfo getInfoById(int id) {
		return this.baseinfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Baseinfo> getAll() {
		return this.baseinfoMapper.selectAll();
	}

	/**
	 *  使用事务将 详细表也一并生成
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean insertWithChildren(Baseinfo info) {
		// 选择性的插入列
		this.baseinfoMapper.insertSelective(info);
		// 获取 mybatis 帮我们找到的主键
		int id = info.getBuid();
		
		Baseinfo_Detail detail = new Baseinfo_Detail();
		// 作为外键
		detail.setBuid(id);
		detail.setNick(info.getNick());
		this.detailMapper.insertSelective(detail);
		return true;
	}

	@Override
	public Baseinfo getInfoByPhone(String phone) {
		return this.baseinfoMapper.selectByPhone(phone);
	}

	@Override
	public int updateLastTime(Baseinfo info) {
		return this.baseinfoMapper.updateLastloginTime(info);
	}
	
	public int updatePassword(Baseinfo info){
		return this.baseinfoMapper.updatePassword(info);
	}

	@Override
	public int updateIcon(Baseinfo info) {
		return this.baseinfoMapper.updateIcon(info);
	}

}
