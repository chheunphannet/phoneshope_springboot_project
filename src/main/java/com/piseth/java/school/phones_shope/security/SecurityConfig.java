package com.piseth.java.school.phones_shope.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.piseth.java.school.phones_shope.role.RoleEnum;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
            	.requestMatchers("/","index.html","css/**","js/**").permitAll()
            	//.requestMatchers(HttpMethod.POST,"/brands").hasAuthority(BRAND_WRITE.getDecription())
            	//.requestMatchers(HttpMethod.GET,"/brands").hasAuthority(BRAND_READ.getDecription())
            	//.requestMatchers(HttpMethod.POST,"/model").hasAuthority(MODEL_WRITE.getDecription())
                .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults()) // Enables form-based login
            .httpBasic(Customizer.withDefaults()); // Enables HTTP Basic auth

        return http.build(); 
    }
	
	 @Bean
	    UserDetailsService userDetailsService() {
		 	UserDetails user1 = User.builder()
		         .username("sale")
		         .password(passwordEncoder().encode("123"))
		         .authorities(RoleEnum.SALE.getAuthority())
		         .build();
	        UserDetails user = User.builder()
	        	.username("admin")
	            .password(passwordEncoder().encode("123"))
	            .authorities(RoleEnum.ADMIN.getAuthority())
	            .build();
	        return new InMemoryUserDetailsManager(user,user1);
	    }
	 
	 @Bean
	 PasswordEncoder passwordEncoder() {
	     return new BCryptPasswordEncoder();
	 }

}
