package com.focuseddeveloper.videostore.dao;

import java.util.List;

import com.focuseddeveloper.videostore.model.MovieDvd;

public interface MovieDvdDAO {
	List<MovieDvd> get();
	
	MovieDvd get(int id);
	
	void save(MovieDvd movieDvd);
	
	void delete(int id);
	
	public void updateAddToStock(int id, int addToSTock);
	
	public void updateRentDvd(int id);
	
	public void updateReturnDvd(int id);
	
}
