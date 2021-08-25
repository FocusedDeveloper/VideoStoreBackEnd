package com.focuseddeveloper.videostore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.focuseddeveloper.videostore.dao.UserDAO;
import com.focuseddeveloper.videostore.model.MovieDvd;
import com.focuseddeveloper.videostore.model.MovieRentals;
import com.focuseddeveloper.videostore.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;

	@Transactional
	@Override
	public List<User> get() {
		return userDAO.get();
	}

	@Transactional
	@Override
	public User getUserById(int id) {
		
		return userDAO.get(id);
	}

	@Transactional
	@Override
	public User getUserByUserName(String name) {
		
		return userDAO.get(name);
	}

	@Transactional
	@Override
	public void save(User user) {
		userDAO.save(user);
		
	}

	@Transactional
	@Override
	public void delete(int id) {
		userDAO.delete(id);
		
	}

	@Transactional
	@Override
	public void updateRentDvd(User user, MovieDvd movie) {
		userDAO.updateRentDvd( user, movie);
		
	}

	@Transactional
	@Override
	public void updateReturnDvd(MovieRentals rental) {
		// TODO Auto-generated method stub
		rental.returnMovie();
		userDAO.updateReturnDvd(rental);
		
	}

}
