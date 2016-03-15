package com.ss.service;

import com.ss.pojo.Baseinfo_Detail;

public interface Baseinfo_DetailService {
	int updateDetail(Baseinfo_Detail record);
	
	Baseinfo_Detail getDetailByBuid(int buid);
}
