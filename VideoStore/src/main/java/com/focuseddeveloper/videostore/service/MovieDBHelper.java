package com.focuseddeveloper.videostore.service;

import com.focuseddeveloper.videostore.controller.Secret;

public class MovieDBHelper {
	
	public static final String API_KEY = "api_key=";
	public static final String APIKEY = Secret.APIKEY;
	public static final String MOVIE_BASE_URL = "http://api.themoviedb.org/3/discover/movie?";
	public static final String MOVIE_DETAILS_URL = "https://api.themoviedb.org/3/movie/";
	
	public static final String PERSON_BASE_URL = "https://api.themoviedb.org/3/search/person?";
	public static final String PERSON_PREFIX = "&language=en-US&query=";
	public static final String PERSON_SUFFIX = "&page=1&include_adult=false";
	
	public static final String WITH_GENRES= "with_genres=";
	public static final String WITH_CAST= "with_cast=";
	
	public static final String RATED = "certification_country=US&certification=";
	public static final String RELEASED_AFTER = "primary_release_date.gte=";
	public static final String RELEASED_BEFORE = "primary_release_date.lte=";
	public static final String PAGE = "page=";
	
	public static final String SORT_BY_POPULARITY = "sort_by=popularity.desc";
	public static final String SORT_BY_VOTE = "sort_by=vote_average.desc";
	public static final String SORT_BY_REVENUE = "sort_by=revenue.desc";
	
	public static final String AND = "&";
	public static final String COMMA = ",";
	public static final String QUERY = "?";
	public static final String ENGLISH = "language=en-US";
	
	public static final String pattern = "yyyy-MM-dd";
	
	
	public static String buildDetailsQuery(String movieId) {	
		
		StringBuilder sb = new StringBuilder();
		sb.append(MOVIE_DETAILS_URL + movieId + QUERY +  API_KEY + APIKEY +  AND + ENGLISH);
		return sb.toString();
	}
	
	public static String buildPersonQuery(String castString) {	
		
		StringBuilder sb = new StringBuilder();
		sb.append(PERSON_BASE_URL + API_KEY + APIKEY + PERSON_PREFIX + castString + PERSON_SUFFIX);
		
		return sb.toString();
	}
	
	public static String buildQuery(Integer page, Integer[] genres,Integer[] cast, String rated, String dateReleasedBefore, String dateReleasedAfter, String sort ) {
		StringBuilder sb = new StringBuilder();
		sb.append(MOVIE_BASE_URL);
		
		boolean appended = false;
		
		if(genres!= null && genres.length > 0) {
			if(appended == true) {sb.append(AND); }
			for(int i = 0; i< genres.length; i++) {
				if(i == 0)  {
					sb.append(WITH_GENRES);
				}else {
					sb.append(COMMA);
				}
				sb.append(genres[i].toString());
				appended = true;
			}
		}
		if(cast != null && cast.length > 0) {
			if(appended == true) {sb.append(AND); }
			for(int i = 0; i< cast.length; i++) {
				if(i == 0)  {
					sb.append(WITH_CAST);
				}else {
					sb.append(COMMA);
				}
				sb.append(cast[i].toString());
				appended = true;
			}
		}
		
		if(rated != null) {
			if(appended == true) {sb.append(AND); }
			sb.append(RATED + rated);
			appended = true;
		}
		
		if(dateReleasedBefore != null) {
			if(appended == true) {sb.append(AND); }
			sb.append(RELEASED_BEFORE + dateReleasedBefore);
			appended = true;
		}
		
		if(dateReleasedAfter != null) {
			if(appended == true) {sb.append(AND); }
			sb.append(RELEASED_AFTER + dateReleasedAfter);
			appended = true;
		}
		if(page != null && page != 0) {
			if(appended == true) {sb.append(AND); }
			sb.append(PAGE + page.toString());
			appended = true;
		} 
		if(sort!=null) {
			if(appended == true) {sb.append(AND); }
			switch(sort) {
			case "popularity":
				sb.append(SORT_BY_POPULARITY);
				appended = true;
				break;
			case "vote":
				sb.append(SORT_BY_VOTE);
				appended = true;
				break;
			case "revenue":
				sb.append(SORT_BY_REVENUE);
				appended = true;
				break;
			default:
				sb.append(SORT_BY_POPULARITY);
				appended = true;
				break;
				
			}
		}
		
		if(appended == true) {sb.append(AND); }
		sb.append(API_KEY + APIKEY);
		
		return sb.toString();
	}
	
}
