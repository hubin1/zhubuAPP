package com.ss.util;

abstract public class CreateValidateCode {
	
	public static String create4BitValidateCode(){
		String code = String.valueOf((int) (Math.random() * 10000)+1000);
		// 验证码 不足 4位 用0 补齐
		code = code.length() < 4 ? code + "0" : code;
		
		if(code.length() == 5){
			code = code.substring(0, 4);
		}
		
		return code;
	}
}
