package com.focuseddeveloper.videostore.service;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.focuseddeveloper.videostore.model.Movie;
import com.focuseddeveloper.videostore.model.MovieDvd;
import com.focuseddeveloper.videostore.model.TmdbMovieParser;

@Service
public class MovieService {
	
	@Autowired
	private MovieDvdService movieDvdService;
	
	private List<Movie> movieList = new ArrayList<Movie>();
	
	// http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=fc4ecf6be846cd6edcddb660fdd2f272		
	// http://api.themoviedb.org/3/discover/movie?primary_release_date.lte=2019-3-1&api_key=fc4ecf6be846cd6edcddb660fdd2f272
	
	public Movie getMovieDetails(String movieId) {
		Movie movie = null;
		
		String queryString = MovieDBHelper.buildDetailsQuery(movieId);
		
		URL src; 
		
		try {
			src = new URL(queryString);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			TmdbMovieParser parsedMovie = objectMapper.readValue(src, TmdbMovieParser.class);
			System.out.println(parsedMovie);
			movie = new Movie(Integer.parseInt(movieId),parsedMovie);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return movie;
	}
	
	public List<Movie> getMovies(Map<String, String> params) {
		
		List<MovieDvd> movieStoreCurrentStock = movieDvdService.get();
		
		String queryString = createQueryString(params);
		//System.out.println("QueryString: "+ queryString);
		   
		ObjectMapper objectMapper = new ObjectMapper();
		URL src;
		
		try {
			src = new URL(queryString);
						
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			JsonNode jsonNode = objectMapper.readTree(src);
			List<JsonNode> nodeList = jsonNode.findParents("title");	
			
			movieList.clear();
			//System.out.println("Get Movies:");
			
			for(int i = 0; i < nodeList.size(); i++) {
				Movie movie = new Movie();
				
				int id = nodeList.get(i).get("id").asInt();
				
				if( movieStoreCurrentStock.stream().filter( theMovie -> theMovie.getId() == id ).findAny().orElse(null) == null ) {
				
					TmdbMovieParser parsedMovie = objectMapper.readValue(nodeList.get(i).traverse(), TmdbMovieParser.class);
					
					movie = new Movie(id,parsedMovie);
					movieList.add(movie);
				}
			}
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return movieList;
	}
	
	private String createQueryString(Map<String, String> params) {
		
		Integer page;
		Integer[] genres;
		Integer[] cast;
		String rated, dateReleasedBefore, dateReleasedAfter, sort;
		
		if( params.get("page")!= null) {
			page = Integer.parseInt(params.get("page"));
		}else {
			page = 1;
		}
		
		if( params.get("genres")!= null) {
			String[] integerString = params.get("genres").split(",");
			genres = new Integer[integerString.length];
			for(int i = 0; i < genres.length; i++) {
				genres[i] = Integer.parseInt(integerString[i]);
			}
		}else {
			genres = null;
		}
		
		if( params.get("cast")!= null) {
			String[] integerString = params.get("cast").split(",");
			cast = new Integer[integerString.length];
			for(int i = 0; i < cast.length; i++) {
				cast[i] = Integer.parseInt(integerString[i]);
			}
		}else {
			cast = null;
		}
		
		if( params.get("rated")!= null) {
			rated = params.get("rated");
		}else {
			rated = null;
		}
		
		if( params.get("dateReleasedBefore")!= null) {
			dateReleasedBefore = params.get("dateReleasedBefore");
		}else {
			dateReleasedBefore = null;
		}
		
		if( params.get("dateReleasedAfter")!= null) {
			dateReleasedAfter = params.get("dateReleasedAfter");
		}else {
			dateReleasedAfter = null;
		}
		
		if( params.get("sort")!= null) {
			sort = params.get("sort");
		}else {
			sort = null;
		}
		
		return MovieDBHelper.buildQuery(page, genres, cast, rated, dateReleasedBefore, dateReleasedAfter,sort ) ;
	}

}
