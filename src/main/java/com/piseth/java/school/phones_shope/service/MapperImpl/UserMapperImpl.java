package com.piseth.java.school.phones_shope.service.MapperImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phones_shope.DTO.UserDto;
import com.piseth.java.school.phones_shope.ExceptionHandle.ResourceNotFoundException;
import com.piseth.java.school.phones_shope.Mapper.UsersMapper;
import com.piseth.java.school.phones_shope.entity.Role;
import com.piseth.java.school.phones_shope.entity.Users;
import com.piseth.java.school.phones_shope.repository.RoleRepository;
@Service
public class UserMapperImpl implements UsersMapper{
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private RoleRepository roleRepo;
	
	@Override
	public Users toUser(UserDto dto) {
		if(dto == null) {
			return null;
		}
		Set<Role> roles = new HashSet<>();
		Role role = roleRepo.findByName(dto.getRole())
				.orElseThrow(() -> new ResourceNotFoundException("not found this role",HttpStatus.BAD_REQUEST));
		
		roles.add(role);
		
		Users user = new Users();
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setUsername(dto.getUsername());
		user.setPassword(encoder.encode(dto.getPassword()));
		user.setRoles(roles);
		
		return user;
	}

}
