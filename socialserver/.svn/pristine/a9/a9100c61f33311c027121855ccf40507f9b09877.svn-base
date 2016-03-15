
package com.ss.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ss.dao.IGroupMemberShipDao;
import com.ss.dao.IOrganizationDao;
import com.ss.pojo.Group_membership;
import com.ss.pojo.Mass_organization;
import com.ss.service.OrganizationService;
import com.ss.util.StringUtils;

/**
 * [社团]<p>
 * [功能详细描述]<p>
 * @author zzb
 * @version 1.0, 2016年1月16日
 * @see
 * @since zhubu-V1001
 */
@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService
{
    @Resource
    private IOrganizationDao organizationMapper;

    @Resource
    private IGroupMemberShipDao groupMemberShipMapper;

    @Override
    public List<Mass_organization> getAllByOrg(Mass_organization mass_organization)
    {
        return organizationMapper.selectAllByOrg(mass_organization);
    }

    /**
     * 使用事务，创建社团后插入创建者信息
     */
    @Override
    @Transactional(value="transactionManager",rollbackFor=java.lang.Exception.class)
    public Mass_organization insertWithOrg(Mass_organization mass_organization)
    {
        this.organizationMapper.insertOrgSelective(mass_organization);
        Integer omuid=mass_organization.getOmuid();
        mass_organization.setOmuid(omuid);
        Group_membership group_membership=new Group_membership();
        group_membership.setAdministrator(Byte.valueOf(StringUtils.groupmanager));
        group_membership.setOmuid(omuid);
        group_membership.setBuid(mass_organization.getBuid());
        group_membership.setState(Byte.valueOf(StringUtils.orgmumstate_0));
        groupMemberShipMapper.insertMember(group_membership);
        return mass_organization;
    }

    @Override
    public int updateWithOrg(Mass_organization mass_organization)
    {
        return organizationMapper.updateByOmuidSelective(mass_organization);
    }

    @Override
    public Integer getCount()
    {
        return organizationMapper.selectCount();
    }

    @Override
    public List<Mass_organization> getAllByOrgAndmumber(Mass_organization mass_organization)
    {
        return organizationMapper.selectAllByOrgAndMember(mass_organization);
    }

}
