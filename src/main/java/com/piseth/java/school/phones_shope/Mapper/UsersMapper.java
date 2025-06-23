package com.piseth.java.school.phones_shope.Mapper;

import com.piseth.java.school.phones_shope.DTO.UserDto;
import com.piseth.java.school.phones_shope.entity.Users;

public interface UsersMapper {
	Users toUser(UserDto dto);
}
