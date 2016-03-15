package com.ss.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ss.dao.IBaseinfo_DetailDAO;
import com.ss.pojo.Baseinfo_Detail;
import com.ss.service.Baseinfo_DetailService;

@Service("baseinfoDetailService")
public class Baseinfo_DetailServiceImpl implements Baseinfo_DetailService {

	@Resource
	private IBaseinfo_DetailDAO detailMapper;

	@Override
	public int updateDetail(Baseinfo_Detail record) {
		return this.detailMapper.updateByBUID(record);
	}

	@Override
	public Baseinfo_Detail getDetailByBuid(int buid) {
		return this.detailMapper.selectByBUID(buid);
	}

}
