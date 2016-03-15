package com.ss.filter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ss.util.DefaultVariables;

/**
 * 过滤成员中需要进行身份验证的请求
 * 
 * @author louis
 *
 */
public class SessionFilter extends OncePerRequestFilter {

	private Logger logger = Logger.getLogger(SessionFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 获取请求的路径
		String path = request.getRequestURI();
		int pos = path.lastIndexOf(";");
		if(-1 == pos){
			path = path.substring(path.lastIndexOf("/"));
		}else{
			path = path.substring(path.lastIndexOf("/"), pos);
		}
		
		
		logger.debug(path);
		boolean b = DefaultVariables.SETS.contains(path);
		if(b){
			HttpSession session = request.getSession(true);
			if(null == session.getAttribute(DefaultVariables.USERINFO)){
				logger.debug(path+" 请求未携带 token 或 token 过期");
				response.getWriter().println("{\"key\":\"status\",\"value\":\"1\"}");
				return;
			}
		}
		
		filterChain.doFilter(request, response);

	}

}
