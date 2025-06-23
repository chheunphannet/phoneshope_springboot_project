package com.piseth.java.school.phones_shope.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity(name = "Users")
@Data
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private boolean isAccountNonLocked = true;
	private boolean isAccountNonExpired = true;
//	
//	private RoleEnum role;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;
}
