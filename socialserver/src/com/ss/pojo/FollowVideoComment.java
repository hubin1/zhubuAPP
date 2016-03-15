package com.ss.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FollowVideoComment {
    private Integer fvid;

    private Integer vid;
    
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

    public Integer getFvid() {
        return fvid;
    }

    public void setFvid(Integer fvid) {
        this.fvid = fvid;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
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