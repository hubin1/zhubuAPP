
package com.ss.pojo;

/**
 * [社团与个人关系]<p>
 * [功能详细描述]<p>
 * @author zzb
 * @version 1.0, 2016年1月16日
 * @see
 * @since zhubu-V1001
 */
public class Group_membership
{
    /**
     * 主键
     */
    private Integer id;

    /**
     * 个人id/外键
     */
    private Integer buid;

    /**
     * 社团id/外键
     */
    private Integer omuid;

    /**
     * 团名片
     */
    private String om_ud_name;

    /**
     * 是否为管理员 0 不是 1 是
     */
    private Byte administrator;

    /**
     * 0正式成员，1申请加入成员 2关注人员
     */
    private Byte state;

    //一下为人员基本信息
    private String icon;

    private String phone;

    private String name;

    private Byte sex;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getBuid()
    {
        return buid;
    }

    public void setBuid(Integer buid)
    {
        this.buid = buid;
    }

    public Integer getOmuid()
    {
        return omuid;
    }

    public void setOmuid(Integer omuid)
    {
        this.omuid = omuid;
    }

    public String getOm_ud_name()
    {
        return om_ud_name;
    }

    public void setOm_ud_name(String om_ud_name)
    {
        this.om_ud_name = om_ud_name;
    }

    public Byte getAdministrator()
    {
        return administrator;
    }

    public void setAdministrator(Byte administrator)
    {
        this.administrator = administrator;
    }

    public Byte getState()
    {
        return state;
    }

    public void setState(Byte state)
    {
        this.state = state;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Byte getSex()
    {
        return sex;
    }

    public void setSex(Byte sex)
    {
        this.sex = sex;
    }

}
