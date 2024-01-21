package com.neotech.api.lesson04;

import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GET_OneBook {

	@Test
	public void getOneBook() 
	{
		//WE ARE CREATING THE SAME THING AS WE DO IN POSTMAN BUT IN JAVA/ECLIPSE

		// 1. Base URI that we are Connecting for all API calls
		RestAssured.baseURI = "https://bookstore.toolsqa.com";
		
		// 2. We Build/Create a Request
		RequestSpecification request = RestAssured.given();
		
		// 3. Giving Query Parameters as Required per Swagger Document / This one is more clearer easy to read
		request.queryParam("ISBN", "9781449325862");

		// 4. Now, we send the request / This one is more clearer easy to read 
		Response response = request.when().get("BookStore/v1/Book");
		
		// OR, This one Same as above / above one is more clearer
		//Response response = request.when().get("BookStore/v1/Book?ISBN=9781449325862");
		
		System.out.println("Status Code is -> " + response.statusCode());
		
		//Lets Verify Status Code with Assertions
		//response.then() -> means, we get the response then we assert xyzzzzz.........
		response.then().assertThat().statusCode(200);
		
		//response.prettyPrint();
		
		//prettypeek lets us to keep working with response to method chaining  as its part of response it returns response
		response.prettyPeek();
		
		System.out.println("--------------------------------------------------------------------------------");
		
		//This is in a key = value pair / Key can be string , but value has to be in a Matchers
		//response.then() -> means, we get the response then we assert xyzzzzz.........
		response.then().assertThat().body("title", Matchers.equalTo("Git Pocket Guide"));
		
		//In the KEY "subtitle", containString part of the value "Introduction"
		//response.then() -> means, we get the response then we assert xyzzzzz.........
		response.then().assertThat().body("subTitle", containsString("Introduction"));
		
		//In the KEY "author", the Value of it ends with "Silverman"
		//response.then() -> means, we get the response then we assert xyzzzzz.........
		response.then().assertThat().body("author", endsWith("Silverman"));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
