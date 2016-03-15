package com.ss.service;

import java.util.List;

import com.ss.pojo.Baseinfo;

public interface BaseinfoService {
	public Baseinfo getInfoById(int id);

	public List<Baseinfo> getAll();

	public Baseinfo getInfoByPhone(String phone);

	public boolean insertWithChildren(Baseinfo info);

	public int updateLastTime(Baseinfo info);
	
	public int updatePassword(Baseinfo info);
	
	public int updateIcon(Baseinfo info);
}
