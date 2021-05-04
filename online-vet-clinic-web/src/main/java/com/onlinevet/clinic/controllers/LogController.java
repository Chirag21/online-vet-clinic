package com.onlinevet.clinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/appointments/admin/log")
public class LogController {
	
	@GetMapping
	public String logPage() {
		return "/appointments/admin/log";
	}
}
