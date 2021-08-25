package com.focuseddeveloper.videostore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.focuseddeveloper.videostore.dao.UserDAO;
import com.focuseddeveloper.videostore.model.MyUserDetails;
import com.focuseddeveloper.videostore.model.User;
import com.focuseddeveloper.videostore.service.UserService;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserDAO userDAO;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userDAO.get(username);
		
		//System.out.println("Authenticate User: "+username);
		
		if(user == null) {
			System.out.println("User: "+username + " does not exist.");
			throw(new UsernameNotFoundException("Not found: " + username) );
		}
		
		//System.out.println("Authenticated name: "+user.getUserName());
		//System.out.println("Authenticated roles: "+user.getUserRoles());
		
		return new MyUserDetails(user);
	}

}
