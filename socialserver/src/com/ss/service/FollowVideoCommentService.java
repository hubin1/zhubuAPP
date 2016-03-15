package com.ss.service;

import java.util.Map;

import com.ss.pojo.FollowVideoComment;

public interface FollowVideoCommentService {
	public Map<String, Object> getVideoCommentWithPage(int vid, int page);
	public int save(FollowVideoComment comment);
}
