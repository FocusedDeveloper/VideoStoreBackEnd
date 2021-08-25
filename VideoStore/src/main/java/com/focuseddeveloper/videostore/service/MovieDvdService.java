package com.focuseddeveloper.videostore.service;

import java.util.List;

import com.focuseddeveloper.videostore.model.MovieDvd;

public interface MovieDvdService {
	
	List<MovieDvd> get();
	
	MovieDvd get(int id);
	
	void save(MovieDvd movieDvd);
	
	void delete(int id);
	
	public void updateAddToStock(int id, int addToSTock);
	
	public void updateRentDvd(int id);
	
	public void updateReturnDvd(int id);
}
