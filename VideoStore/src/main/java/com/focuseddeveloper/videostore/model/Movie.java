package com.focuseddeveloper.videostore.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Movie {
	
	private int id;
	private String title;
	private String plot;
	private double rating;
	private LocalDate releaseDate;
	private String urlThumbnail;
	private String backdrop;
	
	private static final String BASE_URL = "http://image.tmdb.org/t/p/";
	private static final String BASE_WIDTH = "w500";
	
	public Movie() {
		
	}
	
	public Movie(int id, TmdbMovieParser parsedMovie) {
		this.id = id;
		this.title = parsedMovie.getOriginal_title();
		this.plot = parsedMovie.getOverview();
		this.rating = parsedMovie.getVote_average();
		this.urlThumbnail = parsedMovie.getPoster_path();
		this.releaseDate = parseReleaseDate(parsedMovie.getRelease_date());
		this.backdrop = parsedMovie.getBackdrop_path();
	}

	private LocalDate parseReleaseDate(Date release_date) {
		LocalDate date;
		try {
			date = release_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}catch(DateTimeParseException e) {
			date = LocalDate.MIN;
		}
		return date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public void setReleaseDate(String releaseDateString) {		
		try {
			this.releaseDate = LocalDate.parse(releaseDateString);
		}catch(DateTimeParseException e) {
			this.releaseDate = LocalDate.MIN;
		}
	}


	public String getUrlThumbnail() {
		return urlThumbnail;
	}

	public void setUrlThumbnail(String urlThumbnail) {
		this.urlThumbnail = urlThumbnail;
	}

	public String getBackdrop() {
		return backdrop;
	}

	public void setBackdrop(String backdrop) {
		this.backdrop = backdrop;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", plot=" + plot + ", rating=" + rating + ", releaseDate="
				+ releaseDate + ", urlThumbnail=" + urlThumbnail + ", backdrop=" + backdrop + "]";
	}
	
	
}
