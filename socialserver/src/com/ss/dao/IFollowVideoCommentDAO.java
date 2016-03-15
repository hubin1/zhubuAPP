package com.ss.dao;

import java.util.List;

import com.ss.pojo.FollowVideoComment;

public interface IFollowVideoCommentDAO {
    int deleteByPrimaryKey(Integer fvid);

    int insert(FollowVideoComment record);

    int insertSelective(FollowVideoComment record);

    FollowVideoComment selectByPrimaryKey(Integer fvid);
    
    List<FollowVideoComment> selectByVid(Integer vid);

    int updateByPrimaryKeySelective(FollowVideoComment record);

    int updateByPrimaryKey(FollowVideoComment record);
}