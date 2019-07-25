package com.Iris.LoginPage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ViewProfile")
public class ViewProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session=request.getSession();
		String from =(String)session.getAttribute("userEmail");
		
		
		ServletContext context = getServletContext();
		try {
			
			
			Connection conn = (Connection)context.getAttribute("connObj");
			PreparedStatement ps=conn.prepareStatement("select * from UserTable where email=?");
			ps.setString(1, from);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String fname = rs.getString(3);
				String lname = rs.getString(4);
				int sal = rs.getInt(5);
				
				out.println("First Name is:"+fname+"  \n\n");
				out.println("last name is: "+lname+"  \n\n");
				out.println("salary is :"+sal+"  \n\n");
				
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
