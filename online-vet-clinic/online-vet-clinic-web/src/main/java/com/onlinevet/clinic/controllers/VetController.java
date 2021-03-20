package com.onlinevet.clinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlinevet.clinic.services.VetService;

@RequestMapping("/vets")
@Controller
public class VetController {
	private final VetService vetService;

	// no need for @Autowired after Spring 4.2 if there is only 1 constructor
	public VetController(VetService vetService) {
		super();
		this.vetService = vetService;
	}

	@RequestMapping({ "", "/", "/vetList", "/vetList.html" })
	public String listVets(Model model) {
		model.addAttribute("vets", vetService.findAll());
		return "vets/vetList";
	}
}
