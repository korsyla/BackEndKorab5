package com.neotech.steps;

import com.neotech.utilities.APIConstants;
import com.neotech.utilities.ConfigsReader;
import com.neotech.utilities.Constants;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GenerateTokenAPIUtils {
	
	
	public static String token;
	
	
	@Given("A token is generated")
	public void a_token_is_generated() 
	{
		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPETH);
		
		String username = ConfigsReader.getProperty("APIUsername");
		String password = ConfigsReader.getProperty("APIPassword");
		
		String payload = "{\r\n"
		    		+ "  \"userName\": \""+username+"\",\r\n"
		    		+ "  \"password\": \""+password+"\"\r\n"
		    		+ "}";
		  
		RestAssured.baseURI = APIConstants.BASE_URI;
		  
		  
		token = RestAssured.given()
				  		.contentType(ContentType.JSON)
				  		.body(payload)
				  		.when()
				  		.post(APIConstants.GENERATE_TOKEN_ENDPOINT)
				  		//MEANS -> from the .body response, - as .jsonpath object/info, - give me the VALUE of the key "token"
				  		.body().jsonPath().getString("token");
		  
		System.out.println(token);
		
		
	}
	

}
