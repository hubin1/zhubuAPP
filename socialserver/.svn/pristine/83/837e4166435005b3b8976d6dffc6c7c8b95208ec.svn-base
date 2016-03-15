
package com.ss.service;

import java.util.List;

import com.ss.pojo.Mass_organization;

/**
 * [社团]<p>
 * [功能详细描述]<p>
 * @author zzb
 * @version 1.0, 2016年1月16日
 * @see
 * @since zhubu-V1001
 */
public interface OrganizationService
{
    /**
     * 查询当前用户下的所有社团信息,不关联社团人员
     * @param mass_organization
     * @return
     */
    public List<Mass_organization> getAllByOrg(Mass_organization mass_organization);
    /**
     * 查询当前用户下的所有社团信息,关联社团人员
     * @param mass_organization
     * @return
     */
    public List<Mass_organization> getAllByOrgAndmumber(Mass_organization mass_organization);
    /**
     * 查询总数
     * @return
     */
    public Integer getCount();

    /**
     * 插入社团信息
     * @param mass_organization
     * @return 插入的社团主键
     */
    public Mass_organization insertWithOrg(Mass_organization mass_organization);

    /**
     * 更新社团信息
     * @param mass_organization
     * @return
     */
    public int updateWithOrg(Mass_organization mass_organization);
}
