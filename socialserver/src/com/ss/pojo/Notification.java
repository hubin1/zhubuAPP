package com.ss.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author louis
 *
 */
public class Notification {
    private Integer nid;

    private Date ntime;

    private Integer reason;

    @JsonIgnore
    private Integer buid;

    private Integer type;

    private Integer refid;

    private String msg;

    private String filla;

    private String fillb;

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public Date getNtime() {
        return ntime;
    }

    public void setNtime(Date ntime) {
        this.ntime = ntime;
    }

    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public String getFilla() {
        return filla;
    }

    public void setFilla(String filla) {
        this.filla = filla == null ? null : filla.trim();
    }

    public String getFillb() {
        return fillb;
    }

    public void setFillb(String fillb) {
        this.fillb = fillb == null ? null : fillb.trim();
    }
}