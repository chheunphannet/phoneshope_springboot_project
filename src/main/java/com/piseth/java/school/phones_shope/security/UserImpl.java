package com.piseth.java.school.phones_shope.security;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phones_shope.DTO.UserDto;
import com.piseth.java.school.phones_shope.ExceptionHandle.ResourceNotFoundException;
import com.piseth.java.school.phones_shope.Mapper.UsersMapper;
import com.piseth.java.school.phones_shope.entity.Permission;
import com.piseth.java.school.phones_shope.entity.Role;
import com.piseth.java.school.phones_shope.entity.Users;
import com.piseth.java.school.phones_shope.repository.RoleRepository;
import com.piseth.java.school.phones_shope.repository.UserRepository;
import com.piseth.java.school.phones_shope.userService.UserService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UserImpl implements UserService{
	 private final UserRepository repository;
	 private final UsersMapper usersMapper;
	 
	 
	@Override
	public Optional<AuthUser> getUserByUsername(String username) {
		Users users = repository.findByUsername(username).orElseThrow(() -> 
			new ResourceNotFoundException("not found : " + username, HttpStatus.NOT_FOUND));
		AuthUser user = AuthUser.builder()
				.username(users.getUsername())
				.password(users.getPassword())
				.isAccountNonExpired(users.isAccountNonExpired())
				.isAccountNonLocked(users.isAccountNonLocked())
				.authorities(getAuthority(users.getRoles()))
				.build();
		return Optional.ofNullable(user);
	}

	@Override
	public Users createUser(UserDto dto) {
		if(dto == null) {
			throw new ResourceNotFoundException("dto is null",HttpStatus.BAD_REQUEST);
		}
		
		return repository.save(usersMapper.toUser(dto));
	}
	
	private Set<SimpleGrantedAuthority> getAuthority(Set<Role> roles){
		Set<SimpleGrantedAuthority> collect = roles.stream()
				.map(r -> new SimpleGrantedAuthority("ROLE_"+ r.getName()))
				.collect(Collectors.toSet());
		
		Set<SimpleGrantedAuthority> collects = roles.stream()
				.flatMap(role -> toStream( role.getPermissions()) )
				.collect(Collectors.toSet()); 
		 
		 collects.addAll(collect);
		 
		return collects;
	}
	
	private Stream<SimpleGrantedAuthority> toStream(Set<Permission> permission){
		return permission.stream()
				.map(p -> new SimpleGrantedAuthority(p.getName()));
	}
}
