package com.neotech.api.lesson05;

import org.junit.Test;

import com.neotech.utilities.APIConstants;
import com.neotech.utilities.APIGlobalVariables;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DELETE_DeleteUserAccount {
	
	@Test
	public void deleteUserAccount() 
	{
		
		RestAssured.baseURI = APIConstants.BASE_URI;
		
		Response response = RestAssured
						.given()
					//		.header("Authorization", "Bearer " + APIGlobalVariables.token)
							.auth().oauth2(APIGlobalVariables.token)
							.pathParam("UUID", APIGlobalVariables.userId)
						.when()
							.delete(APIConstants.DELETE_USER_ACCOUNT)
							.prettyPeek();
					//		.then().assertThat().statusCode(204);
		
		
				response.then().assertThat().statusCode(204);

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	

}
