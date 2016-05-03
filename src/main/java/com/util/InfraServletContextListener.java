package com.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
 
@WebListener
public class InfraServletContextListener implements ServletContextListener {
 
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        ServletContextUtil.getInstance().setContext(ctx);
        System.out.println("InfraServlet Context initialized");
    }
 
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Shutting down system");
    }
     
}