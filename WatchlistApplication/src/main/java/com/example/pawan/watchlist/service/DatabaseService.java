package com.example.pawan.watchlist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pawan.watchlist.entity.Movie;
import com.example.pawan.watchlist.repository.MovieRepo;

@Service  // Services are typically used to implement business logic, computations, or calls to external APIs.
public class DatabaseService {
	@Autowired
	MovieRepo movierepo;
	
	@Autowired
	RatingService ratingService;
	
	public void create(Movie movie)
	{
		String rating =ratingService.getMovieRating(movie.getTitle());
		if(rating!=null)	
		{
			movie.setRating(Float.parseFloat(rating));
		}
		movierepo.save(movie);
	}
	public List<Movie> getAllMovies()
	{
		return movierepo.findAll(); // it will find all the movies 
	}
	public Movie getMovieById(Integer id)
	{
		return movierepo.findById(id).get();// fetching the movie by id
	}
	public void update(Movie movie, Integer id)
	{
		Movie toBeUpdated = getMovieById(id);
		toBeUpdated.setTitle(movie.getTitle());
		toBeUpdated.setRating(movie.getRating());
		toBeUpdated.setComment(movie.getComment());
		toBeUpdated.setPriority(movie.getPriority());
		
		movierepo.save(toBeUpdated);
	}
	//new
	public void delete(Integer id)
	{
		movierepo.deleteById(id);
	}
}
