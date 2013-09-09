package com.routing.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBC {
	
	private static final long serialVersionUID = 1L;
    private static String dbURL = "jdbc:derby://localhost:1527/myDB;create=true;user=me;password=mine";
    private static String tableName = "restaurants";
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
	

    protected static void createConnection()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL); 
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
    }
    
	protected static void insertRestaurants(int id, String restName, String cityName)
    {
        try
        {
            stmt = conn.createStatement();
            stmt.execute("insert into " + tableName + " values (" +
                    id + ",'" + restName + "','" + cityName +"')");
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
    protected static List<RoutingEntries> selectRestaurants()
    {
     
    	RoutingEntries rEntry = new RoutingEntries();
    	List<RoutingEntries> routingEntriesTab = new ArrayList<RoutingEntries>();
    	
    	try
        {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + tableName);
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i=1; i<=numberCols; i++)
            {
                //print Column Names
                System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
            }

            System.out.println("\n-------------------------------------------------");

            while(results.next())
            {
                int id = results.getInt(1);
                String restName = results.getString(2);
                String cityName = results.getString(3);
                
                rEntry.setId(id);
                rEntry.setRestName(restName);
                rEntry.setCityName(cityName);
                
                routingEntriesTab.add(rEntry);
                
                
                System.out.println(id + "\t\t" + restName + "\t\t" + cityName);
            }
            results.close();
            stmt.close();
            
            return routingEntriesTab;
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
		return null;
    }
    
    protected static void shutdown()
    {
        try
        {
            if (stmt != null)
            {
                stmt.close();
            }
            if (conn != null)
            {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }           
        }
        catch (SQLException sqlExcept)
        {
            
        }

    }


}
