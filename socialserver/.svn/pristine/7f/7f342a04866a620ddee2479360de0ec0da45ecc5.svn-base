
package com.ss.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ss.dao.IGroupMemberShipDao;
import com.ss.pojo.Group_membership;
import com.ss.service.GroupMemberShipService;

/**
 * [社团人员关系]<p>
 * [功能详细描述]<p>
 * @author zzb
 * @version 1.0, 2016年1月19日
 * @see
 * @since zhubu-V1001
 */
@Service("groupMemberShipService")
public class GroupMemberShipServiceImpl implements GroupMemberShipService
{
    @Resource
    private IGroupMemberShipDao groupMemberShipMapper;

    @Override
    public int insertMember(Group_membership group_membership)
    {
        return groupMemberShipMapper.insertMember(group_membership);
    }

    @Override
    public int updateMember(Group_membership group_membership)
    {
        return groupMemberShipMapper.updateMember(group_membership);
    }

    @Override
    public int deleteMember(List<Map<String, Object>> ls)
    {
        return groupMemberShipMapper.deleteMember(ls);
    }

    @Override
    public List<Group_membership> selectMember(Group_membership group_membership)
    {
        return groupMemberShipMapper.selectMember(group_membership);
    }

    @Override
    public int insertMemberBatch(List<Map<String, Object>> ls)
    {
        return groupMemberShipMapper.insertMemberBatch(ls);
    }

}
