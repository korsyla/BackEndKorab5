package com.neotech.DB.lesson05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCDemo2 {
	
	public static String dbUsername = "user1";
	public static String dbPassword = "Neotech@123"; 
	//DB url format
	//jdbc:jdbctype://ipaddress:portnumber/db_name
	public static String dbUrl = "jdbc:mysql://147.182.216.34:3306/LibraryMgmt";
	
	

	public static void main(String[] args) throws SQLException {

		//Retrieve all the book category names and store them in ArrayList
		//Print the ArrayList in the Console
		
		// Create a connection to the database
		Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		
		//Here we saying using the connection (Object) above create a Statment st (object)
		Statement st = conn.createStatement();
		
		//Now that I have a Statment st (Object) / I can execute a Statment / We can put into a ResultSet rs (object)
		ResultSet rs = st.executeQuery("SELECT * FROM bookcategory");
		
	
		List<String> categoryNames = new ArrayList<>();
		
		while(rs.next()) 
		{
			categoryNames.add(rs.getObject("BookCategoryName").toString());  // OR //categoryNames.add(rs.getString("BookCategoryName"));
			
			
		}
		
		System.out.println(categoryNames);
		
		System.out.println("-------------------------------");
		
		for(String name : categoryNames) 
		{
			System.out.println(name + " ");
		}
		
		
		//As Final Step, Close the connection and other objects one by one
		rs.close();
		st.close();
		conn.close();
		
		
	}

}
