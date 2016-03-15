
package com.ss.pojo;

import java.util.Date;

/**
 * [互动点赞关系表实体类]<p>
 * [功能详细描述]<p>
 * @author zzb
 * @version 1.0, 2016年1月16日
 * @see
 * @since zhubu-V1001
 */
public class Eval_group_user
{
    /**
     * 主键
     */
    private Integer id;

    /**
     * 活动id/外键
     */
    private Integer moauid;

    /**
     * 人员id/外键
     */
    private Integer buid;

    /**
     * 点赞时间
     */
    private Date time;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getMoauid()
    {
        return moauid;
    }

    public void setMoauid(Integer moauid)
    {
        this.moauid = moauid;
    }

    public Integer getBuid()
    {
        return buid;
    }

    public void setBuid(Integer buid)
    {
        this.buid = buid;
    }

    public Date getTime()
    {
        return time;
    }

    public void setTime(Date time)
    {
        this.time = time;
    }

}
