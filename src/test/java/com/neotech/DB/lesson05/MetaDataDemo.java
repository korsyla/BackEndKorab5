package com.neotech.DB.lesson05;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class MetaDataDemo {
	
	
	public static String dbUsername = "user1";
	public static String dbPassword = "Neotech@123"; 
	//DB url format
	//jdbc:jdbctype://ipaddress:portnumber/db_name
	public static String dbUrl = "jdbc:mysql://147.182.216.34:3306/classicmodels";
	
	
	@Test
	public void dbMetaData() throws SQLException 
	{
		// Create a connection to the database
		Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		
		DatabaseMetaData dbMetaData = conn.getMetaData();
		
		String driverName = dbMetaData.getDriverName();
		System.out.println("drivername is -> "+ driverName);
		
		String dbVersion = dbMetaData.getDatabaseProductVersion();
		System.out.println("DatabaseProductVersion is -> " + dbVersion);
		
		String dbName = dbMetaData.getDatabaseProductName();
		System.out.println("DatabaseProductName is -> " + dbName);
		
		conn.close();
		
	}
 	
	@Test
	public void rsMetaData() throws SQLException 
	{
		//1. Creating a connection
		Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		
		//To get the Results we NEED a Query!!!
		//2. Create a Statement st OBJECT / Statement is part of the Connection
		Statement st = conn.createStatement();
		
		//Now that I have a Statment st (Object) / I can execute a Statment / We can put it into a ResultSet rs (object)
		ResultSet rs = st.executeQuery("select * from employees where employeeNumber = 1076;");
		
		
		//Getting the data/info from ResultSet
		ResultSetMetaData rsMetaData = rs.getMetaData();
		
		//Get the total number of Columns
		int columnCount = rsMetaData.getColumnCount();
		System.out.println("ColumnCount is -> " + columnCount);
		
		// Get the name of the first column
		String columnName1 = rsMetaData.getColumnName(1);
		System.out.println("ColumnName is -> " + columnName1 );
		
		//Get the name of the third column
		String columnName3 = rsMetaData.getColumnName(3);
		System.out.println("ColumnName 3 is -> " + columnName3);
		
		System.out.println("-- Print all column names --");
		
		for(int i = 1; i <= columnCount; i++) 
		{
			String columnName = rsMetaData.getColumnName(i);
			System.out.println("ColumnName: " + i +  " -> " + columnName);
		}
		
		
		System.out.println("-- Lets Print all the column types ---");
		
		for(int i = 1; i <= columnCount; i++) 
		{
			String columnType = rsMetaData.getColumnTypeName(i);
			System.out.println("ColumnType is -> " + i + " -> " + columnType);
		}
		
		rs.close();
		st.close();
		conn.close();
		
	}

	
}
