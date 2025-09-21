package com.krn.e_attendance.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.krn.e_attendance.auth.model.User;
import com.krn.e_attendance.auth.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		User user=userRepository.findByEmail(usernameOrEmail)
				.orElseGet(()->userRepository.findByUsername(usernameOrEmail).orElseThrow(()->new UsernameNotFoundException("User not found")));
	return new CustomUserDetails(user);
	}
	
}
