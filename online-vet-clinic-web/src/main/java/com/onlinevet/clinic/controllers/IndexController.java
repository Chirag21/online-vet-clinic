package com.onlinevet.clinic.controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.onlinevet.clinic.model.User;
import com.onlinevet.clinic.services.UserService;
import com.onlinevet.clinic.model.User;

@Controller
public class IndexController {
	
	@Autowired
	UserService userService;

	@GetMapping({ "/index", "/index.html" })
	public String indexPage(Model model,Principal principal) {
		Optional<User> userOptional = userService.findByUsername(principal.getName());
		if(userOptional.isPresent()) {
			model.addAttribute("user", userOptional.get());
		}
		return "index";
	}

	@GetMapping({ "", "/","/loginSignup","/loginSignup.html" })
		model.addAttribute("user",User.builder().build());
		return "loginSignup";
	}
}
