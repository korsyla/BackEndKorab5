package com.neotech.api.lesson04;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ResponseVerification {

	@Test
	public void verifyAllBooksRequest() 
	{
		// 1. Base URI for all API calls
		RestAssured.baseURI = "https://bookstore.toolsqa.com";
		System.out.println(RestAssured.baseURI);
		
		//Build/Create a Request: 1st thing we need a method, base uri, endpoint, parameters(If any)..
		RequestSpecification request = RestAssured.given();
		
		//Now, we send the request to the Endpoint / What is the Endpoint to send
		Response response = request.when().get("BookStore/v1/Books");
		
		
		//Lets Verify the response code is 200...
		response.then().assertThat().statusCode(200);
		
		//OR, We can verify using JUnit Assertion... (EXPECTED, ACTUAL)
		Assert.assertEquals(200, response.statusCode());
		
		System.out.println("----------------------------------------------------------------------");
		
		//Verify the content type in header
		//Content-Type is the KEY  & application/json; charset=utf-8  -> is the Value
		response.then().assertThat().header("Content-Type", "application/json; charset=utf-8");
		
		//OR, We can verify using JUnit Assertion... (EXPECTED, ACTUAL)
		// response.header("Content-Type" -> means give me the value of Content-Type from PostMan in header
		Assert.assertEquals("application/json; charset=utf-8", response.header("Content-Type"));
		
		System.out.println("----------------------------------------------------------------------");
		
		//Check if body contains "Learning JavaScript Design Patterns"
		//Body refers to the whole Info in the body of PostMan
		String body = response.body().asString();
		Assert.assertTrue(body.contains("Learning JavaScript Design Patterns"));
		
		//We can also use this for body in one line / same as above
		Assert.assertTrue(response.body().asString().contains("Learning JavaScript Design Patterns"));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
