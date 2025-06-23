package com.piseth.java.school.phones_shope.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piseth.java.school.phones_shope.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{
	Optional<Users> findByUsername(String username);
}
