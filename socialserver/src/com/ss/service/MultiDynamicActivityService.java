package com.ss.service;

import java.util.Map;

import com.ss.pojo.MultiDynamicActivity;

/**
 * 
 * 
 * @author louis
 *
 */
public interface MultiDynamicActivityService {
	//public int saveActivity(MultiDynamicActivity act);
	
	public Map<String, Object> getActivities(int buid, int page);
	
	public Map<String, Object> getFriendsActivities(int buid, int page);
	
	public int deleteActivity(MultiDynamicActivity record);
}
