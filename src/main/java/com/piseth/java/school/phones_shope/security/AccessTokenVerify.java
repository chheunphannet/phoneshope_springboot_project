package com.piseth.java.school.phones_shope.security;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccessTokenVerify extends OncePerRequestFilter {
	private final String jwtSecret;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// 1. It looks for the "Authorization" header in your request to /brands.
		String authorizationHeader = request.getHeader("Authorization");

		// 2. If the header is not found OR it doesn't start with "Bearer ", 
		//    it does nothing and proceeds. Spring Security then sees no authentication
		//    and returns a 401 Unauthorized error. THIS IS WHAT IS HAPPENING.
		if (Objects.isNull(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")
				|| authorizationHeader.isBlank()) {
			filterChain.doFilter(request, response);
			return;
		}

		// 3. If you provide the header correctly, this code will run,
		//    validate your token, and grant you access.
		String token = authorizationHeader.replace("Bearer ", "");
		Claims signedClaims = Jwts.parser()
				.verifyWith(Keys.hmacShaKeyFor(jwtSecret.getBytes())) 
				.build().parseSignedClaims(token).getPayload();
		
		String username = signedClaims.getSubject(); //get user_name
		List<Map<String, String>> authorities = (List<Map<String, String>>) signedClaims.get("authorities");

		Set<SimpleGrantedAuthority> grantedAuthorities;
		
        if (authorities != null) {
            grantedAuthorities = authorities.stream()
                    .map(authMap -> new SimpleGrantedAuthority(authMap.get("authority")))
                    .collect(Collectors.toSet());
        } else {
            grantedAuthorities = Set.of();
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
	}

}