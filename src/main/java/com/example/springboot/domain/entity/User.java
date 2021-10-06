package com.example.springboot.domain.entity;

import com.example.springboot.domain.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String email;
	private String password;

	public User() {}

	public User(UserDto dto) {
		// validation
		this.email = dto.getEmail();
		this.password = dto.getPassword();
	}
}
