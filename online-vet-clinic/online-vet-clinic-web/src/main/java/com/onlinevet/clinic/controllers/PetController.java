package com.onlinevet.clinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pets")
@Controller
public class PetController {
	@RequestMapping({"","/","petList","petList.html"})
	public String listPets() {
		return "/pets/petList";
	}
}
