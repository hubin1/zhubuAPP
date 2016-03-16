package com.org.inspection.server.info;

/**
 * 用于返回状态的对象
 * @author louis
 *
 */
public class Status {
	
	private String stas;   //状态 
	private String value;  // 原因
	private Object everything; // 携带任何可以返回的信息
	
	
	
	public String getStas() {
		return stas;
	}
	public void setStas(String stas) {
		this.stas = stas;
	}
	public Status(String stas, String value, Object even) {
		this.stas = stas;
		this.value = value;
		this.everything = even;
	}
	public Status() {
	}
	
	public Status(String stas, String value) {
		this(stas, value, null);
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
