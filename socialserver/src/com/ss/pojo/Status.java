package com.ss.pojo;

/**
 * 用于返回状态的对象
 * @author louis
 *
 */
public class Status {
	
	private String key;   // 暂时 固定为 status
	private String value;  // 根据不同的请求配置
	private String token;  // 会话 token 的携带信息
	private Object everything; // 携带任何可以返回的信息
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Status(String key, String value,String token) {
		this.key = key;
		this.value = value;
		this.token = token;
	}
	public Status() {
	}
	
	public Status(String key, String value) {
		this(key, value, null);
	}
	
	public Status(String key, String value, Object everything){
		this(key, value, null, everything);
	}
	
	public Status(String key, String value, String token, Object everything) {
		this.key = key;
		this.value = value;
		this.token = token;
		this.everything = everything;
	}
	
	public Object getEverything() {
		return everything;
	}
	public void setEverything(Object everything) {
		this.everything = everything;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
