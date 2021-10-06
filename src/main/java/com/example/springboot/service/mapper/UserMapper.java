package com.example.springboot.service.mapper;

import com.example.springboot.configuration.mapper.Mapper;
import com.example.springboot.domain.dto.UserDto;
import com.example.springboot.domain.entity.User;

@Mapper
public class UserMapper {

	public UserDto mapToDto(User user) {
		var dto = new UserDto();
		dto.setEmail(user.getEmail());
		dto.setPassword(user.getPassword());
		return dto;
	}

	public User mapToEntity(UserDto dto) {
		return new User(dto);
	}
}
