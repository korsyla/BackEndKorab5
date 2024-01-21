package com.neotech.api.lesson05;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.neotech.utilities.APIConstants;
import com.neotech.utilities.APIGlobalVariables;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class POST_AddListOfBooks {
	
	@Test
	public void addListOfBooks() 
	{
		
		RestAssured.baseURI = APIConstants.BASE_URI;
		
		String payload  = "{\r\n"
				+ "    \"userId\": \""+ APIGlobalVariables.userId +"\",\r\n"
				+ "    \"collectionOfIsbns\": [\r\n"
				+ "        {\r\n"
				+ "            \"isbn\": \""+ APIGlobalVariables.book1 +"\"\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"isbn\": \""+ APIGlobalVariables.book2 +"\"\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"isbn\": \"" + APIGlobalVariables.book3 + "\"\r\n"
				+ "        }\r\n"
				+ "    ]\r\n"
				+ "}";
		
		System.out.println(payload);
		
//		RestAssured.baseURI = APIConstants.BASE_URI;
		
		RestAssured.given()
		.header("Authorization", "Bearer " + APIGlobalVariables.token)
		.body(payload)
		.contentType(ContentType.JSON)
		.when().post(APIConstants.ADD_LIST_OF_BOOKS_ENDPOINT)
		.prettyPeek()
		.then()
		.assertThat().statusCode(201)
		.and()
		.body("books[0].isbn", Matchers.is(APIGlobalVariables.book1))
		.and()
		.body("books[1].isbn", Matchers.is(APIGlobalVariables.book2));
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
