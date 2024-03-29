package com.example.pawan.watchlist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.pawan.watchlist.entity.Movie;
import com.example.pawan.watchlist.service.DatabaseService;

import jakarta.validation.Valid;


@RestController //@RestController is used in REST style controllers and returns the JSON/XML response directly to the client.
public class MovieController {
	@Autowired
	DatabaseService databaseService;
	@GetMapping("/watchlistItemForm")   //for fetching the submitform (/watchlistItemForm)
	public ModelAndView showWatchlistForm( @RequestParam(required = false) Integer id)
	{
		
		String viewName="watchlistItemForm";
		
		Map<String, Object> model = new HashMap<>();
		if(id==null)
		{
			model.put("watchlistItem", new Movie());
		}
		else
		{
			model.put("watchlistItem", databaseService.getMovieById(id));
		}
		
		return new ModelAndView(viewName, model); //controller  returns Model and view name
		
	}
	
	@PostMapping("/watchlistItemForm") //we are entering the data on the same form that's why we used the same URL ()/watchlistItemForm
	//this method will post the movies
	public ModelAndView submitWatchlistForm(@Valid @ModelAttribute("watchlistItem") Movie movie, BindingResult bindingResult)//___ , BindingResult bindingResult
	{
		if(bindingResult.hasErrors())
		{
			return new ModelAndView("watchlistItemForm");
		}
		Integer id = movie.getId();
		if(id==null)
		{
			databaseService.create(movie); //creating movie in database for submitting the movie
		}
		else
		{
			databaseService.update(movie, id);
		}
		
		RedirectView rd = new RedirectView();//performing an action - as user click on submit button it will redirect to the watchlist page using redirect class by creating object
		rd.setUrl("/watchlist"); //url - watchlist page
		return new ModelAndView(rd); //returning model and view with redirect object
	}
	
	@GetMapping("/watchlist")
	public ModelAndView getWatchlist( Integer id)//for getting movies (rows) and number og movies to watch
	{
		String viewName="watchlist";
		Map<String, Object> model= new HashMap<>();
		List<Movie> movieList= databaseService.getAllMovies();//stored all the movies in the list from the database
		model.put("watchlistrows", movieList);
		
		model.put("noofmovies", movieList.size());
		return new ModelAndView(viewName, model);
	}

	
	@GetMapping("/watchlist/{id}")
	public RedirectView deletemovie( @PathVariable Integer id )
	{
		databaseService.delete(id);
		RedirectView redirectView = new RedirectView("/watchlist");
		return redirectView;
	}

}