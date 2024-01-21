package com.neotech.steps;

import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Assert;

import com.neotech.utilities.APIConstants;
import com.neotech.utilities.APIGlobalVariables;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AddBooksSteps {
	
	
	Response response;

	
	@When("I add books to the account")
	public void i_add_books_to_the_account() 
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
	    
	    RestAssured.given()
	    .auth().oauth2(GenerateTokenAPIUtils.token)
	    .contentType(ContentType.JSON)
	    .body(payload)
	    .when()
	    .post(APIConstants.ADD_LIST_OF_BOOKS_ENDPOINT);
	    
	}
	
	@When("I get the account information")
	public void i_get_the_account_information() 
	{
	    RestAssured.baseURI = APIConstants.BASE_URI;
	    
	  //response gives us all the info for our GET User account endpoint / And we can use for Validation Below 
	  response = RestAssured.given()
			  	.auth().oauth2(GenerateTokenAPIUtils.token)
			  	.pathParam("UserId", APIGlobalVariables.userId)
			  	.when().get(APIConstants.GET_USERACCOUNT_ENDPOINT)
			  	.prettyPeek();
	}
	
	@Then("Ivalidate that the books are in the account")
	public void ivalidate_that_the_books_are_in_the_account() 
	{
		//1st WAY -> Validate in response body 
	    response.then().assertThat().body("books[0].isbn", Matchers.equalTo(APIGlobalVariables.book1));
	    
	    //2nd WAY - validate book is in response body using JUnit ASSERTIONS
	    String firstBook = response.body().jsonPath().getString("books[0].isbn");
	    Assert.assertEquals(APIGlobalVariables.book1, firstBook);
	    
	    //We can also validate the second book is in response body
	    response.then().assertThat().body("books[1].isbn", Matchers.equalTo(APIGlobalVariables.book2));
	    
	    //lets get user name /This gives us the username
	    String username = response.body().jsonPath().getString("username");
	    System.out.println("Username is -> " + username);
	    
	    //lets get the # of pages for the second book
	    int secondBookPages = response.body().jsonPath().getInt("books[1].pages");
	    System.out.println("The second book has -> " + secondBookPages + " pages.");
	    
	    
	    //Get all the info for a specific book 
	    //Since in the book info in in KEY-VALUE format so we use MAP
	    Map<String, String> book1 = response.body().jsonPath().getMap("books[0]");
	    System.out.println(book1);
	    
	    
	    //get all ISBN values for all books
	    List<String> all_isbn = response.body().jsonPath().getList("books.isbn");
	    System.out.println(all_isbn);
	}
	

}
