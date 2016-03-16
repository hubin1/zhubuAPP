package com.org.inspection.util;

import org.codehaus.jackson.map.ObjectMapper;

import com.org.inspection.server.info.Status;

public class Json2ObjectUtil {

	public static String status2Json(Status st) throws Exception {
		ObjectMapper mapper = new ObjectMapper();  
        String json =  mapper.writeValueAsString(st);
        return json;
	}
}
