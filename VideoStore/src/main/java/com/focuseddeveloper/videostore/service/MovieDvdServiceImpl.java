package com.focuseddeveloper.videostore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.focuseddeveloper.videostore.dao.MovieDvdDAO;
import com.focuseddeveloper.videostore.model.MovieDvd;

@Service

public class MovieDvdServiceImpl implements MovieDvdService{
	
	@Autowired
	private MovieDvdDAO movieDvdDAO;

	@Transactional
	@Override
	public List<MovieDvd> get() {
		return movieDvdDAO.get();
	}

	@Transactional
	@Override
	public MovieDvd get(int id) {
		return movieDvdDAO.get(id);
	}

	@Transactional
	@Override
	public void save(MovieDvd movieDvd) {
		movieDvdDAO.save(movieDvd);
		
	}

	@Transactional
	@Override
	public void delete(int id) {
		movieDvdDAO.delete(id);
		
	}

	@Override
	public void updateAddToStock(int id, int addToStock) {
		addToStock = MovieDvd.validateAddToStock(addToStock);
		movieDvdDAO.updateAddToStock(id, addToStock);
	}

	@Override
	public void updateRentDvd(int id) {
		movieDvdDAO.updateRentDvd(id);
		
	}

	@Override
	public void updateReturnDvd(int id) {
		movieDvdDAO.updateRentDvd(id);
		
	}
	
}
