
package com.ss.dao;

import java.util.List;
import java.util.Map;

import com.ss.pojo.Mass_organization_activity;

public interface IOrganization_ActivityDAO
{
    /**
     * 查询总数
     * @return
     */
    int selectCount();

    /**
     * 根据用户id查询所属社团
     * @param mass_organization
     * @return
     */
    List<Mass_organization_activity> selectAllByActivity(Mass_organization_activity mass_organization_activity);

    /**
     * 查询社团列表
     * @param mass_organization
     * @return
     */
    List<Map<Object, Object>> selectAllByActivityList(Mass_organization_activity mass_organization_activity);

    /**
     * 更新社团，不更新的字段保持空
     * @param mass_organization
     * @return
     */
    int updateByActSelective(Mass_organization_activity mass_organization_activity);

    /**
     * 插入社团,不更新的字段保持空,不为空则报错
     * @param mass_organization
     * @return
     */
    int insertActSelective(Mass_organization_activity mass_organization_activity);

}
