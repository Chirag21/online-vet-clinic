package com.onlinevet.clinic.controllers;

import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlinevet.clinic.model.User;
import com.onlinevet.clinic.service.UserService;

@Controller
public class IndexController {

	@Autowired
	UserService userService;

	@GetMapping({ "/index", "/index.html" })
	public String indexPage(Model model, Principal principal) {
		Optional<User> userOptional = userService.findByUsername(principal.getName());
		if (userOptional.isPresent()) {
			model.addAttribute("user", userOptional.get());
		}
		return "index";
	}

	@GetMapping({ "loginSignup", "/loginSignup.html" })
	public String loginSignupPage(Model model) {
		model.addAttribute("user", User.builder().build());
		return "loginSignup";
	}

	@GetMapping({ "", "/" })
	public String getLoginPage(@RequestParam(required = false) String error, Model model) {
		if (Objects.nonNull(error))
			model.addAttribute("error", "Invalid credentials!!!");
		model.addAttribute("user", User.builder().build());
		return "appointments/login";
	}

	@GetMapping({ "/appointments/home", "/appointments/home.html" })
	public String appointmentsHomepage(Model model,Principal principal) {
		Optional<User> userOptional = userService.findByUsername(principal.getName());
		if (userOptional.isPresent()) {
			model.addAttribute("user", userOptional.get());
		}
		return "/appointments/home";
	}
}
