package com.neotech.api.lesson05;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.neotech.utilities.APIConstants;
import com.neotech.utilities.APIGlobalVariables;

import io.restassured.RestAssured;

public class GET_UserAccount {
	
	@Test
	public void getAccount() 
	{
		// GET -  /Account/v1/User/{UUID}

		RestAssured.baseURI = APIConstants.BASE_URI;
		String token = APIGlobalVariables.token;
		
		// This reads just how it does in postman .. Authorization -> Bearer Token
//		RestAssured.given().auth().oauth2(token)..pathParam... etcccc;
		//OR, Another way
		RestAssured.given()
		.header("Authorization", "Bearer " + APIGlobalVariables.token) //This is Authorization .. also we can use this  .auth().oauth2
		.pathParam("UserId", APIGlobalVariables.userId)
		.when().get(APIConstants.GET_USERACCOUNT_ENDPOINT)
		.prettyPeek()
		.then().assertThat().statusCode(200)
		.and()
		.body("userId", Matchers.is(APIGlobalVariables.userId))
		.and()
		.body("username", Matchers.equalTo("TrentA"));
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
