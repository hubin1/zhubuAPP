package com.ss.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 热门信息的 内容
 * @author Administrator
 *
 */
public class HotList {
    private Integer hid;
    
    @JsonIgnore
    private Integer buid;
    
    @JsonIgnore
    private Integer vid;

    private Long review;

    private Date confirmtime;
    
    // 视频信息
    private VideoComment video;
    
    // 获取的条数
    private int totol;

    public int getTotol() {
		return totol;
	}

	public void setTotol(int totol) {
		this.totol = totol;
	}

	public VideoComment getVideo() {
		return video;
	}

	public void setVideo(VideoComment video) {
		this.video = video;
	}

	public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public Integer getBuid() {
        return buid;
    }

    public void setBuid(Integer buid) {
        this.buid = buid;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Long getReview() {
        return review;
    }

    public void setReview(Long review) {
        this.review = review;
    }

    public Date getConfirmtime() {
        return confirmtime;
    }

    public void setConfirmtime(Date confirmtime) {
        this.confirmtime = confirmtime;
    }
}