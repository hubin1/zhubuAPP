
package com.ss.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ss.dao.IComment_activityDAO;
import com.ss.pojo.Comment_activity;
import com.ss.service.CommentActivityService;

/**
 * [活动评论]<p>
 * [功能详细描述]<p>
 * @author zzb
 * @version 1.0, 2016年1月21日
 * @see
 * @since zhubu-V1001
 */
@Service("commentActivityService")
public class CommentActivityServiceImpl implements CommentActivityService
{
    @Resource
    private IComment_activityDAO comment_activityMapper;

    @Override
    public List<Comment_activity> selectComment(Comment_activity activity)
    {
        return comment_activityMapper.selectComment(activity);
    }

    @Override
    public int insertComment(Comment_activity activity)
    {
        return comment_activityMapper.insertComment(activity);
    }

}
