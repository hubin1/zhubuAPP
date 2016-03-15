
package com.ss.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ss.pojo.Baseinfo;
import com.ss.pojo.Group_membership;
import com.ss.pojo.Status;
import com.ss.service.GroupMemberShipService;
import com.ss.util.SessionUserUtil;
import com.ss.util.StringUtils;

/**
 * [社团与人员关系]<p>
 * [功能详细描述]<p>
 * @author zzb
 * @version 1.0, 2016年1月18日
 * @see
 * @since zhubu-V1001
 */
@RequestMapping(value = "/groupNumber")
@Controller
public class GroupMemberShipController
{
    @Autowired
    private GroupMemberShipService groupMemberShipService;

    @RequestMapping(value = "/findGroupNumber", method = RequestMethod.POST)
    @ResponseBody
    public List<Group_membership> findGroupNumber(@ModelAttribute Group_membership membership , HttpSession session)
            throws RuntimeException
    {
        SessionUserUtil.getUserBySession(session);
        List<Group_membership> list = groupMemberShipService.selectMember(membership);
        return list;
    }

    @RequestMapping(value = "/addGroupNumber", method = RequestMethod.POST)
    @ResponseBody
    public Status addGroupNumber(@ModelAttribute Group_membership membership , HttpSession session)
            throws RuntimeException
    {
        Baseinfo original = SessionUserUtil.getUserBySession(session);
        Integer buid = original.getBuid();
        membership.setBuid(buid);
        groupMemberShipService.insertMember(membership);
        return new Status("status", "1", "");
    }

    @RequestMapping(value = "/addGroupNumberBatch", params = { "parameters" }, method = RequestMethod.POST)
    @ResponseBody
    public Status addGroupNumberBatch(@RequestParam(value = "parameters") String parameters , HttpSession session)
            throws RuntimeException
    {
        SessionUserUtil.getUserBySession(session);
        if (null == parameters)
        {
            return new Status("status", "0", "");
        }
        String[] strs = parameters.split(",");
        if (strs.length > 0)
        {
            List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < strs.length; i++)
            {
                String[] params = strs[i].split("#");
                Map<String, Object> map = new HashMap<>();
                map.put("buid", params[0]);
                map.put("omuid", params[1]);
                map.put("administrator", StringUtils.groupmanager);
                map.put("state", StringUtils.orgmumstate_0);
                ls.add(map);
            }
            groupMemberShipService.insertMemberBatch(ls);
        }
        else
        {
            return new Status("status", "0", "");
        }
        return new Status("status", "200", "");
    }

    @RequestMapping(value = "/updateGroupNumber", method = RequestMethod.POST)
    @ResponseBody
    public Status updateGroupNumber(@ModelAttribute Group_membership membership , HttpSession session)
            throws RuntimeException
    {
        SessionUserUtil.getUserBySession(session);
        groupMemberShipService.updateMember(membership);
        return new Status("status", "200", "");
    }

    @RequestMapping(value = "/deleteGroupNumber", params = { "ids", "omuid" }, method = RequestMethod.POST)
    @ResponseBody
    public Status deleteGroupNumber(@RequestParam(value = "ids") String ids ,
            @RequestParam(value = "omuid") Integer omuid , HttpSession session) throws RuntimeException
    {
        Baseinfo baseinfo = SessionUserUtil.getUserBySession(session);
        Integer buid = baseinfo.getBuid();
        if (null == ids || null == omuid)
        {
            return new Status("status", "0", "");
        }
        Group_membership membership = new Group_membership();
        membership.setBuid(buid);
        membership.setOmuid(omuid);
        List<Group_membership> list = groupMemberShipService.selectMember(membership);
        if (list.size() > 0)
        {
            membership = list.get(0);
            Byte b = Byte.valueOf(StringUtils.administrator_0);
            if (!b.equals(membership.getAdministrator()))
            {
                String[] strs = ids.split(",");
                if (strs.length > 0)
                {
                    List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();
                    for (int i = 0; i < strs.length; i++)
                    {
                        Map<String, Object> maps = new HashMap<>();
                        maps.put("id", Integer.valueOf(strs[i]));
                        ls.add(maps);
                    }
                    groupMemberShipService.deleteMember(ls);
                    return new Status("status", "200", "");
                }
                else
                {
                    return new Status("status", "0", "");
                }

            }
            else
            {
                return new Status("status", "0", "");
            }

        }
        else
        {
            return new Status("status", "0", "");
        }
    }
}
