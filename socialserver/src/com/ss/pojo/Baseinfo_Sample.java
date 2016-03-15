package com.ss.pojo;

/**
 * 用户信息的 简易模型 作用在于 需要的时候 替代 繁琐且敏感的 Baseinfo信息模型
 * 
 * @author louis
 *
 */
public class Baseinfo_Sample {
	public int getBuid() {
		return buid;
	}

	public void setBuid(int buid) {
		this.buid = buid;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	private int buid;
	private String nick;
	private String iname;
	private String ipos;

	public String getIname() {
		return iname;
	}

	public void setIname(String iname) {
		this.iname = iname;
	}

	public String getIpos() {
		return ipos;
	}

	public void setIpos(String ipos) {
		this.ipos = ipos;
	}
}
