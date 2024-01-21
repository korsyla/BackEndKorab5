package com.neotech.api.lesson05;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TopMovies {
	
	@Test
	public void compareMovies() 
	{
		RestAssured.baseURI = "http://199.83.14.77:8080";
		
		Response top100Response = RestAssured
				.given()
					.queryParam("count", "30")
				.when()
					.get("/api/v1/topMovies");
		
		
//		top100Response.prettyPeek();
		//("") -> this gives us the whole list of movies and not a specific one
		int size = top100Response.body().jsonPath().getList("").size();

		System.out.println("------------------------------------------------------------------------------------");
		
		
		for(int i = 0; i < size; i++) 
		{
			String id = top100Response.body().jsonPath().getString("[" + i +"].id");
			int rank = top100Response.body().jsonPath().getInt("[" + i +"].rank");
			String title = top100Response.body().jsonPath().getString("[" + i +"].title");
			String releaseYear = top100Response.body().jsonPath().getString("[" + i +"].releaseYear");
			
			Response innerMovie = RestAssured
					.given()
						.pathParam("id", id)
					.when()
						.get("/api/v1/movie/{id}");
			
//			innerMovie.prettyPeek();
			
			String innerId = innerMovie.body().jsonPath().getString("id");
			int innerRank = innerMovie.body().jsonPath().getInt("rank");
			String innertitle = innerMovie.body().jsonPath().getString("title");
			String innerReleaseYear = innerMovie.body().jsonPath().getString("releaseYear");
			
			
			System.out.println("Comparing the info of the movie with rank -> " + rank);
			
			if(!id.equals(innerId)) 
			{
				System.err.println("ID's are different! " + id + " != " + innerId );
			}
			
			if(rank != innerRank) 
			{
				System.err.println("ID's are different! " + rank + " != " + innerRank );
			}
			
			if(!title.equals(innertitle)) 
			{
				System.err.println("ID's are different! " + title + " != " + innertitle );
			}
			
			if(!releaseYear.equals(innerReleaseYear)) 
			{
				System.err.println("ID's are different! " + releaseYear + " != " + innerReleaseYear );
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
	}
	
	
	
	

}
