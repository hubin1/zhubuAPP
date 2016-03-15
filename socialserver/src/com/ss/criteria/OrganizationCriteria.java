
package com.ss.criteria;

/**
 * [社团搜索类]<p>
 * [功能详细描述]<p>
 * @author zzb
 * @version 1.0, 2016年1月19日
 * @see
 * @since zhubu-V1001
 */
public class OrganizationCriteria extends AbstractQueryCriteria
{
    /**
     * 是否为管理员 0 不是 1 是
     */
    private Byte administrator;

    /**
     * 0正式成员，1申请加入成员 2关注人员
     */
    private Byte mumState;

    public Byte getAdministrator()
    {
        return administrator;
    }

    public void setAdministrator(Byte administrator)
    {
        this.administrator = administrator;
    }

    public Byte getMumState()
    {
        return mumState;
    }

    public void setMumState(Byte mumState)
    {
        this.mumState = mumState;
    }

}
