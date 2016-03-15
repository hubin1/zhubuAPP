
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
import com.ss.pojo.Comment_activity;
import com.ss.pojo.Status;
import com.ss.service.CommentActivityService;
import com.ss.util.SessionUserUtil;

/**
 * [活动评论]<p>
 * [功能详细描述]<p>
 * @author zzb
 * @version 1.0, 2016年1月21日
 * @see
 * @since zhubu-V1001
 */
@RequestMapping(value = "/commentActivity")
@Controller
public class CommentActivityController
{
    @Autowired
    private CommentActivityService commentActivityService;

    /**
     * 查询活动评论
     * @param comment_activity
     * @param session
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/finComment", method = RequestMethod.POST)
    @ResponseBody
    public List<Comment_activity> finComment(@ModelAttribute Comment_activity comment_activity , HttpSession session)
            throws RuntimeException
    {
        SessionUserUtil.getUserBySession(session);
        List<Comment_activity> comment_activitys = commentActivityService.selectComment(comment_activity);
        return comment_activitys;
    }

    /**
     * 增加活动评论
     * @param comment_activity
     * @param session
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    @ResponseBody
    public Status addEval(@ModelAttribute Comment_activity comment_activity , HttpSession session)
            throws RuntimeException
    {
        Baseinfo baseinfo = SessionUserUtil.getUserBySession(session);
        comment_activity.setBuid(baseinfo.getBuid());
        comment_activity.setTime(new Date());
        commentActivityService.insertComment(comment_activity);
        return new Status("status", "200", "");
    }
}
