package com.ss.service;

import java.util.Map;

import com.ss.pojo.FollowImageComment;

public interface FollowImageCommentService {
	public Map<String, Object> getImageCommentWithPage(int icid, int page);
	
	public int save(FollowImageComment comment);
}
