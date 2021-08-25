package com.focuseddeveloper.videostore.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
public class UserMovieRentalsKey {
	
	@Column(name = "user_id")
	int userId;
	
	@Column(name = "movie_id")
	int movieId;

	public UserMovieRentalsKey() {
		this.userId = 0;
		this.movieId = 0;
	}
	
	public UserMovieRentalsKey(int userId, int movieId) {
		super();
		this.userId = userId;
		this.movieId = movieId;
	}



	public int getUserId() {
		return userId;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + movieId;
		result = prime * result + userId;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserMovieRentalsKey other = (UserMovieRentalsKey) obj;
		if (movieId != other.movieId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
}
