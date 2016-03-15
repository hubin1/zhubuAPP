package com.fp.pojo;

public class VStatus {
	public VStatus(int status) {
		super();
		this.status = status;
	}

	// status: 1 成功 2 失败 3 文件类型不支持 4 文件过大
	// 成功 即可获得其余 2个参数， 失败 则 不能获取 其余 2个参数
	private int status;

	private String videopath;
	private int vsuffix;

	private String imagepath;
	private String imagename;

	public VStatus(int status, String videopath, int vsuffix, String imagepath, String imagename) {
		this.status = status;
		this.videopath = videopath;
		this.vsuffix = vsuffix;
		this.imagepath = imagepath;
		this.imagename = imagename;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getVideopath() {
		return videopath;
	}

	public void setVideopath(String videopath) {
		this.videopath = videopath;
	}

	public int getVsuffix() {
		return vsuffix;
	}

	public void setVsuffix(int vsuffix) {
		this.vsuffix = vsuffix;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
}
