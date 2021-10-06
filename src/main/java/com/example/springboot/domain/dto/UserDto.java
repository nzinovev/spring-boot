package com.example.springboot.domain.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserDto {

	@Email
	@NotBlank
	String email;
	@NotBlank
	@Size(min = 5, max = 15)
	String password;
	@NotBlank
	@Size(min = 5, max = 15)
	String confirmPassword;
}
