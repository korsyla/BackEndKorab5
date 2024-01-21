package com.neotech.DB.lesson06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class HomeWork {
	
//	 Connect to classicmodels database
//     Execute a query to get all information of customer with id 124
//     Get the resultset metadata
//     Print the number of columns
//     Get all the column names and store them in an arraylist
//     Print the Arraylist
	
	
	//1st Thing we make a Connection to the DataBase
	//1st thing we need is DB URL. 
	//2nd Thing we need is DB Credentials (username and password).
	//3rd thing we need is DB URL Format -> //jdbc:jdbcType://ipaddress:portnumber/db_name -> 
    //					                     "jdbc:mysql://147.182.216.34:3306/classicmodels"
	//Instead of ipaddress we can also use the neotech.com
	
	
	public static String dbUrl =  "jdbc:mysql://147.182.216.34:3306/classicmodels";
	
	public static String dbUsername = "user1";
	public static String dbPassword = "Neotech@123";
	
	
	
	@Test
	public void getResults() throws SQLException 
	{
		//1st Thing we create a Connection to the DataBase
		Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		
		//2nd Thing. We  create a Statment (object) st to use to run a Query / BUT We only creating the object here
		Statement st = conn.createStatement();
		
		//Now that I have a Statment st (Object) / I can execute a Statment / We can put into a ResultSet rs (object)
		ResultSet rs = st.executeQuery("select * from customers where customerNumber < 124;");
		
		
		//Getting the MetaData of the ResultSet / MetaData is the Structure of our Data and not the actual data
		ResultSetMetaData rsMetaData = rs.getMetaData();
		
		//Getting the number of Columns / It gave us 13
		int colCount = rsMetaData.getColumnCount();
		System.out.println("Number of Columns is -> " + colCount);
		
		//Get all the column names and store them in an arraylist / AND Print the Arraylist
		List<String> columnName = new ArrayList<>();
		
		for(int i = 1; i <= colCount; i++) 
		{
			columnName.add(rsMetaData.getColumnName(i));
	
		}
		
		System.out.println("Columnnames are -> " + columnName);
		
		
	// ---------------------------------------------------------------------------------------------------------------------------
	
		
		//Now, How do we read the actual DATA from our ResultSet rs that we stored??
		//This is going to iterate item by item 1 by one Data Set -> Those data below
		// '124', 'Mini Gifts Distributors Ltd.', 'Nelson', 'Susan', '4155551450', '5677 Strong St.', NULL, 'San Rafael', 'CA', '97562', 'USA', '1165', '210500.00'
//		rs.next();
//		
//		//We can give the column name OR the column index / We give the key and it returns us the value
//		String customerName = rs.getString("customerName");
//		String phone = rs.getString(5);
//		
//		System.out.println("Customername is -> " + customerName);
//		System.out.println("Phone number is -> " + phone);
		
		
		
		// What if we changed our ResultSet Query to -> "select * from customers where customerNumber < 124;"
		
		
		while(rs.next()) 
		{
			String customerName = rs.getString("customerName");
			String phone = rs.getString(5).toString();
			
			System.out.println(customerName + " : " + phone);
		
		}
		
		 
		//Last thing is, Close DatabBAse Objects conn. st, rs so we can save Resources / Memory
		conn.close();
		st.close();
		rs.close();
		
		
		
		
		
		
		
		
		
		
		
//		conn.close();
//		st.close();
//		rs.close();
		
		
		
	}
	

}
