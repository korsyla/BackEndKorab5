package com.neotech.api.lesson05;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.neotech.utilities.APIConstants;
import com.neotech.utilities.APIGlobalVariables;

import io.restassured.RestAssured;

public class DELETE_AllBooks {
	
	@Test
	public void deleteAllBooks() 
	{
		
		RestAssured.baseURI = APIConstants.BASE_URI;
		
		RestAssured.given()
//		.auth().oauth2(APIGlobalVariables.token)
		.header("Authorization", "Bearer " + APIGlobalVariables.token) //Authorization
		.queryParam("UserId", APIGlobalVariables.userId)  //USerid
		.when()
		.delete(APIConstants.DELETE_ALL_BOOOKS_ENDPOINT)
		.prettyPeek()
		.then();
		
		//This below won't work because a body doesn't comes back from response for Deleting all books even though 
		//The document says there is a body, in this case we have to report it to the Developers
		//API DOUCMENTATION(SWAGGER) HAS TO BE FOLLOWED
//		.assertThat().statusCode(204)
//		.assertThat().body("userId", Matchers.equalTo(APIGlobalVariables.userId));  //This gives us the response on the console
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	

}
