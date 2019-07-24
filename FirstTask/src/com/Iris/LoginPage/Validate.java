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

import java.sql.*;

@WebServlet("/Validate")
public class Validate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String s1=request.getParameter("email");
		String s2=request.getParameter("pass");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			HttpSession session=request.getSession();
			session.setAttribute("userEmail",s1);
			//ServletContext context=getServletContext();
			//context.setAttribute("connObj", conn);
			
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
