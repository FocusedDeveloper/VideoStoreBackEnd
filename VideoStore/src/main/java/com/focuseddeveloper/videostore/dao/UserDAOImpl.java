package com.focuseddeveloper.videostore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.focuseddeveloper.videostore.model.MovieDvd;
import com.focuseddeveloper.videostore.model.MovieRentals;
import com.focuseddeveloper.videostore.model.User;

@Repository
public class UserDAOImpl implements UserDAO{

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<User> get() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<User> query = currentSession.createQuery("from User", User.class);
		List<User> list = query.getResultList();		
		return list;
	}

	@Override
	public User get(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<User> query = currentSession.createQuery("from User WHERE id = "+id, User.class);
		User user = query.getSingleResult();
		return user;
	}

	@Override
	public User get(String name) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<User> query = currentSession.createQuery("from User WHERE user_name = '"+name+"'", User.class);
		System.out.println("SQL: " + query.toString());
		User user = query.uniqueResult();
		
		/*
		Query<MovieRentals> rentalQuery = currentSession.createQuery("from move_rentals WHERE user_id = '"+user.getId()+"'", MovieRentals.class);
		List<MovieRentals> rentalList = rentalQuery.getResultList();
		user.setRentals(rentalList);
		*/
		
		
		return user;
	}

	@Override
	public void save(User user) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(user);		
		
	}

	@Override
	public void delete(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		User user = (User)currentSession.get(User.class, id);
		currentSession.delete(user);
		
	}

	@Override
	public void updateRentDvd(User user, MovieDvd movie) {
		Session currentSession = entityManager.unwrap(Session.class);
		MovieRentals movieRental = new MovieRentals(user, movie);
		currentSession.save(movieRental);
	}

	@Override
	public void updateReturnDvd(MovieRentals rental) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.save(rental);
		
	}

}
