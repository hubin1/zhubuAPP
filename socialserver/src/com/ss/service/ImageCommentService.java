package com.ss.service;

import java.util.Map;

import com.ss.pojo.ImageComment;

public interface ImageCommentService {
	public int save(ImageComment comm);

	public int update4zan_add(int zid);
	
	public int update4zan_remove(int zid);
	
	public Map<String, Object> getAll(int typeA, int typeB, int pageNum);
	
	public Map<String, Object> approximate(int typeA, int typeB, int pageNum, String title);
}
