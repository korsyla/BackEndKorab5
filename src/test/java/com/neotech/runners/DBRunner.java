package com.neotech.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions
(
		//which .features/or Test Suits we want	to RUN
		//Here is the path to the feature folder we gona run / we can also specify which feature we want to run. like = "src/test/resources/features/LoginToHRM.feature",
		features = "src/test/resources/features",
		
		//We can specify where the code/implementation for the features are located
		glue = "com.neotech.steps",
		
		//If true, it ONLY checks if all the steps/features are implemented like When, And, Then etc, and NOT the TEST
		//If false, than it will run the test
		dryRun = false,
		
		//tags is same as saying groups in TestNG
		//Here, ONLY tags with @tagType test will RUN!
		//If we don't specify the tags, than it will run all features/testcases
		tags = "@AddBooks",
		
		monochrome = true,
		
		
		//This will print the steps of scenario in the CONSOLE
		plugin = { 
					//"pretty" -> Prints the gherkin steps to the console 
//					"pretty"
									
				//This Creates and saves the BASIC html report on our TARGET Folder
				"html:target/cucumber-default-report.html",
				
				//This ADVANCED report stores every step of the execution in the json file in our TARGET Folder
				"json:target/cucumber.json"
				
									}

		)




public class DBRunner {

}
