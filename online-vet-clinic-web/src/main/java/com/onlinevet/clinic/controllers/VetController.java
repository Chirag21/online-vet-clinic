package com.onlinevet.clinic.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onlinevet.clinic.model.Vet;
import com.onlinevet.clinic.service.VetService;

@RequestMapping
@Controller
public class VetController {
	@Autowired
	private final VetService vetService;

	// no need for @Autowired after Spring 4.2 if there is only 1 constructor
	public VetController(VetService vetService) {
		this.vetService = vetService;
	}

	@RequestMapping({ "/vets", "/vets/index", "/vets/index.html", "/vets.html" })
	public String listVets(Model model) {
		model.addAttribute("vets", vetService.findAll());
		return "vets/vetList";
	}

	@GetMapping("/api/vets")
	public @ResponseBody Set<Vet> getVetsJson() {
		return vetService.findAll();
	}
	
	@GetMapping("/register-vet")
	public String getVetRegister(@ModelAttribute Vet vet, Model model) {
		return "/appointments/vet/register";
	}
}
