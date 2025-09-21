package com.krn.e_attendance.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class LoginResponse{
	private String username;
	private String email;
	private String token;
}