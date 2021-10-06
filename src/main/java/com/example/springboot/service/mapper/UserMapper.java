package com.example.springboot.service.mapper;

import com.example.springboot.domain.dto.UserDto;
import com.example.springboot.domain.entity.User;
import org.springframework.stereotype.Service;

@Service
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
