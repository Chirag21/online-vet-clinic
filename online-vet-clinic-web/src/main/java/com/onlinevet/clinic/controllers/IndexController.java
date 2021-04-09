package com.onlinevet.clinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlinevet.clinic.model.User;

@Controller
public class IndexController {

	@RequestMapping({ "/index", "/index.html" })
	public String indexPage() {
		return "index";
	}

	@RequestMapping({ "", "/" })
	public String loginSignupPage(Model model) {
		model.addAttribute("user",User.builder().build());
		return "loginSignup";
	}
}
