package com.krn.e_attendance.auth.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAutheticationFilter extends OncePerRequestFilter{
	private final JwtUtil jwtUtil;
	private final UserDetailsService userDetailsService;
	
	public JwtAutheticationFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
		this.jwtUtil=jwtUtil;
		this.userDetailsService=userDetailsService;
	}
	
	//Checking if header has Bearer token
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
		String authHeader=request.getHeader("Authorization");
		String token=null;
		String username=null;
		
		if(authHeader!=null && authHeader.startsWith("Bearer ")) {
			token=authHeader.substring(7); //removes bearer
			try {
				username=jwtUtil.extractUsername(token);
			}catch(Exception e) {
				System.out.println("Invalid JWT: "+e.getMessage());
			}
		}
		//validate token and set authentication
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails=userDetailsService.loadUserByUsername(username);
			
			if(jwtUtil.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(
						userDetails,
						null,
						userDetails.getAuthorities()
						);
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			}
		}
		filterChain.doFilter(request, response);
	}

}
