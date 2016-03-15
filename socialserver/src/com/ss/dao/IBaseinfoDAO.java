package com.ss.dao;

import java.util.List;

import com.ss.pojo.Baseinfo;

/**
 * 操纵 数据库 baseinfo 表的接口，由 mybatis 进行完成
 * 
 * @author louis
 * @version 1.0
 * @since 2015-12-23
 */
public interface IBaseinfoDAO {
	int deleteByPrimaryKey(Integer buid);

	int insert(Baseinfo info);

	int insertSelective(Baseinfo info);

	Baseinfo selectByPrimaryKey(Integer buid);

	List<Baseinfo> selectAll();

	Baseinfo selectByPhone(String phone);

	int updateByPrimaryKeySelective(Baseinfo info);

	int updateByPrimaryKey(Baseinfo info);

	int updateLastloginTime(Baseinfo info);
	
	int updatePassword(Baseinfo info);
	
	int updateIcon(Baseinfo info);

}