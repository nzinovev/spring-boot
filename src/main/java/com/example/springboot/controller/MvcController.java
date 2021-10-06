package com.example.springboot.controller;

import com.example.springboot.domain.dto.UserDto;
import com.example.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MvcController {

	private final UserService userService;

	@GetMapping("/home")
	public String homePage() {
		return "home";
	}

	@GetMapping("/find-user")
	public String findUser(@RequestParam String email, Model model) {
		var user = userService.getUserByEmail(email);
		model.addAttribute("user", user);
		return "user";
	}

	@GetMapping("/users")
	public String users(Model model) {
		var dtoList = userService.findAll();
		model.addAttribute("users", dtoList);
		return "users";
	}

	@GetMapping("/sign-up")
	public String login(@ModelAttribute(name = "user") UserDto user) {
		return "add-user";
	}

	@GetMapping("/break")
	public String breakTheApp() {
		UserDto dto = null;
		dto.getPassword();
		return "users";
	}

	@PostMapping("/users")
	public String createUser(@Valid @ModelAttribute(name = "user") UserDto user, BindingResult result) {
		if (result.hasErrors()) {
			return "add-user";
		}
		userService.createUser(user);

		return "redirect:/users";
	}
}
