package com.focuseddeveloper.videostore.model;

public class MovieQuery {

	//Integer[] genres,Integer[] cast, String rated, String dateReleasedBefore, String dateReleasedAfter, Integer page, String sort 
	
	private Integer[] genres;
	private Integer[] cast;
	private String rated;
	private String dateReleasedBefore;
	private String dateReleasedAfter;
	Integer page;
	String sort;
	
	public MovieQuery() {
		
	}
	
	public Integer[] getGenres() {
		return genres;
	}
	public void setGenres(Integer[] genres) {
		this.genres = genres;
	}
	public Integer[] getCast() {
		return cast;
	}
	public void setCast(Integer[] cast) {
		this.cast = cast;
	}
	public String getRated() {
		return rated;
	}
	public void setRated(String rated) {
		this.rated = rated;
	}
	public String getDateReleasedBefore() {
		return dateReleasedBefore;
	}
	public void setDateReleasedBefore(String dateReleasedBefore) {
		this.dateReleasedBefore = dateReleasedBefore;
	}
	public String getDateReleasedAfter() {
		return dateReleasedAfter;
	}
	public void setDateReleasedAfter(String dateReleasedAfter) {
		this.dateReleasedAfter = dateReleasedAfter;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
}
