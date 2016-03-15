
package com.ss.pojo;

import java.util.Date;

import com.ss.criteria.CommonPage;

/**
 * [社团活动实体类]<p>
 * [功能详细描述]<p>
 * @author zzb
 * @version 1.0, 2016年1月16日
 * @see
 * @since zhubu-V1001
 */
public class Mass_organization_activity extends CommonPage
{
    /**
     * 社团活动id
     */
    private Integer moauid;

    /**
     * 创建人id
     */
    private Integer uid;

    /**
     * 社团id
     */
    private Integer omuid;

    /**
     * 活动名称
     */
    private String moname;

    /**
     * 地点
     */
    private String place;

    /**
     * 开始时间
     */
    private Date stime;

    /**
     * 结束时间
     */
    private Date etime;

    /**
     * 海报地址
     */
    private Integer refid;

    /**
     * 类型 0个人活动 1社团活动
     */
    private Byte type;

    /**
     * 0审核失败 1审核成功 2 审核中
     */
    private Byte state;

    /**
     * 活动类别 0其他 1交友聚会2音乐戏剧 3体育活动 4户外旅行 5讲座公益 6789
     */
    private Byte actionid;

    /**
     * 活动介绍
     */
    private String introduce;

    /**
     * 删除标识
     */
    private Byte delstate;

    /**
     * 点赞总数
     */
    private Integer evalnum;

    public Byte getDelstate()
    {
        return delstate;
    }

    public void setDelstate(Byte delstate)
    {
        this.delstate = delstate;
    }

    public Integer getEvalnum()
    {
        return evalnum;
    }

    public void setEvalnum(Integer evalnum)
    {
        this.evalnum = evalnum;
    }

    public Integer getMoauid()
    {
        return moauid;
    }

    public void setMoauid(Integer moauid)
    {
        this.moauid = moauid;
    }

    public Integer getUid()
    {
        return uid;
    }

    public void setUid(Integer uid)
    {
        this.uid = uid;
    }

    public Integer getOmuid()
    {
        return omuid;
    }

    public void setOmuid(Integer omuid)
    {
        this.omuid = omuid;
    }

    public String getPlace()
    {
        return place;
    }

    public void setPlace(String place)
    {
        this.place = place;
    }

    public Date getStime()
    {
        return stime;
    }

    public void setStime(Date stime)
    {
        this.stime = stime;
    }

    public Date getEtime()
    {
        return etime;
    }

    public void setEtime(Date etime)
    {
        this.etime = etime;
    }

    public Integer getRefid()
    {
        return refid;
    }

    public void setRefid(Integer refid)
    {
        this.refid = refid;
    }

    public Byte getType()
    {
        return type;
    }

    public void setType(Byte type)
    {
        this.type = type;
    }

    public Byte getState()
    {
        return state;
    }

    public void setState(Byte state)
    {
        this.state = state;
    }

    public Byte getActionid()
    {
        return actionid;
    }

    public void setActionid(Byte actionid)
    {
        this.actionid = actionid;
    }

    public String getIntroduce()
    {
        return introduce;
    }

    public void setIntroduce(String introduce)
    {
        this.introduce = introduce;
    }

    public String getMoname()
    {
        return moname;
    }

    public void setMoname(String moname)
    {
        this.moname = moname;
    }

}
