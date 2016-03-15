
package com.ss.dao;

import java.util.List;
import java.util.Map;

import com.ss.pojo.Group_membership;

/**
 * [社团人员关系]<p>
 * [功能详细描述]<p>
 * @author zzb
 * @version 1.0, 2016年1月19日
 * @see
 * @since zhubu-V1001
 */
public interface IGroupMemberShipDao
{
    /**
     * 查找社团人员
     * @param group_membership
     * @return
     */
    List<Group_membership> selectMember(Group_membership group_membership);

    /**
     * 添加人员
     * @param group_membership
     * @return
     */
    int insertMember(Group_membership group_membership);

    /**
     * 批量添加人员
     * @param ls
     * @return
     */
    int insertMemberBatch(List<Map<String, Object>> ls);

    /**
     * 更新社团人员
     * @param group_membership
     * @return
     */
    int updateMember(Group_membership group_membership);

    /**
     * 删除社团人员
     * @param buids 关系表主键id
     * @return
     */
    int deleteMember(List<Map<String, Object>> ls);
}
