package com.krn.e_attendance.auth.config;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.security.Key;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {
	private final SecretKey secretKey;
	private final long expirationMs;
	
	public JwtUtil(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}")long expirationMs) {
		this.secretKey=Keys.hmacShaKeyFor(secret.getBytes());
		this.expirationMs=expirationMs;
	}
	
	public String generateToken(String username) {
	return Jwts.builder()
			.setSubject(username)
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis()+expirationMs))
			.signWith(secretKey)
			.compact();
}
	public  boolean validateToken(String token, UserDetails userDetails) {
		final String username=extractUsername(token);
		return (username.equals(userDetails.getUsername())&&!isTokenExpired(token));
	}
public String extractUsername(String token) {
	return extractAllClaims(token).getSubject();
}
private boolean isTokenExpired(String token) {
	return extractAllClaims(token).getExpiration().before(new Date());
}

private Claims extractAllClaims(String token) {
	return Jwts.parser()
			.setSigningKey(secretKey)
			.build()
			.parseClaimsJws(token)
			.getBody();
}
}
