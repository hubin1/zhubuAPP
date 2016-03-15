package com.ss.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MultiDynamicActivity {
    private Integer mdid;

    private Integer buid;

    private Integer type;

    @JsonIgnore
    private Integer refid;
    
    private Object content;
    
    

    public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	private Date uploadtime;

	@JsonIgnore
    private String domain;

	@JsonIgnore
    private Integer filla;

	@JsonIgnore
    private Integer fillb;

	@JsonIgnore
    private Integer fillc;

    private Date updatetime;

    public Integer getMdid() {
        return mdid;
    }

    public void setMdid(Integer mdid) {
        this.mdid = mdid;
    }

    public Integer getBuid() {
        return buid;
    }

    public void setBuid(Integer buid) {
        this.buid = buid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getRefid() {
        return refid;
    }

    public void setRefid(Integer refid) {
        this.refid = refid;
    }

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

//    public String getComment() {
//        return comment;
//    }
//
//    public void setComment(String comment) {
//        this.comment = comment == null ? null : comment.trim();
//    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    public Integer getFilla() {
        return filla;
    }

    public void setFilla(Integer filla) {
        this.filla = filla;
    }

    public Integer getFillb() {
        return fillb;
    }

    public void setFillb(Integer fillb) {
        this.fillb = fillb;
    }

    public Integer getFillc() {
        return fillc;
    }

    public void setFillc(Integer fillc) {
        this.fillc = fillc;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}