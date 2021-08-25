package com.focuseddeveloper.videostore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.focuseddeveloper.videostore.model.MovieDvd;

@Repository
public class MovieDvdDAOImpl implements MovieDvdDAO{
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<MovieDvd> get() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<MovieDvd> query = currentSession.createQuery("from MovieDvd", MovieDvd.class);
		List<MovieDvd> list = query.getResultList();
	
		
		
		return list;
	}

	@Override
	public MovieDvd get(int id) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		Query<MovieDvd> query = currentSession.createQuery("from MovieDvd WHERE id = "+id, MovieDvd.class);
		MovieDvd movieDvd = query.getSingleResult();
		
		return movieDvd;
	}

	@Override
	public void save(MovieDvd movieDvd) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(movieDvd);		
	}

	@Override
	public void delete(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		MovieDvd movieDvd = (MovieDvd)currentSession.get(MovieDvd.class, id);
		currentSession.delete(movieDvd);
	}
	
	@Override
	public void updateAddToStock(int id,int updatedStock) {
		Session currentSession = entityManager.unwrap(Session.class);
		MovieDvd movieDvd = (MovieDvd)currentSession.get(MovieDvd.class, id);
		currentSession.evict(movieDvd);
		//System.out.println(movieDvd);
		movieDvd.setStock(updatedStock);
		//System.out.println(movieDvd);
		currentSession.merge(movieDvd);

	}
	
	@Override
	public void updateRentDvd(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		MovieDvd movieDvd = (MovieDvd)currentSession.get(MovieDvd.class, id);
		movieDvd.rentDvd();
		currentSession.update(movieDvd);
		
	}
	
	@Override
	public void updateReturnDvd(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		MovieDvd movieDvd = (MovieDvd)currentSession.get(MovieDvd.class, id);
		movieDvd.returnDvd();
		currentSession.update(movieDvd);
	}

}
