
package com.ss.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ss.pojo.Baseinfo;
import com.ss.pojo.Mass_organization_activity;
import com.ss.pojo.Status;
import com.ss.service.ActivityService;
import com.ss.util.SessionUserUtil;

/**
 * [活动]<p>
 * [功能详细描述]<p>
 * @author hmm
 * @version 1.0, 2016年1月16日
 * @see
 * @since zhubu-V1001
 */
@RequestMapping(value = "/act")
@Controller
public class ActivityController
{
    @Autowired
    private ActivityService activityService;

    /**
     * 查寻活动信息 详情
     * @param session
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/finAct", method = RequestMethod.POST)
    @ResponseBody
    public List<Mass_organization_activity> finAct(
            @ModelAttribute Mass_organization_activity mass_organization_activity , HttpSession session)
                    throws RuntimeException
    {
        SessionUserUtil.getUserBySession(session);
        List<Mass_organization_activity> mass_organization_activitys = activityService
                .getAllByAct(mass_organization_activity);
        return mass_organization_activitys;
    }

    /**
     * 查寻活动信息 列表
     * @param mass_organization_activity
     * @param session
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/finActList", method = RequestMethod.POST)
    @ResponseBody
    public List<Map<Object, Object>> finActList(@ModelAttribute Mass_organization_activity mass_organization_activity ,
            HttpSession session) throws RuntimeException
    {
        SessionUserUtil.getUserBySession(session);
        List<Map<Object, Object>> mass_organization_activitys = activityService
                .getAllByActList(mass_organization_activity);
        return mass_organization_activitys;
    }

    /**
     * 添加活动信息
     * @param mass_organization_activity 活动实体
     * @param session
     * @return 500：添加失败 200：添加成功
     */
    @RequestMapping(value = "/addAct", method = RequestMethod.POST)
    @ResponseBody
    public Status addAct(@ModelAttribute Mass_organization_activity mass_organization_activity , HttpSession session)
            throws RuntimeException
    {
        Baseinfo original = SessionUserUtil.getUserBySession(session);
        Integer buid = original.getBuid();
        mass_organization_activity.setUid(buid);
        activityService.insertWithAct(mass_organization_activity);
        return new Status("status", "200", null);
    }

    /**
     * 更新活动信息
     * @param mass_organization_activity 活动实体
     * @param session
     * @return
     */
    @RequestMapping(value = "/updateAct", method = RequestMethod.POST)
    @ResponseBody
    public Status updateAct(@ModelAttribute Mass_organization_activity mass_organization_activity , HttpSession session)
    {
        Baseinfo original = SessionUserUtil.getUserBySession(session);
        Integer buid = original.getBuid();
        mass_organization_activity.setUid(buid);
        activityService.updateWithAct(mass_organization_activity);
        return new Status("status", "200", null);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
