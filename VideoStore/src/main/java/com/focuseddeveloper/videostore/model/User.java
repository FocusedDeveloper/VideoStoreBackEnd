package com.focuseddeveloper.videostore.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "user")
public class User  {
	
	public static final int MAX_RENTALS = 3;

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name ="user_name")
	private String userName;
	
	@Column(name ="user_password")
	private String userPassword;
	
	@Column(name ="user_salt")
	private String userSalt;
	
	@Column(name ="user_roles")
	private String userRoles;
	
	@Column(name ="active")
	private boolean active;
	
	@OneToMany(mappedBy="user")
	private List< MovieRentals> rentals = new ArrayList<MovieRentals>();
	
	public List<MovieRentals> getRentals() {
		return new ArrayList<MovieRentals>(rentals);
	}
	

	
	public List<MovieDvd> getCheckedOutMovies() {
		//System.out.println("Retrieving current rentals");
		List<MovieDvd> movies = new ArrayList<MovieDvd>();
		List<MovieRentals> currentRentals = rentals.stream()
				.filter(rental -> !rental.isReturned())
				.filter(rental -> !rental.isLost())
				.collect(Collectors.toList());
		for(MovieRentals rental : currentRentals) {
			//System.out.println("Current Rental Name: "+rental.getMovie().getTitle());
			movies.add(rental.getMovie());
		}
		return movies;
	}
	
	public MovieRentals getValidRental(MovieDvd movie) {
		System.out.println("Retrieving valid rental");
		List<MovieRentals> currentRentals = rentals.stream()
									.filter(rental -> !rental.isReturned())
									.filter(rental -> !rental.isLost())
									.collect(Collectors.toList());
		
		MovieRentals currentRental = null;
		//System.out.println("Pending Movie ID: "+movie.getId());
		for(MovieRentals rental : currentRentals) {
		//	System.out.println("Current Rental Movie ID: "+rental.getMovie().getId());
			if( movie.getId().equals(rental.getMovie().getId()) ) {
				//System.out.println("Match Found");
				currentRental = rental;
			}
		}
		
		return currentRental;
	}
	
	public void addRental(MovieRentals rental) {
		rentals.add(rental);
	}
	
	public void setRentals(List<MovieRentals> rentals) {
		this.rentals = rentals.stream().collect(Collectors.toList());
	}
	
	public User() {
		this.userName = "";
		this.userPassword = "";
		this.userSalt = "";
		this.userRoles = "USER";
		this.active = true;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserSalt() {
		return userSalt;
	}
	public void setUserSalt(String userSalt) {
		this.userSalt = userSalt;
	}
	public String getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(String userRoles) {
		this.userRoles = userRoles;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userPassword=" + userPassword + ", userSalt=" + userSalt
				+ ", userRoles=" + userRoles + ", active=" + active + "]";
	}
	
	

}
