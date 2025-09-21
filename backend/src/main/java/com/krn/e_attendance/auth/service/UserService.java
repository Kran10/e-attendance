package com.krn.e_attendance.auth.service;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.krn.e_attendance.auth.repository.*;
import com.krn.e_attendance.auth.dto.LoginRequest;
import com.krn.e_attendance.auth.model.*;
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
	public User login(LoginRequest loginRequest) {
		String id=loginRequest.getIdentifier();
		Optional<User> userOpt=userRepository.findByEmail(id);
		if(userOpt.isEmpty()) {
			userOpt=userRepository.findByUsername(id);
			System.out.println("Hi, "+userOpt);
		}
		User user=userOpt.orElseThrow(()->new RuntimeException("Invalid email or password"));
		if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid email or password");
		}
		return user;
	}
}
