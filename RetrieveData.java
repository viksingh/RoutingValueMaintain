package com.routing.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/retrieveData")

public class RetrieveData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RetrieveData() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		System.out.println("WE ARE IN GET");

		Map <String, Object> map = new HashMap<String,Object>();
		boolean isValid = false;
		
		List<RoutingEntries> routingEntriesTab = new ArrayList<RoutingEntries>();		
        JDBC.createConnection();
        routingEntriesTab =  JDBC.selectRestaurants();

        for (int j = 0; j < routingEntriesTab.size(); j++) {
        	
        	map.put( Integer.toString(j), routingEntriesTab.get(j));
        	
        }
        
        
    	response.setContentType("application/json");
    	response.setCharacterEncoding("UTF-8");
    	

 String test =  new Gson().toJson(routingEntriesTab);
 response.getWriter().write(new Gson().toJson(routingEntriesTab));
        
        
        
//		write(response, map);	
        JDBC.shutdown();	
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("WE ARE IN POST");
		
		Map <String, Object> map = new HashMap<String,Object>();
		boolean isValid = false;
		
		List<RoutingEntries> routingEntriesTab = new ArrayList<RoutingEntries>();		
        JDBC.createConnection();
        routingEntriesTab =  JDBC.selectRestaurants();

        for (int j = 0; j < routingEntriesTab.size(); j++) {
        	
        	map.put( Integer.toString(j), routingEntriesTab.get(j));
        	
        }
        
        
    	response.setContentType("application/json");
    	response.setCharacterEncoding("UTF-8");
    	

 String test =  new Gson().toJson(routingEntriesTab);
 response.getWriter().write(new Gson().toJson(routingEntriesTab));

        
        
        
        JDBC.shutdown();
		
	}
	
	private void write(HttpServletResponse response, Map<String, Object> map) throws IOException {
	response.setContentType("application/json");
	response.setCharacterEncoding("UTF-8");
	
	String temp = new Gson().toJson(map);
	response.getWriter().write(new Gson().toJson(map));
	
		
	}
	

}
