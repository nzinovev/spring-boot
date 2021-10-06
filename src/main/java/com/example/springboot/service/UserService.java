package com.example.springboot.service;

import com.example.springboot.domain.dto.UserDto;
import com.example.springboot.exception.model.ApiException;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.el.stream.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserMapper userMapper;
	private final UserRepository userRepository;

	public UserDto getUser(long id) {
		var user = userRepository.findById(id)
				.orElseThrow(
						() -> new ApiException(HttpStatus.NOT_FOUND.value(), "User with id: " + id + " is not found"));
		return userMapper.mapToDto(user);
	}

	@Transactional
	public UserDto createUser(UserDto dto) {
		var user = userMapper.mapToEntity(dto);
		var savedUser = userRepository.save(user);
		return userMapper.mapToDto(savedUser);
	}

	@Transactional
	public UserDto updateUser(long id, UserDto dto) {
		var user = userRepository.findById(id)
				.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND.value(), "User with id: " + id + " is not found"));
		ofNullable(dto.getEmail()).ifPresent(user::setEmail);
		ofNullable(dto.getPassword()).ifPresent(user::setPassword);
		var updatedUser = userRepository.save(user);
		return userMapper.mapToDto(updatedUser);
	}

	public void deleteUser(long id) {
		userRepository.deleteById(id);
	}
}
