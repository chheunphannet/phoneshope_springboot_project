package com.piseth.java.school.phones_shope.security;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class JwtFillter extends UsernamePasswordAuthenticationFilter {
	
	private final AuthenticationManager authenticationManager;
	private final String key;
	
	public JwtFillter(AuthenticationManager authenticationManager,String key) {
        this.authenticationManager = authenticationManager;
        this.key = key;
        super.setAuthenticationManager(authenticationManager);
    }

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			LoginRequest loginValue = mapper.readValue(request.getInputStream(), LoginRequest.class);
			Authentication authentication = new UsernamePasswordAuthenticationToken(loginValue.getUsername(),
					loginValue.getPassword());
			Authentication authenticate = authenticationManager.authenticate(authentication);
			return authenticate;
		} catch (IOException e) {
			throw new BadCredentialsException("Invalid login request format", e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
			if (key == null || key.trim().isEmpty()) {
	            throw new IllegalStateException("JWT secret key is not configured. Please set jwt.secret in application.properties");
	        }
			String token = Jwts.builder()
					.subject(authResult.getName())
					.issuedAt(new Date())
					.claim("authorities", authResult.getAuthorities()) //get permission what can do?
				    .expiration(java.sql.Date.valueOf(LocalDate.now().plusDays(7)))
					.issuer("phoneshop.com")
					.signWith(Keys.hmacShaKeyFor(key.getBytes()))
					.compact();
			response.setHeader("Authorization","Bearer " + token);
	}

}
