package com.focuseddeveloper.videostore.model;

import org.springframework.stereotype.Component;

@Component
public class MovieActionResponse {
	public static String SUCCESS = "Success";
	public static String FAILED = "Failed";
	private String message;
	
	public MovieActionResponse() {
		message = SUCCESS;
	}
	
	public MovieActionResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void failed() {
		this.message = FAILED;
	}
	
	public void success() {
		this.message = SUCCESS;
	}
}
