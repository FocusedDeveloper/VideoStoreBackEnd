package com.focuseddeveloper.videostore.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.focuseddeveloper.videostore.model.MovieActionResponse;
import com.focuseddeveloper.videostore.model.MovieDvd;
import com.focuseddeveloper.videostore.model.MovieRentals;
import com.focuseddeveloper.videostore.model.User;
import com.focuseddeveloper.videostore.service.MovieDvdService;
import com.focuseddeveloper.videostore.service.UserService;

@CrossOrigin(origins =  Secret.FRONTEND)
						
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping({ "/user"})
public class UserController {
	
	@Autowired
	MovieActionResponse actionRepsonse;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MovieDvdService movieDvdService;
	
	@GetMapping(produces = "application/json")
	public User getUser(Principal principal)  {
		User user = userService.getUserByUserName(principal.getName());
		return user;
	}
	
	@GetMapping({"/all"})
	public List<User> getAllUsers(){
		return userService.get();
	}
	  
	@PostMapping
	public User addUser(@RequestBody User user) {
		System.out.println("Incoming User Name: "+user.toString());
		User newUser = new User();
		newUser.setUserName(user.getUserName());
		newUser.setUserPassword(user.getUserPassword());
		System.out.println("New User Name: "+newUser.toString());
		
		if(user.getUserName().toUpperCase().startsWith("ADMIN")) {
			user.setUserRoles("ADMIN");
		}
		userService.save(newUser);
		return user;
	}
	
	@PutMapping
	public User updateUser(@RequestBody User user) {
		userService.save(user);
		return user;
	}
	
	@DeleteMapping
	public void deleteUser(@RequestBody User user) {
		userService.delete(user.getId());
	}
	
	//Checkout a movie from the store
	@PutMapping("/checkout")
	public MovieActionResponse checkOutMovie(Principal principal, @RequestBody MovieDvd movie) {
		User user = userService.getUserByUserName(principal.getName());

		//MovieDvd movie = movieDvdService.get(movieId);
		if(movie.getStock() > 0) {
			movieDvdService.updateRentDvd(movie.getId());	
			userService.updateRentDvd(user, movie);
			actionRepsonse.success();
		}else {
			actionRepsonse.failed();
		}
		return actionRepsonse;
	}
	
	//Return a movie to the store
		@PutMapping("/return")
		public MovieActionResponse returnMovie(Principal principal, @RequestBody MovieDvd movie) {
			User user = userService.getUserByUserName(principal.getName());
			System.out.println("Returning movie");
			//MovieDvd movie = movieDvdService.get(movieId);\
			MovieRentals rental = user.getValidRental(movie);
			
			if(rental != null) {
				System.out.println("Rental Found.  See Next Line for Details");
				System.out.println(rental);
				//movieDvdService.updateReturnDvd(movie.getId());
				userService.updateReturnDvd(rental);
				actionRepsonse.success();
			}else {
				actionRepsonse.failed();
			}
			return actionRepsonse;
		}
	
	@GetMapping({"/checkedOutMovies"})
	public List<MovieDvd> getCheckedOutMovies(Principal principal){
		System.out.println("USER Controller: GetCheckoutedOutMovies");
		User user = userService.getUserByUserName(principal.getName());
		return user.getCheckedOutMovies();
	}
	
	

}
