
@AddBooks
Feature: Add Books to Account API

		Background:
							Given A token is generated 
							
		Scenario: Adding books and validating the account
							When I add books to the account
							And I get the account information
							Then Ivalidate that the books are in the account
												
							