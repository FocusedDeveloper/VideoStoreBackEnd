package com.focuseddeveloper.videostore.service;

import java.util.List;

import com.focuseddeveloper.videostore.model.MovieDvd;
import com.focuseddeveloper.videostore.model.MovieRentals;
import com.focuseddeveloper.videostore.model.User;

public interface UserService {
	
List<User> get();
	
	User getUserById(int id);
	
	User getUserByUserName(String name);
	
	void save(User user);
	
	void delete(int id);

	public void updateRentDvd(User userId, MovieDvd movieId);
	
	public void updateReturnDvd(MovieRentals rental);
}
