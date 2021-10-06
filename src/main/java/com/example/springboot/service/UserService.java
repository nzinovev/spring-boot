package com.example.springboot.service;

import com.example.springboot.domain.dto.UserDto;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> findAll() {
        var users = userRepository.findAll();
        return users.stream()
                .map(userMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public void createUser(UserDto dto) {
        var user = userMapper.mapToEntity(dto);
        userRepository.save(user);
    }
}
