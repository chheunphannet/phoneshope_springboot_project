package com.piseth.java.school.phones_shope.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
@AllArgsConstructor	
@Builder
public class AuthUser implements UserDetails{
	
	private String username;
	private String password;
	private Boolean isAccountNonLocked;
	private Boolean isAccountNonExpired;
	private Collection<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

}
