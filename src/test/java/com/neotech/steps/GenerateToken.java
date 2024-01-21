package com.neotech.steps;

import org.hamcrest.Matchers;

import com.neotech.utilities.APIConstants;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GenerateToken {
	
	
	// Instance variables
		 RequestSpecification request;
		 Response response;
		 
	@Given("I create a token request")
	public void i_create_a_token_request() 
	{
		   RestAssured.baseURI = APIConstants.BASE_URI;
		   request = RestAssured.given();
	}	 
	
	@Given("I provide the header information")
	public void i_provide_the_header_information() 
	{
	    request.contentType(ContentType.JSON);
	    //Another way- but its not preffered
//	  	request.header("Content-Type", "application/json");
	}
	
	@Given("I provide the body payload information")
	public void i_provide_the_body_payload_information() 
	{
	    String payload = "{\r\n"
	    		+ "  \"userName\": \"TrentA\",\r\n"
	    		+ "  \"password\": \"Trent@123\"\r\n"
	    		+ "}";
	    
	    request.body(payload);
	}
	
	@When("I make a POST request to generate token endpoint")
	public void i_make_a_post_request_to_generate_token_endpoint() 
	{
	    response = request.when().post(APIConstants.GENERATE_TOKEN_ENDPOINT);
	    response.prettyPeek(); // We do this just to see it we don't have to prettypeek its OPTIONAL
	}
	
	@Then("I  validate the status code is {int}")
	public void i_validate_the_status_code_is(Integer statusCode) 
	{
	    response.then().assertThat().statusCode(statusCode);
	}
	
	@Then("I validate that the body contains {string}")
	public void i_validate_that_the_body_contains(String success) 
	{
	    response.then().assertThat().body(Matchers.containsString(success)); // This makes more sense for whats requiered
	    //OR
//	    response.then().assertThat().body("status", Matchers.equalTo(success));
	}
	
	@Then("I validate that the {string} is {string}")
	public void i_validate_that_the_is(String key, String value) //key - refers to "result" .. value refers to "User authorized successfully."  
	{
	    response.then().assertThat().body(key, Matchers.equalTo(value));
	}

}
