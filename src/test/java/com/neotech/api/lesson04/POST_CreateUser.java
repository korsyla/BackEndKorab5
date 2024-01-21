package com.neotech.api.lesson04;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class POST_CreateUser {
	
	@Test
	public void createUserTest() 
	{
		
		// 1. Base URI that we are Connecting for all API calls
		RestAssured.baseURI = "https://bookstore.toolsqa.com";

		// 2. We Build/Create a Request
		RequestSpecification request = RestAssured.given();
		
		//We Set the request header to application/json
		request.header("Content-Type", "application/json");
		
		//We create the playload / body info such as username /password etc .....
		String playload = "{\r\n"
				+ "    \"userName\": \"TrentA\",\r\n"
				+ "    \"password\": \"Trent@123\"\r\n"
				+ "}";
		System.out.println(playload);
		
		//add the playload into the body 
		request.body(playload);
		
		//Now we can SEND the Request (post)
		Response response = request.when().post("/Account/v1/User");
		
		System.out.println("Status code is -> " + response.statusCode());
		response.prettyPrint();
		
		response.then().assertThat().statusCode(201);
		//Assert.assertEquals(406, response.statusCode());
		
			
	}
	
	
	
	
//	@Test
	public void createUserTestShorterWay() 
	{
		
		//THIS IS A SHORTER WAY FRO ABOVE TEST
		
		//We create the playload / body info such as username /password etc .....
		String playload = "{\r\n"
						+ "    \"userName\": \"TrentArnold\",\r\n"
						+ "    \"password\": \"Trent@123\"\r\n"
						+ "}";
		
		
		// 1. Base URI that we are Connecting for all API calls
		RestAssured.baseURI = "https://bookstore.toolsqa.com";
		
		RestAssured.given() 	// With Given we creating the Request
			.header("Content-Type", "application/json") 	//Then we send the header of the Request
			.body(playload) 	//Then we send the body of the Request / All the way up to here the request is ready 
			.when().post("/Account/v1/User")   //Here we receive a Response
			.then().assertThat().statusCode(406);
		
		
		
		
	}
	
	

}
