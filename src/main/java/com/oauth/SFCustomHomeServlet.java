package com.oauth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.Constants;

@WebServlet("/sfcustomhome")
public class SFCustomHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		System.out.println("URL OF BASE"+request.getSession().getAttribute(Constants.INSTANCE_URL)+Constants.SFHomeUrl);
		response.sendRedirect(request.getSession().getAttribute(Constants.INSTANCE_URL)+Constants.SFHomeUrl);
		System.out.println("End");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
