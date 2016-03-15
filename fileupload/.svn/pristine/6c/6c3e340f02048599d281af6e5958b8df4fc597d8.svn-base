package com.fp.pojo;

/**
 * 上传和下载
 * 
 * @author louis
 *
 */
public class Status {
	// status: 1 成功 2 失败 3 文件类型不支持 4 文件过大
	// 成功 即可获得其余 2个参数， 失败 则 不能获取 其余 2个参数
	private int status;
	private String pos;
	private String[] names;

	public Status(int status, String pos, String[] names) {
		this.status = status;
		this.pos = pos;
		this.names = names;
	}

	public Status(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getNames() {
		String s = "";
		for (String one : this.names) {
			s = s + "," + one;
		}
		if(s.isEmpty()){
			return s;
		}else{
			return s.substring(1);
		}
		
	}

	public void setNames(String[] names) {
		this.names = names;
	}
}
