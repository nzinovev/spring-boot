package com.example.springboot.controller;

import com.example.springboot.domain.dto.UserDto;
import com.example.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MvcController {

    private final UserService userService;

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/users")
    public String users(Model model) {
        var dtoList = userService.findAll();
        model.addAttribute("users", dtoList);
        model.addAttribute("user", new UserDto());
        return "user";
    }

    @PostMapping("/users")
    public String createUser(UserDto user, Model model) {
        userService.createUser(user);

        return "redirect:/users";
    }
}
