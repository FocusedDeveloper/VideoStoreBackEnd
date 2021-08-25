package com.focuseddeveloper.videostore.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.focuseddeveloper.videostore.model.Movie;
import com.focuseddeveloper.videostore.model.Person;
import com.focuseddeveloper.videostore.service.MovieService;
import com.focuseddeveloper.videostore.service.PersonService;

//@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "*")
@CrossOrigin(origins =  Secret.FRONTEND)
@RestController
@RequestMapping("/storeapi")
public class NewMovieController {
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	PersonService personService;
	
	@GetMapping(value = "/newmovies")
	public List<Movie> getMovies(@RequestParam Map<String, String> params) {
		
		//System.out.println("Request Received");
		//System.out.println("Params: "+ params);
		List<Movie> movies = movieService.getMovies(params);
		//System.out.println(movies.toString());
		return movies;
	}
	
	@GetMapping(value = "/cast")
	public List<Person> getCast(@RequestParam String castString){
		//System.out.println("Request Received");
		//System.out.println("cast: "+ castString);
		List<Person> castList = personService.getCast(castString);
		//System.out.println(castList.toString());
		return castList;
	}
	
	@GetMapping(value = "/details")
	public Movie getMovieDetails(@RequestParam String id) {
		System.out.println("Movie Detail Request:");
		Movie movie = movieService.getMovieDetails( id);
		
		System.out.println(movie);
		return movie;
	}
	

}
