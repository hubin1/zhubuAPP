package com.org.inspection.util;

import java.util.Map;

public class MapToStringUtil {
	
	public static String map2String(Map<String, String> map){
		String s = "";
		for(Object o : map.keySet()){
			s += "key=" + o + " value=" + map.get(o) + "&&";
		}
		return s;
	}
}
