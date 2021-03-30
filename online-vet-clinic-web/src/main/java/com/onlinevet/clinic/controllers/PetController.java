package com.onlinevet.clinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlinevet.clinic.services.PetService;

@RequestMapping("/pets")
@Controller
public class PetController {
	private final PetService petService;
	public PetController(PetService petService) {
		super();
		this.petService = petService;
	}
	@RequestMapping({ "", "/", "/pets", "petList", "petList.html" })
	public String listPets(Model model) {
		model.addAttribute("pets", petService.findAll());
		return "/pets/petList";
	}
}
