package com.neotech.api.lesson04;

import org.junit.Assert;
import org.junit.Test;

import com.neotech.utilities.APIConstants;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class POST_GenerateAToken {
	
//	@Test
	public void generateAToken() 
	{
		
		// 1. Base URI that we are Connecting for all API calls
//		RestAssured.baseURI = "https://bookstore.toolsqa.com";
		RestAssured.baseURI = APIConstants.BASE_URI;
		
		// 2. We Build/Create a Request
		RequestSpecification request = RestAssured.given();
		
		//We Set the request header to application/json
		request.header("Content-Type", "application/json");
		
		//We create the playload / body information from Swagger Documentation such as username /password etc .....
		String playload = "{\r\n"
				+ "  \"userName\": \"EHazarddd\",\r\n"
				+ "  \"password\": \"Hazard@123\"\r\n"
				+ "}";
		
		//We, add the playload into the body 
		request.body(playload);
		
		//Now we can SEND the Request (post)
		Response response = request.when().post(APIConstants.GENERATE_TOKEN_ENDPOINT);
		
		//Check the Status Code to be 200
		response.then().assertThat().statusCode(200);
		
		//Get the full status line
		System.out.println(response.statusLine());
		
		//Get info from the header
		System.out.println(response.header("Content-Type"));
		
		//Get info from the body  / .getString("token") -> means give me the VALUE of the KEY("token")
		String token = response.body().jsonPath().getString("token");
		System.out.println("Token is -> " + token);
		
		//Check the body to contain "token"
		Assert.assertTrue(response.body().asString().contains("token"));
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	@Test
	public void generateTokenShortWay() 
	{
		
		// 1. Base URI that we are Connecting for all API calls
//		RestAssured.baseURI = "https://bookstore.toolsqa.com";
		RestAssured.baseURI = APIConstants.BASE_URI;
				
		// 2. We Build/Create a Request
		RequestSpecification request = RestAssured.given();
				
				
		//We create the playload / body information from Swagger Documentation such as username /password etc .....
		String playload = "{\r\n"
				+ "  \"userName\": \"TrentA\",\r\n"
				+ "  \"password\": \"Trent@123\"\r\n"
				+ "}";		
			
		//Configure and send the Request
		Response response = request
//				.header("Content-Type", "application/json") ->  the Below line does the samething
				.contentType(ContentType.JSON) 
				.body(playload)
				.when().post(APIConstants.GENERATE_TOKEN_ENDPOINT)
				.prettyPeek();
				
				
			
		//Check the Status Code to be 200
		response.then().assertThat().statusCode(200);
			
		
		//Get the full status line
		System.out.println(response.statusLine());
				
		//Get info from the header
		System.out.println(response.header("Content-Type"));
		
		//Get info from the body  / .getString("token") -> means give me the VALUE of the KEY("token")
		String token = response.body().jsonPath().getString("token");
		System.out.println("Token is -> " + token);
		
		
		
		
		
		
	}
	
	
	
	
	
	
	

}
