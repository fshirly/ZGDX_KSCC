package com.fable.kscc.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class RequestUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestUtil.class);

	public HttpServletRequest getRequest() {
		// 获取当前request
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	public String getContextPath() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String contextPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath();
		return contextPath;

	}

	public HttpSession getSession() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		return request.getSession();
	}


	public String getSysId() {
		return (String) getSession().getAttribute("SYSID");
	}

	public String getUserId() {
		return (String) getSession().getAttribute("USERID");
	}
}
