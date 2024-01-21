package com.neotech.api.lesson04;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class GET_AllBooks {

	public static void main(String[] args) {
		
		
		//OTHER LESSONS 1-3 ARE DONE FROM POSTMAN DIRECTLY, THATS WHY WE START FROM APILESSON#4
		
		//1. Base URI for all API calls 
		RestAssured.baseURI = "https://bookstore.toolsqa.com";
		System.out.println(RestAssured.baseURI);
		
		//Build/Create a Request: 1st thing we need a method, base uri, endpoint, parameters(If any)..
		RequestSpecification request = RestAssured.given();
		
		//Now, we send the request to the Endpoint / What is the Endpoint to send
		Response response = request.when().get("BookStore/v1/Books");
		System.out.println(response.statusCode());
		
		System.out.println("------------------------------------------------");
		//Headers
		Headers header = response.getHeaders();
		System.out.println(response.getHeaders());
		//Specific Header
		System.out.println(response.header("Content-Type"));
		
		System.out.println("------------------------------------------------");
		
		ResponseBody body = response.getBody();
//		System.out.println(body.asPrettyString());
		
		//We can also display the response directly without sysout.....
		String body2 = response.prettyPrint();
		
		

		
	}

}
