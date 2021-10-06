package com.example.springboot.domain.dto;

import lombok.Data;

@Data
public class UserDto {

    String email;
    String password;
    String confirmPassword;
}
