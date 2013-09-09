package com.routing.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class UpdateRoutingTab
 */

@WebServlet("/update")
public class RoutingInfo extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public RoutingInfo() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("HELLO : WE ARE REALLY LUCKY GET !!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		System.out.println("Retrieve Values");
		Map <String, Object> map = new HashMap<String,Object>();
		boolean isValid = false;
		
		String filename = request.getParameter("filename");
		String rcvBusSys = request.getParameter("busSyst");
		
		Routing routObj = new Routing();
		routObj.setFilename(filename);
		routObj.setBussys(rcvBusSys);


		if (filename != null && filename.trim().length() != 0 
		&& rcvBusSys != null && rcvBusSys.trim().length() != 0){
			isValid = true;			
			
			map.put("routing", routObj);			
	        JDBC.createConnection();
	        JDBC.insertRestaurants(1, filename, rcvBusSys);
	        JDBC.selectRestaurants();
	        JDBC.shutdown();
		}
		map.put("isValid",isValid);
		write(response, map);		
		
	}

	
	private void write(HttpServletResponse response, Map<String, Object> map) throws IOException {
	response.setContentType("application/json");
	response.setCharacterEncoding("UTF-8");
	response.getWriter().write(new Gson().toJson(map));
	
		
	}

	
	
	
}
