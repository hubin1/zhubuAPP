package com.ss.dao;

import com.ss.pojo.Baseinfo_Detail;

public interface IBaseinfo_DetailDAO {
    int deleteByPrimaryKey(Integer duid);

    int insert(Baseinfo_Detail record);

    int insertSelective(Baseinfo_Detail record);

    Baseinfo_Detail selectByPrimaryKey(Integer duid);
    
    Baseinfo_Detail selectByBUID(Integer buid);

    int updateByPrimaryKeySelective(Baseinfo_Detail record);

    int updateByPrimaryKey(Baseinfo_Detail record);
    
    int updateByBUID(Baseinfo_Detail record);
}