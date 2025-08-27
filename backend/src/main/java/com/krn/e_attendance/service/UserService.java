package com.krn.e_attendance.service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.krn.e_attendance.model.User;
import com.krn.e_attendance.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository) {
		this.userRepository=userRepository;
		this.passwordEncoder=new BCryptPasswordEncoder();
	}
	public User registerUser(User user) throws Exception{
		if(userRepository.existsByUsername(user.getUsername())) {
			throw new Exception("Username already taken");
		}
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new Exception("Email alreay registered");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
}
