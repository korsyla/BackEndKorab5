package com.neotech.api.lesson05;

import org.junit.Assert;
import org.junit.Test;

import com.neotech.utilities.APIConstants;
import com.neotech.utilities.APIGlobalVariables;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PUT_UpdateOneBook {
	
	
	@Test
	public void updateOneBook() 
	{
		//PUT - /BookStore/v1/Books/{ISBN}
		//We are trying to change/update one book with another book
		//So we, get rid of one book and update it with another book
		
		
		RestAssured.baseURI = APIConstants.BASE_URI;
		
		//Request needs 4 items
		//1. Authorization Token
		//2. ContentType
		//3. path parameter
		//4. payload/body
		
		
		//4. payload/BODY  / book4  -> will be replacing  -> book1 / 
		// BODY/payload should contain the new ISBN or new Replacement
		String payload = "{\r\n"
				+ "  \"userId\": \""+ APIGlobalVariables.userId + "\",\r\n"
				+ "  \"isbn\": \""+ APIGlobalVariables.book1 + "\"\r\n"
				+ "}";
		
		//1. Authorization Token
		//2. ContentType
		//3. path parameter
//		RestAssured.given().auth().oauth2(APIGlobalVariables.token)   //1. Authorization Token
//		.contentType(ContentType.JSON)      //2. ContentType
//		.pathParam("ISBN", APIGlobalVariables.book1) // This is the book, we are trying to replace with another book
//		.body(payload)
//		.when()
//		.put(APIConstants.UPDATE_ONE_BOOK_ENDPOINT)
//		.prettyPeek()
//		.then()
//		.assertThat().statusCode(200);
		
		
		
		Response response = RestAssured.given()
				.auth().oauth2(APIGlobalVariables.token)
				.pathParam("ISBN", APIGlobalVariables.book2)
				.contentType(ContentType.JSON)
				.body(payload)
				.when()
				.put(APIConstants.UPDATE_ONE_BOOK_ENDPOINT)
				.prettyPeek();
		
		
		//Now, I can validate
		response.then().assertThat().statusCode(200);
		
		//Can also check if the response body contains a certain value
		//We checking that from books we updated book2 .... Replaced book1 with book2
		Assert.assertTrue(response.jsonPath().getString("books").contains(APIGlobalVariables.book1));
		
		//negative testing/assertion  to make sure body doesn't contain a certain value/book
		Assert.assertFalse(response.jsonPath().getString("books").contains(APIGlobalVariables.book2));
		
		
	}
	
	
	
	
	

}
