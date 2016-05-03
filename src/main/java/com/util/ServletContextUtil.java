package com.util;

import javax.servlet.ServletContext;

public class ServletContextUtil {

	private static ServletContext context;
	private static ServletContextUtil servletContextUtil;
	
	protected ServletContextUtil(){
	}
	
	public static ServletContextUtil getInstance(){
		if(servletContextUtil == null){
			servletContextUtil = new ServletContextUtil(); 
		}
		return servletContextUtil;
	}

	public static ServletContext getContext() {
		if(servletContextUtil == null){
			servletContextUtil = new ServletContextUtil(); 
		}
		return context;
	}

	public static void setContext(ServletContext context) {
		ServletContextUtil.context = context;
	}
}
