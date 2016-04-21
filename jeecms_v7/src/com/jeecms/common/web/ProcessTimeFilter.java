package com.jeecms.common.web;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 执行时间过滤器(后台日志记录一个请求在服务端使用的总时间)
 */
public class ProcessTimeFilter implements Filter {
	protected final Logger log = LoggerFactory
			.getLogger(ProcessTimeFilter.class);
	/**
	 * 请求执行开始时间
	 */
	public static final String START_TIME = "_start_time";

	public void destroy() {
	}

	/* 
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//context location
		///WEB-INF/config/jeecms-servlet-front.xml
		///WEB-INF/config/plug/**/*-servlet-front-action.xml
		
		HttpServletRequest request = (HttpServletRequest) req;
		long time = System.currentTimeMillis();
		request.setAttribute(START_TIME, time);
		chain.doFilter(request, response);
		time = System.currentTimeMillis() - time;
		log.debug("process in {} ms: {}", time, request.getRequestURI());
		Enumeration enums = request.getAttributeNames();
		while(enums.hasMoreElements()){
			Object o =  enums.nextElement();
			if (o instanceof String){
				String key = (String)o;
				Object value = request.getAttribute(key);
				log.debug("Request attributes: key: 【{}】, value: 【{}】 ", new Object[]{key, value} );
			}
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}
}
