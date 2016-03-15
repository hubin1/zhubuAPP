package com.ss.dao;

import java.util.List;

import com.ss.pojo.FollowImageComment;

public interface IFollowImageCommentDAO {
    int deleteByPrimaryKey(Integer fid);

    int insert(FollowImageComment record);

    int insertSelective(FollowImageComment record);

    FollowImageComment selectByPrimaryKey(Integer fid);
    
    List<FollowImageComment> selectByIcid(Integer icid);

    int updateByPrimaryKeySelective(FollowImageComment record);

    int updateByPrimaryKey(FollowImageComment record);
}