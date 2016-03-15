package com.ss.dao;

import java.util.List;

import com.ss.pojo.VideoComment;

public interface IVideoCommentDAO {
    int deleteByPrimaryKey(Integer vid);

    int insert(VideoComment record);

    int insertSelective(VideoComment record);

    VideoComment selectByPrimaryKey(Integer vid);
    
    List<VideoComment> select();

    int updateByPrimaryKeySelective(VideoComment record);

    int updateByPrimaryKey(VideoComment record);
    
    int update4zan(VideoComment record);
    
    List<VideoComment> selectByType(int type);
    
    List<VideoComment> selectContainsImage();
    
}