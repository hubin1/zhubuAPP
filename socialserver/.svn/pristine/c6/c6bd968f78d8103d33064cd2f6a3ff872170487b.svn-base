
package com.ss.dao;

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
public interface IOrganizationDao
{

    /**
     * 查询总数
     * @return
     */
    int selectCount();

    /**
     * 查询社团信息，不关联人员
     * @param mass_organization
     * @return
     */
    List<Mass_organization> selectAllByOrg(Mass_organization mass_organization);
    /**
     * 查询社团信息，关联人员
     * @param mass_organization
     * @return
     */
    List<Mass_organization> selectAllByOrgAndMember(Mass_organization mass_organization);

    /**
     * 更新社团，不更新的字段保持空
     * @param mass_organization
     * @return
     */
    int updateByOmuidSelective(Mass_organization mass_organization);

    /**
     * 插入社团,不更新的字段保持空,不为空则报错
     * @param mass_organization
     * @return
     */
    int insertOrgSelective(Mass_organization mass_organization);

}
