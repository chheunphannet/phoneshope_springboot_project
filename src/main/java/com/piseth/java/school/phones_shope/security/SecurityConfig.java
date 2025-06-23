package com.piseth.java.school.phones_shope.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig{
	@Value("${jwt.secret}")
	private String jwtSecret;
	
	//private final UserDetailsService detailsService;
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager)
			throws Exception {
		http.csrf(csrf -> csrf.disable())
		.addFilter(jwtFilter(authenticationManager))
		.addFilterAfter(new AccessTokenVerify(jwtSecret),JwtFillter.class)
		.sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(
						authorize -> authorize.requestMatchers("/", "index.html", "css/**", "js/**").permitAll()
								// .requestMatchers(HttpMethod.POST,"/brands").hasAuthority(BRAND_WRITE.getDecription())
								// .requestMatchers(HttpMethod.GET,"/brands").hasAuthority(BRAND_READ.getDecription())
								.requestMatchers(HttpMethod.POST,"/user/create").permitAll()
								.anyRequest().authenticated())
				//.oauth2Login(Customizer.withDefaults())
				.formLogin(Customizer.withDefaults()) // Enables form-based login
				.httpBasic(Customizer.withDefaults()); // Enables HTTP Basic auth

		return http.build();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	JwtFillter jwtFilter(AuthenticationManager authenticationManager) {
		return new JwtFillter(authenticationManager, jwtSecret);
	}
	

//	@Bean
//	UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//		UserDetails user1 = User.builder()
//				.username("sale")
//				.password(passwordEncoder.encode("123"))
//				.authorities(RoleEnum.SALE.getAuthority())
//				.build();
//		UserDetails user = User.builder()
//				.username("admin")
//				.password(passwordEncoder.encode("123"))
//				.authorities(RoleEnum.ADMIN.getAuthority())
//				.build();
//		return new InMemoryUserDetailsManager(user, user1);
//	}

	@Bean
    AuthenticationProvider getAuthenticationProvider(UserDetailsService userDetailsService, 
                                                   PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }

}
