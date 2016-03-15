package com.ss.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FollowImageComment {
    private Integer fid;

    private Integer icid;

    @JsonIgnore
    private Integer buid;
    
    private Baseinfo_Sample baseinfosample;
    
    
    public Baseinfo_Sample getBaseinfosample() {
		return baseinfosample;
	}

	public void setBaseinfosample(Baseinfo_Sample baseinfosample) {
		this.baseinfosample = baseinfosample;
	}

	private String comment;

    private Date uploadtime;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getIcid() {
        return icid;
    }

    public void setIcid(Integer icid) {
        this.icid = icid;
    }

    public Integer getBuid() {
        return buid;
    }

    public void setBuid(Integer buid) {
        this.buid = buid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }
}