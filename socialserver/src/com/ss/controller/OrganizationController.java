
package com.ss.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ss.pojo.Mass_organization;
import com.ss.pojo.Status;
import com.ss.service.OrganizationService;
import com.ss.util.SessionUserUtil;
import com.ss.util.StringUtils;

/**
 * [社团]<p>
 * [功能详细描述]<p>
 * @author zzb
 * @version 1.0, 2016年1月16日
 * @see
 * @since zhubu-V1001
 */
@RequestMapping(value = "/org")
@Controller
public class OrganizationController
{
    @Autowired
    private OrganizationService organizationService;

    /**
     * 查找社团信息，不关联社团人员信息
     * @param session
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/finOrg", method = RequestMethod.POST)
    @ResponseBody
    public List<Mass_organization> finOrg(@ModelAttribute Mass_organization mass_organization , HttpSession session)
            throws RuntimeException
    {
        SessionUserUtil.getUserBySession(session);
        List<Mass_organization> mass_organizations = organizationService.getAllByOrg(mass_organization);
        //        Map<String, Object> map = new HashMap<String, Object>();
        //        Integer totalNumber = organizationServer.getCount();
        //        map.put("content", mass_organizations);
        //        map.put("totalNumber", totalNumber);
        return mass_organizations;
    }

    /**
     * 查找社团信息，关联社团人员信息
     * @param session
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/finOrgByMember", method = RequestMethod.POST)
    @ResponseBody
    public List<Mass_organization> finOrgByMember(@ModelAttribute Mass_organization mass_organization ,
            HttpSession session) throws RuntimeException
    {
        SessionUserUtil.getUserBySession(session);
        List<Mass_organization> mass_organizations = organizationService.getAllByOrgAndmumber(mass_organization);
        return mass_organizations;
    }

    /**
     * 添加社团信息
     * @param mass_organization 社团信息
     * @param session
     * @return 500：添加失败 200：添加成功
     */
    @RequestMapping(value = "/addOrg", method = RequestMethod.POST)
    @ResponseBody
    public Status addOrg(@ModelAttribute Mass_organization mass_organization , HttpServletRequest request ,
            HttpSession session) throws RuntimeException
    {
        SessionUserUtil.getUserBySession(session);
        UUID uuid = UUID.randomUUID();
        mass_organization.setOmuuid(String.valueOf(uuid));
        mass_organization.setCreatetime(new Date());
        mass_organization.setDelstate(Byte.valueOf(StringUtils.orgdelstate_0));
        mass_organization.setState(Byte.valueOf(StringUtils.orgstate_4));
        organizationService.insertWithOrg(mass_organization);
        return new Status("status", "200", "");
    }

    /**
     * 更新社团信息
     * @param mass_organization
     * @param session
     * @return
     */
    @RequestMapping(value = "/updateOrg", method = RequestMethod.POST)
    @ResponseBody
    public Status updateOrg(@ModelAttribute Mass_organization mass_organization , HttpSession session)
    {
        SessionUserUtil.getUserBySession(session);
        organizationService.updateWithOrg(mass_organization);
        return new Status("status", "200", "");
    }
}
