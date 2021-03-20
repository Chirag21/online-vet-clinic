package com.onlinevet.clinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/vets")
@Controller
public class VetController {
	@RequestMapping({"","/","/vetList","/vetList.html"})
	public String listVets() {
		return "vets/vetList";
	}
}
