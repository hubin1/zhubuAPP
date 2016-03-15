package com.ss.dao;

import java.util.List;

import com.ss.pojo.MultiDynamicActivity;

public interface IMultiDynamicActivityDAO {
	int deleteByPrimaryKey(Integer mdid);

	int insert(MultiDynamicActivity record);

	int insertSelective(MultiDynamicActivity record);

	MultiDynamicActivity selectByPrimaryKey(Integer mdid);

	List<MultiDynamicActivity> selectByBUID(Integer buid);
	
	List<MultiDynamicActivity> selectAllWithRelationShip(Integer buid);

	int updateByPrimaryKeySelective(MultiDynamicActivity record);

	int updateByPrimaryKey(MultiDynamicActivity record);
}