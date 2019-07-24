package com.Iris.LoginPage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Welcome")
public class Welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		
		String st = (String)request.getAttribute("userName");
		
		
		request.setAttribute("userName", st);
		out.println("Welcome:"+st+"<br/>");
		
		out.println("<a href='ViewProfile'>View_Profile</a>");
		out.println("<a href='UpdatePassword'>Update_Password</a>");
	
	}

}
