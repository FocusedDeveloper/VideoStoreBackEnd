package com.focuseddeveloper.videostore.security;

import java.io.Console;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private MyUserDetailsService jwtUserDetailService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Request: "+request.toString());
		
		/*
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			System.out.print("Header Name: <em>" + headerName);
			String headerValue = request.getHeader(headerName);
			System.out.print("</em>, Header Value: <em>" + headerValue);
			System.out.println("</em><br/>");
		}
		*/
		
		
		final String requestTokenHeader = request.getHeader("authorization");
		
		String username = null;
		String jwtToken = null;
		
		if(requestTokenHeader != null) {
			System.out.println("Request Header Token: "+requestTokenHeader);
			
			if(requestTokenHeader.startsWith("Bearer")) {
				System.out.println("RHT Starts with Bearer");
				
				jwtToken = requestTokenHeader.substring(7);
				System.out.println("jwtToken: "+jwtToken);
				try {
					username = jwtTokenUtil.getUsernameFromToken(jwtToken);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					System.out.println("Unable to get JWT Token");
				} catch (ExpiredJwtException e) {
					// TODO Auto-generated catch block
					System.out.println("Expired JWT Token");
				}
				
			}else {
				
				System.out.println("RHT Does Not Start with Bearer");
			}
		}else {
			logger.warn("No JWT Token");
		}
		
		
		
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
			jwtToken = requestTokenHeader.substring(7);
			System.out.println("jwtToken: "+jwtToken);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				// TODO Auto-generated catch block
				System.out.println("Expired JWT Token");
			}
		} else {
			logger.warn("No JWT Token");
		}
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			System.out.println("Made it inner loop");
			UserDetails userDetails = this.jwtUserDetailService.loadUserByUsername(username);
			
			if(jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				System.out.println("JWT Token is Valid");
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken( userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}else {
				System.out.println("JWT Token is NOT Valid");
			}
		}
		
		filterChain.doFilter(request,response);
		
	}

}
