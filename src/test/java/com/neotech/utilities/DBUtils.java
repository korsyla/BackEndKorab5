package com.neotech.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {
	
	private static Connection conn;
	private static Statement st;
	private static ResultSet rs;
	
	
	//Connecting to the DataBase
	public static void getConnection() 
	{
		
		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPETH);
		
		try 
		{
			String dbUrl = ConfigsReader.getProperty("dbUrl");
			String dbUserName = ConfigsReader.getProperty("dbUsername");
			String dbPassword = ConfigsReader.getProperty("dbPassword");

			conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		} 
		catch (SQLException e) 
		{
			System.out.println("Could NOT connect to the DataBase!");
			e.printStackTrace();
		}
		
	}
	
	
	//Getting the Data / Storing it in a List of Maps and Return it to us as ListOfMaps(listOfData)
	public static List<Map<String, String>> storeDataFromDB(String sqlQuery) 
	{
		List<Map<String, String>> listOfData = new ArrayList<>();
		
		try 
		{
			st = conn.createStatement();
			rs = st.executeQuery(sqlQuery);
			
			ResultSetMetaData rsMetaData = rs.getMetaData();
			
			Map<String, String> map;
			//Iterate over the DATA/ROWS
			while(rs.next()) 
			{
				map = new LinkedHashMap<>();
				
				for(int i = 1; i <= rsMetaData.getColumnCount(); i++) 
				{
					//This Gets the columnName as KEY, and ColumnValue as VALUE / SO KEY - VALUE = Filling the Map
					map.put(rsMetaData.getColumnName(i), rs.getString(i));
					//map.put(rsMetaData.getColumnName(i), rs.getObject(i).toString()); -> This is same as abovee
				}
				listOfData.add(map);
				
			}
			
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		
		return listOfData;
		
	}

	
	//Close The Connection
	public static void closeConnection() 
	{
		try 
		{
			if(rs != null) 
			{
				rs.close();
			}
			if(st != null) 
			{
				st.close();
			}
			if(conn != null) 
			{
				conn.close();
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	

}
