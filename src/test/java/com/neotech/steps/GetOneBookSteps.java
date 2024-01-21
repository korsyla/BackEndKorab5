package com.neotech.steps;

import org.hamcrest.Matchers;

import com.neotech.utilities.APIConstants;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetOneBookSteps {
	
	// Instance variables
	 RequestSpecification request;
	 Response response;
	 
	
	@Given("I create a request")
	public void i_create_a_request() 
	{
	    RestAssured.baseURI = APIConstants.BASE_URI;
	    request = RestAssured.given();
	}
	
	@Given("I provide the ISBN  {string} as the query parameter")
	public void i_provide_the_isbn_as_the_query_parameter(String isbn) //The isbn comes from feature file "9781449325862"
	{
	    request.queryParam("ISBN", isbn);
	}
	
	@When("I make a GET call to the Get One Book endpoint")
	public void i_make_a_get_call_to_the_get_one_book_endpoint() 
	{
	    response = request.when().get(APIConstants.GET_ONE_BOOK_ENDPOINT);
	    response.prettyPeek();//This will print what it looks like
	}
	
	@Then("I validate that the status code is {int}")
	public void i_validate_that_the_status_code_is(Integer statusCode) //The status code comes from feature file "200" 
	{
	    response.then().assertThat().statusCode(statusCode);
	}
	
	@Then("I validate that the ISBN in the response body is {string}")
	public void i_validate_that_the_isbn_in_the_response_body_is(String isbn) 
	{
		//1st wayy
	    response.then().assertThat().body("isbn", Matchers.equalTo(isbn));
	    //2nd wa
	}
	
	@Then("I validate that the book title is {string}")
	public void i_validate_that_the_book_title_is(String title) 
	{
//	    response.then().assertThat().body("title", Matchers.equalTo(title));
	    //OR
	    response.then().assertThat().body("title", Matchers.containsString(title));
	}
	

}
