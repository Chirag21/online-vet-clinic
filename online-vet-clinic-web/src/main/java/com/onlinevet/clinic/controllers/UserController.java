package com.onlinevet.clinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.onlinevet.clinic.model.User;
import com.onlinevet.clinic.services.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
	UserService userService;
	
	@PostMapping("/doLogin")
	public String doLogin(@ModelAttribute("user") User user, Model model) {
		log.debug("USERNAME = " + user);
		System.out.println("User = " + user);
		return "redirect:/index";
	}
}
