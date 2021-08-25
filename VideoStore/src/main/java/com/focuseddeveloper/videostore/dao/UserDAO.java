package com.focuseddeveloper.videostore.dao;

import java.util.List;

import com.focuseddeveloper.videostore.model.MovieDvd;
import com.focuseddeveloper.videostore.model.MovieRentals;
import com.focuseddeveloper.videostore.model.User;

public interface UserDAO {

	List<User> get();
	
	User get(int id);
	
	User get(String name);
	
	void save(User user);
	
	void delete(int id);

	void updateRentDvd(User user, MovieDvd movie);
	
	void updateReturnDvd(MovieRentals rental);
	
}
