package com.krn.e_attendance.auth.controller;
import com.krn.e_attendance.auth.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krn.e_attendance.auth.config.JwtUtil;
import com.krn.e_attendance.auth.dto.ErrorResponse;
import com.krn.e_attendance.auth.dto.LoginRequest;
import com.krn.e_attendance.auth.dto.LoginResponse;
import com.krn.e_attendance.auth.model.User;
import com.krn.e_attendance.auth.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {
	private final UserService userService;
	private final JwtUtil jwtUtil;
	
	
	public AuthController(UserService userService, JwtUtil jwtUtil) {
		this.userService=userService;
		this.jwtUtil=jwtUtil;
	}
	
	
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user){
		try {
			User savedUser=userService.registerUser(user);
			return ResponseEntity.ok(savedUser);
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
	System.out.println("Attempting to login: " + loginRequest.getIdentifier());
	    try {
	        User user = userService.login(loginRequest);
	        String token=jwtUtil.generateToken(user.getUsername());
	        LoginResponse response=new LoginResponse(user.getUsername(), user.getEmail(), token);
	        System.out.println("Hey jude "+response);
	        return ResponseEntity.ok(token);
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(e.getMessage()));
	    }
	}
}
