package com.Iris.LoginPage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.corba.se.pept.transport.Connection;

import java.sql.*;

@WebServlet("/Validate")
public class Validate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String s1=request.getParameter("email");
		String s2=request.getParameter("pass");
		
		ServletContext context = getServletContext();
		
		String s5 = context.getInitParameter("driverName");
		String s6 = context.getInitParameter("connString");
		String s3 = context.getInitParameter("userName");
		String s4 = context.getInitParameter("userPass");
		out.println(s1+" "+s2+" "+s3+" "+s4);
		try {
			
			
			HttpSession session=request.getSession();
			session.setAttribute("userEmail",s1);
			//ServletContext context=getServletContext();
			//context.getAttribute("connObj");
			Connection conn = (Connection)context.getAttribute("connObj");
			PreparedStatement ps=conn.prepareStatement("select * from UserTable where email=? and password=?");
			ps.setString(1,s1);
			ps.setString(2,s2);
			
			ResultSet rs = ps.executeQuery();
			
			
			if(rs.next()) {
				
				
				String name = rs.getString(3)+rs.getString(4);
				
				
				request.setAttribute("userName", name);
				
				RequestDispatcher rd = request.getRequestDispatcher("Welcome");
				rd.forward(request, response);
			}
			else {
				
				RequestDispatcher rd = request.getRequestDispatcher("LoginPage.html");
				rd.include(request, response);
				
				out.println("<div align = 'center'>Incorrect Email or Password");
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
