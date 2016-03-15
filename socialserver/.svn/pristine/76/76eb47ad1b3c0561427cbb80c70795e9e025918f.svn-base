
package com.ss.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ss.pojo.Baseinfo;
import com.ss.pojo.Eval_group_user;
import com.ss.pojo.Status;
import com.ss.service.EvalGroupUserService;
import com.ss.util.SessionUserUtil;

/**
 * [点赞关系]<p>
 * [功能详细描述]<p>
 * @author zzb
 * @version 1.0, 2016年1月21日
 * @see
 * @since zhubu-V1001
 */
@RequestMapping(value = "/eval")
@Controller
public class EvalGroupUserController
{
    @Autowired
    private EvalGroupUserService evalGroupUserService;

    @RequestMapping(value = "/finEval", method = RequestMethod.POST)
    @ResponseBody
    public List<Eval_group_user> finEval(@ModelAttribute Eval_group_user eval_group_user , HttpSession session)
            throws RuntimeException
    {
        Baseinfo baseinfo = SessionUserUtil.getUserBySession(session);
        eval_group_user.setBuid(baseinfo.getBuid());
        List<Eval_group_user> eval_group_users = evalGroupUserService.selectEval(eval_group_user);
        return eval_group_users;
    }

    @RequestMapping(value = "/addEval", method = RequestMethod.POST)
    @ResponseBody
    public Status addEval(@ModelAttribute Eval_group_user eval_group_user , HttpSession session) throws RuntimeException
    {
        Baseinfo baseinfo = SessionUserUtil.getUserBySession(session);
        eval_group_user.setBuid(baseinfo.getBuid());
        eval_group_user.setTime(new Date());
        evalGroupUserService.insertEval(eval_group_user);
        return new Status("status", "200", "");
    }
}
