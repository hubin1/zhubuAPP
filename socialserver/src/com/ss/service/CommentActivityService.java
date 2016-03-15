/**
 * 
 */

package com.ss.service;

import java.util.List;

import com.ss.pojo.Comment_activity;

/**
 * [活动评论]<p>
 * [功能详细描述]<p>
 * @author zzb
 * @version 1.0, 2016年1月21日
 * @see
 * @since zhubu-V1001
 */
public interface CommentActivityService
{
    /**
     * 查找评论
     * @param activity
     * @return
     */
    List<Comment_activity> selectComment(Comment_activity activity);

    /**
     * 添加评论
     * @param activity
     * @return
     */
    int insertComment(Comment_activity activity);
}
