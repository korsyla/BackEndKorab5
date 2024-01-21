
@GenerateToken
Feature: Generate Token API

			Background:
								Given I create a token request 
								And I provide the header information
								And I provide the body payload information
								
			Scenario:
								When I make a POST request to generate token endpoint
								Then I  validate the status code is 200
								And I validate that the body contains "Success"	
								And I validate that the "result" is "User authorized successfully."