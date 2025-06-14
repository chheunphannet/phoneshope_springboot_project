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
		String authorizationHeader = request.getHeader("Authorization");
		if (Objects.isNull(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")
				|| authorizationHeader.isBlank()) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = authorizationHeader.replace("Bearer ", "");
		Claims signedClaims = Jwts.parser()
				.verifyWith(Keys.hmacShaKeyFor(jwtSecret.getBytes())) // Use verifyWith()																				// setSigningKey()
				.build().parseSignedClaims(token).getPayload();
		
		String username = signedClaims.getSubject();
		List<Map<String, String>> authorities = (List<Map<String, String>>) signedClaims.get("authorities");

		Set<SimpleGrantedAuthority> grantedAuthorities;
        if (authorities != null) {
            grantedAuthorities = authorities.stream()
                    .map(authMap -> new SimpleGrantedAuthority(authMap.get("authority"))) // or whatever key contains the role
                    .collect(Collectors.toSet());
        } else {
            grantedAuthorities = Set.of(); // or Set.of(new SimpleGrantedAuthority("ROLE_USER"))
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
	}

}
