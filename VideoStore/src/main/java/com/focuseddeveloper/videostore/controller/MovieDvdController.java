package com.focuseddeveloper.videostore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.focuseddeveloper.videostore.model.MovieDvd;
import com.focuseddeveloper.videostore.service.MovieDvdService;


@CrossOrigin(origins = Secret.FRONTEND)
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class MovieDvdController {
	
	@Autowired
	private MovieDvdService movieDvdService;
	
	//Return a complete list of all movies in DB
	@GetMapping(value = "/moviedvd")
	public List<MovieDvd> getMovieDvdList(){
		
		return movieDvdService.get();
	}
	
	//Return a single movie in DB by ID
	//Use a larger picture to display the image
	@GetMapping("/moviedvd/{id}")
	public MovieDvd getMovieDvd(@PathVariable int id){
		
		return movieDvdService.get(id);
	}
	
	//@CrossOrigin(origins = "http://localhost:4200")
	//Add a new movie to the DB
	@PostMapping("/moviedvd")
	public MovieDvd addMovieDvd(@RequestBody MovieDvd movieDvd){
		System.out.println("Post New DVD: "+movieDvd.getTitle());
		movieDvdService.save(movieDvd);
		return movieDvd;
	}
	
	// Movie to the DB
		@PutMapping("/moviedvd")
		public MovieDvd updateDvd(@RequestBody MovieDvd movieDvd){
			movieDvdService.save(movieDvd);
			return movieDvd;
		}
	
	@DeleteMapping("/moviedvd/{id}")
	public void removeMovieDvd(@PathVariable int id){
		movieDvdService.delete(id);
	}
	
	//Add more copies of a movie to the store
	@PutMapping("/moviedvd/{id}/{addToStock}")
	public MovieDvd updateStockMovieDvd(@PathVariable int id,@PathVariable int addToStock ){
		//System.out.print("Put Request Received. ID: "+id + " add#: "+ addToStock);
		movieDvdService.updateAddToStock(id, addToStock);
		return movieDvdService.get(id);
	}
	
	//Checkout a movie from the store
	@PutMapping("/moviedvd/checkout/{id}")
	public void rentMovieDvd(@PathVariable int id){
		movieDvdService.updateRentDvd(id);
	}
	
	//Return a movie
	@PutMapping("/moviedvd/checkin/{id}")
	public void returnMovieDvd(@PathVariable int id){
		movieDvdService.updateReturnDvd(id);
	}

}
