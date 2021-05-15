package com.onlinevet.clinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccessController {
    @GetMapping("/unauthorized")
    public String unauthorized() {
        return "appointments/error/403";
    }
    
	@GetMapping("/accessDenied")
	public ModelAndView accessdenied() {
		return new ModelAndView("accessDenied");
	}
}
