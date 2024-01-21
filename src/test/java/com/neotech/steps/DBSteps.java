package com.neotech.steps;

import java.util.List;
import java.util.Map;

import com.neotech.utilities.DBUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DBSteps extends DBUtils {
	
	//This is a class variable/Instance variable which can be accessed by any method from the class even locally within method
	List<Map<String, String>> cusomtersList;
	
	@Given("I am connected to the database")
	public void i_am_connected_to_the_database() 
	{
	   DBUtils.getConnection();
	   getConnection();
	   // OR -> getConnection(); , directly since we extended DBUtils Class 
	}
	
	@When("I get the top three customers by credit limit")
	public void i_get_the_top_three_customers_by_credit_limit() 
	{
	   cusomtersList = DBUtils.storeDataFromDB("SELECT customerNumber, customerName, creditLimit FROM customers "
	    		+ "ORDER BY creditLimit DESC LIMIT 3;");
	    
	   
	}
	
	@Then("I print their names and credit limits")
	public void i_print_their_names_and_credit_limits() 
	{
		System.out.println("-------------------------");
//		System.out.println(cusomtersList);
		
		for(Map<String, String> customer : cusomtersList) 
		{
			// This gives us a list of Maps / Data of each row
			System.out.println(customer);
			
			System.out.println("---------------------------------------------------------------------");
			
			//By GIVING the Key of column "customerName", we GET the value(s) of it one by one
			String customerName = customer.get("customerName");
			//By GIVING the Key of column "creditLimit", we GET the value(s) of it one by one
			String creditLimit = customer.get("creditLimit");
			
//			System.out.println(customerName + " -> " + creditLimit);
		}
		
		System.out.println("-------------------------");
		 
	}
	
	@Then("I close the database connection")
	public void i_close_the_database_connection() 
	{
	    DBUtils.closeConnection();
	}

}
