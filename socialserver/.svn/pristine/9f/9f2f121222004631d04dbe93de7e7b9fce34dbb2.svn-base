package com.ss.util;

import com.ss.pojo.Baseinfo;
import com.ss.pojo.Baseinfo_Sample;

/**
 * 专门用于将敏感对象简化
 * @author louis
 *
 */
abstract public class ShrinkObject {
	
	public static Baseinfo_Sample shrinkBaseInfo(Baseinfo base){
		Baseinfo_Sample sample = new Baseinfo_Sample();
		sample.setBuid(base.getBuid());
		sample.setNick(base.getNick());
		sample.setIname(base.getIcon());
		sample.setIpos(base.getIpos());	
		return sample;
	}
}
