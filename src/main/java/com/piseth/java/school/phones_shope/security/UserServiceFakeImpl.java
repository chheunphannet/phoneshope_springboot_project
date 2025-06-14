package com.piseth.java.school.phones_shope.security;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phones_shope.role.RoleEnum;
import com.piseth.java.school.phones_shope.userService.UserService;
@Service
public class UserServiceFakeImpl implements UserService{
	@Autowired
	private PasswordEncoder passEncoder;
	@Override
	public Optional<AuthUser> getUserByUsername() {
		List<AuthUser> authUsers = List.of(
				new AuthUser("@net", passEncoder.encode("n@1223"), true, true, RoleEnum.SALE.getAuthority()),
				new AuthUser("@eii", passEncoder.encode("e@1223"), true, true, RoleEnum.SALE.getAuthority())
				);
		return authUsers.stream().filter(u -> u.getUsername().equals(authUsers));
	}

}
