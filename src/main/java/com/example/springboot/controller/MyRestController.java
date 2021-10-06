package com.example.springboot.controller;

import com.example.springboot.domain.dto.UserDto;
import com.example.springboot.exception.model.ApiException;
import com.example.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MyRestController {

	private final UserService userService;

	@GetMapping("/users/{id}")
	public UserDto getUser(@PathVariable Long id) {
		return userService.getUser(id);
	}

	@PostMapping("/users")
	@ResponseStatus(value = HttpStatus.CREATED)
	public UserDto createUser(@Valid @RequestBody UserDto dto) {
		if (!dto.getPassword()
		        .equals(dto.getConfirmPassword())) {
			throw new ApiException(HttpStatus.BAD_REQUEST.value(), "Passwords doesn't match");
		}
		return userService.createUser(dto);
	}

	@PutMapping("/users/{id}")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public UserDto updateUser(@PathVariable Long id, @RequestBody @Valid UserDto dto) {
		return userService.updateUser(id, dto);
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}
}
