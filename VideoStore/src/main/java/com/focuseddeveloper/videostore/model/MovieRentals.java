package com.focuseddeveloper.videostore.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="movie_rentals")
public class MovieRentals {
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	@Column(name = "rental_id")
	Integer rentalId;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
	
	
	@OneToOne()
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private MovieDvd movie;
	
	
	@Column(name = "date_rented")
	private LocalDate dateRented;
	
	@Column(name = "returned")
	private boolean returned;
	
	@Column(name = "date_returned")
	private LocalDate dateReturned;
	
	@Column(name = "lost")
	private boolean lost;
	

	public MovieRentals() {
		this.user = null;
		this.movie = null;
		initDates();
	}
	
	public MovieRentals(User user,MovieDvd movie) {
		this.user = user;
		this.movie = movie;
		initDates();
	}

	public MovieRentals(int userId, int movieId, LocalDate dateRented, boolean returned,
			LocalDate dateReturned, boolean lost) {
		super();
		this.dateRented = dateRented;
		this.returned = returned;
		this.dateReturned = dateReturned;
		this.lost = lost;
	}
	
	private void initDates() {
		this.dateRented = LocalDate.now();
		this.returned = false;
		this.dateReturned = null;
		this.lost = false;
	}
	
	public void returnMovie() {
		this.returned = true;
		this.dateReturned = LocalDate.now();
	}
	
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MovieDvd getMovie() {
		return movie;
	}

	public void setMovie(MovieDvd movie) {
		this.movie = movie;
	}


	public LocalDate getDateRented() {
		return dateRented;
	}

	public void setDateRented(LocalDate dateRented) {
		this.dateRented = dateRented;
	}

	public boolean isReturned() {
		return returned;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}

	public LocalDate getDateReturned() {
		return dateReturned;
	}

	public void setDateReturned(LocalDate dateReturned) {
		this.dateReturned = dateReturned;
	}

	public boolean isLost() {
		return lost;
	}

	public void setLost(boolean lost) {
		this.lost = lost;
	}

	public Integer getRentalId() {
		return rentalId;
	}

	public void setRentalId(Integer rentalId) {
		this.rentalId = rentalId;
	}

	@Override
	public String toString() {
		return "MovieRentals [rentalId=" + rentalId + ", user=" + user + ", movie=" + movie + ", dateRented="
				+ dateRented + ", returned=" + returned + ", dateReturned=" + dateReturned + ", lost=" + lost + "]";
	}
	
	
	
}
