package com.Iris.LoginPage;

import java.sql.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.sun.corba.se.pept.transport.Connection;


@WebListener
public class Listen implements ServletContextListener {

	
    public void contextDestroyed(ServletContextEvent event)  { 
        
    	ServletContext context = event.getServletContext();
    	Connection conn = (Connection)context.getAttribute("connObj");
    	try {
    		conn.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

	
    public void contextInitialized(ServletContextEvent event)  { 
         
    	ServletContext context = event.getServletContext();
    	
    	String s1 = context.getInitParameter("driverName");
    	String s2 = context.getInitParameter("connString");
    	String s3 = context.getInitParameter("userName");
    	String s4 = context.getInitParameter("userPass");
    	
    	try {
    		Class.forName(s1);
    		Connection conn = (Connection)DriverManager.getConnection(s2,s3,s4);
    		context.setAttribute("connObj", conn);
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    	
    }
	
}
