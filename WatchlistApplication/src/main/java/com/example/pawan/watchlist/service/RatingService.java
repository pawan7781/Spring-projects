package com.example.pawan.watchlist.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;
@Service
public class RatingService {

	String apiUrl="https://www.omdbapi.com/?apikey=a8431cee&t=";
	public String getMovieRating(String title)
	{
		try
		{	//trying to fetch the rating by calling 
			RestTemplate template=new RestTemplate();
			//apiUrl+title
			ResponseEntity<ObjectNode> response=template.getForEntity(apiUrl + title, ObjectNode.class);// responseEntity is the class who catches the request from resttemplate and keeps it to the object node
			//and objectbode is nothing but the json object
			ObjectNode jsonObject =response.getBody();//fetching response from objectnode
			return jsonObject.path("imdbRating").asText();	
		}
		catch(Exception e)
		{
			System.out.println("Either movie not available or api is down "+e.getMessage());
			return null;
		}
	}
}
