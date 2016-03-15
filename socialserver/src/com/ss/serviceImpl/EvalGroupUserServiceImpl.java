/**
 * 
 */

package com.ss.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ss.dao.IEval_group_userDAO;
import com.ss.pojo.Eval_group_user;
import com.ss.service.EvalGroupUserService;

/**
 * [活动点赞关系]<p>
 * [功能详细描述]<p>
 * @author zzb
 * @version 1.0, 2016年1月21日
 * @see
 * @since zhubu-V1001
 */
@Service("evalGroupUserService")
public class EvalGroupUserServiceImpl implements EvalGroupUserService
{
    @Resource
    private IEval_group_userDAO eval_group_userMapper;

    @Override
    public List<Eval_group_user> selectEval(Eval_group_user eval_group_user)
    {
        return eval_group_userMapper.selectEval(eval_group_user);
    }

    @Override
    public int insertEval(Eval_group_user eval_group_user)
    {
        return eval_group_userMapper.insertEval(eval_group_user);
    }

}
