package com.onlinevet.clinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.onlinevet.clinic.model.Owner;
import com.onlinevet.clinic.model.User;
import com.onlinevet.clinic.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {
	UserService userService;
	
	// no need for @Autowired after Spring 4.2 if there is only 1 constructor
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/doLogin")
	public String doLogin(@Validated User user, BindingResult result) {
		if(result.hasErrors()) {
			log.debug("In doLogin() - Has Errors!");
			return "/loginSignup";
		}
		log.debug("Username " + user.getUserName());
		return "/index";
	}
}
