package com.ss.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ImageComment {
	private Integer icid;

	private Date uploadtime;

	private String ipos;

	public String getIpos() {
		return ipos;
	}

	public void setIpos(String ipos) {
		this.ipos = ipos;
	}

	private String imga;

	private String imgb;

	private String imgc;

	private String imgd;

	private String imge;

	private String imgf;

	@JsonIgnore
	private Integer buid;
	
	private Baseinfo_Sample baseinfosample;

	public Baseinfo_Sample getBaseinfosample() {
		return baseinfosample;
	}

	public void setBaseinfosample(Baseinfo_Sample sample) {
		this.baseinfosample = sample;
	}

	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	private int zan;

	public int getZan() {
		return zan;
	}

	public void setZan(int zan) {
		this.zan = zan;
	}

	private Integer ispass;

	public Integer getIcid() {
		return icid;
	}

	public void setIcid(Integer icid) {
		this.icid = icid;
	}

	public Date getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}

	public String getImga() {
		return imga;
	}

	public void setImga(String imga) {
		this.imga = imga == null ? null : imga.trim();
	}

	public String getImgb() {
		return imgb;
	}

	public void setImgb(String imgb) {
		this.imgb = imgb == null ? null : imgb.trim();
	}

	public String getImgc() {
		return imgc;
	}

	public void setImgc(String imgc) {
		this.imgc = imgc == null ? null : imgc.trim();
	}

	public String getImgd() {
		return imgd;
	}

	public void setImgd(String imgd) {
		this.imgd = imgd == null ? null : imgd.trim();
	}

	public String getImge() {
		return imge;
	}

	public void setImge(String imge) {
		this.imge = imge == null ? null : imge.trim();
	}

	public String getImgf() {
		return imgf;
	}

	public void setImgf(String imgf) {
		this.imgf = imgf == null ? null : imgf.trim();
	}

	public Integer getBuid() {
		return buid;
	}

	public void setBuid(Integer buid) {
		this.buid = buid;
	}

	public Integer getIspass() {
		return ispass;
	}

	public void setIspass(Integer ispass) {
		this.ispass = ispass;
	}
}