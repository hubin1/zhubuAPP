package com.fp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 用于过滤内容
 * 
 * @author louis
 *
 */
public class FormValidFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		boolean result = ServletFileUpload.isMultipartContent(request);
		if (result) {
			chain.doFilter(arg0, arg1);
		} else {
			HttpServletResponse response = (HttpServletResponse) arg1;
			response.getWriter().println("error");
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
