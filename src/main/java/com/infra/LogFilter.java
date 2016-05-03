package com.infra;

import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Implements Filter class
public class LogFilter implements Filter {
	public void init(FilterConfig config) throws ServletException {
		// Get init parameter
		String testParam = config.getInitParameter("test-param");

		// Print the init parameter
		System.out.println("Test Param: " + testParam);
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws java.io.IOException, ServletException {

		// Get the IP address of client machine.
		String ipAddress = request.getRemoteAddr();

		// Log the IP address and current timestamp.
		System.out.println("IP " + ipAddress + ", Time "
				+ new Date().toString());

		HttpServletRequest request2 = (HttpServletRequest) request;
		HttpServletResponse response2 = (HttpServletResponse) response;
		response2.setHeader("Cache-Control",
				"no-cache, no-store, must-revalidate, private"); // HTTP 1.1.
		response2.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response2.setHeader("Expires", "0"); // Proxies.
		response2.setHeader("X-XSS-Protection", "1; mode=block");
		response2.setHeader("X-Content-Type-Options", "nosniff");
		response2.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
		// String sessionid = request2.getSession().getId();
		// be careful overwriting: JSESSIONID may have been set with other flags
		// response2.setHeader("SET-COOKIE", "JSESSIONID=" + sessionid +
		// "; Secure");
		// response2.setHeader("SET-COOKIE", "JSESSIONID=" + sessionid +
		// "; HttpOnly");
		/*if (response2.containsHeader("SET-COOKIE")) {
			String sessionid = request2.getSession().getId();
			String contextPath = request2.getContextPath();
			String secure = "";
			if (request.isSecure()) {
				secure = "; Secure";
			}
			response2.setHeader("SET-COOKIE", "JSESSIONID=" + sessionid
					+ "; Path=" + contextPath + "; HttpOnly" + secure);
		}*/
		System.out.println(response2.toString());

		// Pass request back down the filter chain
		
		
		chain.doFilter(request, response);
	}

	public void destroy() {
		/*
		 * Called before the Filter instance is removed from service by the web
		 * container
		 */
	}
}
