
package com.ss.util;

import javax.servlet.http.HttpSession;

import com.ss.pojo.Baseinfo;

/**
 * [session获取用户，并判断session是否存在用户，用于判断用户是否登录]<p>
 * [功能详细描述]<p>
 * @author zzb
 * @version 1.0, 2016年1月18日
 * @see
 * @since zhubu-V1001
 */
public class SessionUserUtil
{
    public static Baseinfo getUserBySession(HttpSession session){
        Baseinfo original=(Baseinfo) session.getAttribute(DefaultVariables.USERINFO);
        if (null == original)
        {
            throw new RuntimeException("msg:original info not found");
        }
        return original;
    }
}
