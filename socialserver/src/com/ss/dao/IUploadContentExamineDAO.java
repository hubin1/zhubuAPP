package com.ss.dao;

import com.ss.pojo.UploadContentExamine;

public interface IUploadContentExamineDAO {
    int deleteByPrimaryKey(Integer ceid);

    int insert(UploadContentExamine record);

    int insertSelective(UploadContentExamine record);

    UploadContentExamine selectByPrimaryKey(Integer ceid);

    int updateByPrimaryKeySelective(UploadContentExamine record);

    int updateByPrimaryKey(UploadContentExamine record);
}