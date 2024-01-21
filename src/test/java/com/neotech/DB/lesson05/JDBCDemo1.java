package com.neotech.DB.lesson05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo1 {
	
	public static String dbUsername = "user1";
	public static String dbPassword = "Neotech@123"; 
	//DB url format
	//jdbc:jdbctype://ipaddress:portnumber/db_name
	public static String dbUrl = "jdbc:mysql://147.182.216.34:3306/LibraryMgmt";
	

	public static void main(String[] args) throws SQLException {

		//Whats the 1st Thing we need to do??
		 // - Create a connection -> 
		 // 1. We need DB URL/Address and Port Number
		 // 2. UserName 3. Password
		
		/*
		 * HostName: 147.182.216.34
		 * Port: 3306
		 * DB Name: LibraryMgmt 
		 * Password: Neotech@123
		 */
		
		// Create a connection to the database
		Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		
		System.out.println("DataBase Conncection is Successful!!!");
		
		//Create the statement object
		Statement st = conn.createStatement();
		
		//Write the Statement + Got the Results / Execute the Statement and assigned the data info to an OBJECT(rs)
		ResultSet rs = st.executeQuery("SELECT * FROM book");
		
		System.out.println("---------------------------------------");
		//Prints the object not data yet
		System.out.println(rs.toString());
		
		
		//Now, how do we get the data info/values from our ResultSet????
		//This info will be retrieved line by line
		
		rs.next();
		//Getting values using column label
		String bookname1 = rs.getString("BookName");
		System.out.println(bookname1);
		
		rs.next();
		//Getting values using column index
		String bookName2 = rs.getString(2);
		System.out.println(bookName2);
		
		rs.next();
		//Getting values using getObject
		String bookName3 = rs.getObject("BookName").toString();
		System.out.println(bookName3);
		
		System.out.println("---------------------------------------");
		System.out.println("Getting values using a loop!!!!");
		
		while(rs.next()) 
		{
			String bookName = rs.getObject("BookName").toString();
			System.out.println(bookName);
		}
		
		//When we done executing statment, we need to close the connection and other objects
		
		rs.close();
		st.close();
		conn.close();
		
	}

}
