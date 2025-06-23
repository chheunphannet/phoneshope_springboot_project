package com.piseth.java.school.phones_shope.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piseth.java.school.phones_shope.DTO.UserDto;
import com.piseth.java.school.phones_shope.userService.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	private final UserService serviceUser;
	
	@PostMapping("/create")
	public ResponseEntity<?> createUser(@org.springframework.web.bind.annotation.RequestBody UserDto dto){
		return ResponseEntity.ok(serviceUser.createUser(dto));
	}
}
