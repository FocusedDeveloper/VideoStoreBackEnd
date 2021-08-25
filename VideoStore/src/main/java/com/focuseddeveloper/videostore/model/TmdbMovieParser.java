package com.focuseddeveloper.videostore.model;

import java.util.Date;

public class TmdbMovieParser {
	
	private String original_title;
	private String overview;
	private Date release_date;
	private String poster_path;
	private double vote_average;
	private String backdrop_path;
	
	
	
	public String getBackdrop_path() {
		return backdrop_path;
	}
	public void setBackdrop_path(String backdrop_path) {
		this.backdrop_path = backdrop_path;
	}
	public String getOriginal_title() {
		return original_title;
	}
	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public Date getRelease_date() {
		return release_date;
	}
	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}
	public String getPoster_path() {
		return poster_path;
	}
	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}
	public double getVote_average() {
		return vote_average;
	}
	public void setVote_average(double vote_average) {
		this.vote_average = vote_average;
	}
	@Override
	public String toString() {
		return "TmdbMovieParser [original_title=" + original_title + ", overview=" + overview + ", release_date="
				+ release_date + ", poster_path=" + poster_path + ", vote_average=" + vote_average + "]";
	}

	
}
