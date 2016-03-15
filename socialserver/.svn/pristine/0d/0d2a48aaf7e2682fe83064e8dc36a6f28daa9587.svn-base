
package com.ss.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ss.dao.IOrganization_ActivityDAO;
import com.ss.pojo.Mass_organization_activity;
import com.ss.service.ActivityService;

/**
 * [活动]
 * [功能详细描述]
 * @author hmm
 * @version 1.0, 2016年1月16日
 * @see
 * @since zhubu-V1001
 */
@Service("activityServer")
public class ActivityServiceImpl implements ActivityService
{

    @Resource
    private IOrganization_ActivityDAO activityMapper;

    @Override
    public List<Mass_organization_activity> getAllByAct(Mass_organization_activity mass_organization_activity)
    {
        return activityMapper.selectAllByActivity(mass_organization_activity);
    }

    @Override
    public Mass_organization_activity insertWithAct(Mass_organization_activity mass_organization_activity)
    {
        Integer moauid = activityMapper.insertActSelective(mass_organization_activity);
        mass_organization_activity.setMoauid(moauid);
        return mass_organization_activity;
    }

    @Override
    public int updateWithAct(Mass_organization_activity mass_organization_activity)
    {
        return activityMapper.updateByActSelective(mass_organization_activity);
    }

    @Override
    public Integer getCount()
    {
        return activityMapper.selectCount();
    }

    @Override
    public List<Map<Object, Object>> getAllByActList(Mass_organization_activity mass_organization_activity)
    {
        return activityMapper.selectAllByActivityList(mass_organization_activity);
    }

}
