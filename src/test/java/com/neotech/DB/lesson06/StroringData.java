package com.neotech.DB.lesson06;

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

import org.junit.Test;

import com.neotech.utilities.ConfigsReader;
import com.neotech.utilities.Constants;









public class StroringData {
	
	
	
	@Test
	public void getAndStoreData() throws SQLException 
	{
		// Reading the Connection Properties from the configuration.properties file
		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPETH);
		
		String dbUrl = ConfigsReader.getProperty("dbUrl");
		String dbUsername = ConfigsReader.getProperty("dbUsername");
		String dbPassword = ConfigsReader.getProperty("dbPassword");
		
		Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		
		Statement st = conn.createStatement();
		
		// ResultSet is what we will be getting back from the data
		ResultSet rs = st.executeQuery("select employeeNumber, lastName, firstName, email from employees limit 5");
		
		//List of Map, Key and Value
		List<Map<String, String>> listOfMaps = new ArrayList<>();
		Map<String, String> map;
		
		
		while(rs.next()) //rs.next will return us the FIRST Data Row -> '1002', 'Murphy', 'Diane', 'dmurphy@classicmodelcars.com'

		{
			//Create a new Map for every record, and Add it to the List
			map = new LinkedHashMap<>();
			map.put("employeeNumber", rs.getString("employeeNumber"));
			map.put("lastName", rs.getString("lastName"));
			map.put("firstName", rs.getString("firstName"));
			map.put("email", rs.getString("email"));
			
			listOfMaps.add(map);
			
		}
		
		
		System.out.println(listOfMaps);
		
		rs.close();
		st.close();
		conn.close();
		
	}
	
	
	
	//This is More Dynamic
	
	
	@Test
	public void getAndStoreDataEnhanced() throws SQLException 
	{
		
		// Reading the Connection Properties from the configuration.properties file
		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPETH);

		String dbUrl = ConfigsReader.getProperty("dbUrl");
		String dbUsername = ConfigsReader.getProperty("dbUsername");
		String dbPassword = ConfigsReader.getProperty("dbPassword");

		Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

		Statement st = conn.createStatement();

		// ResultSet is what we will be getting back from the data
		ResultSet rs = st.executeQuery("select employeeNumber, lastName, firstName, jobTitle from employees limit 5");
		
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int colCount = rsMetaData.getColumnCount();

		// List of Map, Key and Value
		List<Map<String, String>> listOfMaps = new ArrayList<>();
		Map<String, String> map;
		
		
		//Iterate over the DATA/ROWS
		while(rs.next()) 
		{
			//Initialize the new map object
			map = new LinkedHashMap();
			//Fill the Map using the MetaData
			for(int i = 1; i <= colCount; i++) 
			{
				//This Gets the columnName as KEY, and ColumnValue as VALUE / SO KEY - VALUE
				map.put(rsMetaData.getColumnName(i), rs.getString(i));
			}
			
			listOfMaps.add(map);
			
		
		}
		
		System.out.println(listOfMaps);
		
		
	}
	
	
	
	

}
