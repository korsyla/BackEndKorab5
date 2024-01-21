
@OneBook
Feature: Get one book API
					This feature will fetch one book using the endpoint in documentation
					
					Background:
									Given I create a request
									And I provide the ISBN  "9781449325862" as the query parameter


					Scenario: Get one book and validate the response
										Given I create a request
										And I provide the ISBN  "9781449325862" as the query parameter
										When I make a GET call to the Get One Book endpoint
										Then I validate that the status code is 200
										And I validate that the ISBN in the response body is "9781449325862"
										And I validate that the book title is "Git Pocket Guide"
