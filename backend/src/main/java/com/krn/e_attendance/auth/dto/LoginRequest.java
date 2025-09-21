package com.krn.e_attendance.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
	
	@Email(message="Invalid email format")
	@NotBlank(message="Identifier is required")
	private String identifier;
	
	@NotBlank(message="Password is required")
	private String password;
}
