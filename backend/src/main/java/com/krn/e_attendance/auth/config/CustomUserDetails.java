package com.krn.e_attendance.auth.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.krn.e_attendance.auth.model.User;

public class CustomUserDetails implements UserDetails{
	private final User user;
	
	public CustomUserDetails(User user) {
		this.user=user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override 
	public boolean isEnabled() {
		return true;
	}
	public User getUser() {
		return user;
	}
	public String getEmail() {
		return user.getEmail();
	}
}
