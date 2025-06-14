package com.piseth.java.school.phones_shope.userService;

import java.util.Optional;

import com.piseth.java.school.phones_shope.security.AuthUser;

public interface UserService {
	Optional<AuthUser> getUserByUsername(String username);
}
