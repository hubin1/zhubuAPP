package com.ss.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class VideoComment {
    private Integer vid;

    private String iname;

    private String vpos;
    
    private String ipos;
    
    private Integer vsuffix;
    
    private String title;
    
    private Integer type;
    
    private String url;
    
    
    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public Integer getVsuffix() {
		return vsuffix;
	}

	public void setVsuffix(Integer vsuffix) {
		this.vsuffix = vsuffix;
	}

	public void setIname(String iname) {
		this.iname = iname;
	}

	private int zan;

    public int getZan() {
		return zan;
	}

	public void setZan(int zan) {
		this.zan = zan;
	}

	public String getIpos() {
		return ipos;
	}

	public void setIpos(String ipos) {
		this.ipos = ipos;
	}

	private String comment;
	
	@JsonIgnore
    private Integer buid;
	
	private Baseinfo_Sample baseinfosample;

	public Baseinfo_Sample getBaseinfosample() {
		return baseinfosample;
	}

	public void setBaseinfosample(Baseinfo_Sample sample) {
		this.baseinfosample = sample;
	}

	private Date uploadtime;

    @JsonIgnore
    private Integer ispass;

    private String temp;

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public String getIname() {
        return this.iname;
    }

    public void setVname(String iname) {
        this.iname = iname == null ? null : iname.trim();
    }

    public String getVpos() {
        return vpos;
    }

    public void setVpos(String vpos) {
        this.vpos = vpos == null ? null : vpos.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getBuid() {
        return buid;
    }

    public void setBuid(Integer buid) {
        this.buid = buid;
    }

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    public Integer getIspass() {
        return ispass;
    }

    public void setIspass(Integer ispass) {
        this.ispass = ispass;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp == null ? null : temp.trim();
    }
}