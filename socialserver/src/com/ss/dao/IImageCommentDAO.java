package com.ss.dao;

import java.util.List;

import com.ss.pojo.ImageComment;

public interface IImageCommentDAO {
	int deleteByPrimaryKey(Integer icid);

	int insert(ImageComment record);

	int insertSelective(ImageComment record);

	ImageComment selectByPrimaryKey(Integer icid);
	
	List<ImageComment> select();

	int updateByPrimaryKeySelective(ImageComment record);

	int updateByPrimaryKey(ImageComment record);

	int update4zan(ImageComment record);
}