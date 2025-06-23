package com.piseth.java.school.phones_shope.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phones_shope.userService.UserService;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private  UserService userService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userService.getUserByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("user not found")); 
	}

}
