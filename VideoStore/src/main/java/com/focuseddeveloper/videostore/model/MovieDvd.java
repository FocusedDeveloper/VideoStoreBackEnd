package com.focuseddeveloper.videostore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_physical_movies")
public class MovieDvd {
	
	@Id
	@Column
	private Integer id;
	
	@Column
	private String title;
	@Column
	private int stock;
	@Column(name = "rented_out")
	private int rentedOut;
	@Column(name = "total_rent_count")
	private int totalTimesRented;
	@Column(name = "url_thumbnail")
	private String urlThumbnail;
	
	//private static final String BASE_URL = "http://image.tmdb.org/t/p/";
	//private static final String BASE_WIDTH = "w150";
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getRentedOut() {
		return rentedOut;
	}
	public void setRentedOut(int rentedOut) {
		this.rentedOut = rentedOut;
	}
	public int getTotalTimesRented() {
		return totalTimesRented;
	}
	public void setTotalTimesRented(int totalTimesRented) {
		this.totalTimesRented = totalTimesRented;
	}
	
	public void addStock(int addToStock) {
		addToStock = validateAddToStock(addToStock);
		this.stock+=addToStock;
	}
	
	static public int validateAddToStock(int toAdd) {
		if(toAdd > 50 || toAdd < 1) {
			toAdd = 10;
		}
		return toAdd;
	}
	
	public boolean rentDvd() {
		if(stock > 0) {
			this.totalTimesRented++;
			this.stock--;
			this.rentedOut++;
			return true;
		}
		return false;
	}
	
	public void returnDvd() {
		if(rentedOut > 0) {
			this.stock++;
			this.rentedOut--;
		}
	}
	
	@Override
	public String toString() {
		return "MovieDvd [id=" + id + ", title=" + title + ", stock=" + stock + ", rentedOut=" + rentedOut
				+ ", totalTimesRented=" + totalTimesRented + "]";
	}
	
	public String getUrlThumbnail() {
		return urlThumbnail;
	}

	public void setUrlThumbnail(String urlThumbnail) {
		this.urlThumbnail = urlThumbnail;
	}
	
	

}
