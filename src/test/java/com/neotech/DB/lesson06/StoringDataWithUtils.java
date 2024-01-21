package com.neotech.DB.lesson06;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.neotech.utilities.DBUtils;

public class StoringDataWithUtils {
	
	
	
	
	@Test
	public void getDataFromDB() 
	{
		//Create a connection
		DBUtils.getConnection();
		
		//Execute a Query and Get the List of Maps
		List<Map<String, String>> lmData =  DBUtils.storeDataFromDB("SELECT * FROM employees LIMIT 5");
		
		System.out.println(lmData);
		
		//Close the Connection
		DBUtils.closeConnection();
		
	}
	
	
	

}
