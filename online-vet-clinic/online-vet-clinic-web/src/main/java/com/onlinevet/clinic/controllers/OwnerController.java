package com.onlinevet.clinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlinevet.clinic.services.OwnerService;

@RequestMapping("/owners")
@Controller
public class OwnerController {
	private final OwnerService ownerService;

	// no need for @Autowired after Spring 4.2 if there is only 1 constructor
	public OwnerController(OwnerService ownerService) {
		super();
		this.ownerService = ownerService;
	}

	@RequestMapping({ "", "/", "/ownerList", "/ownerList.html" })
	public String listOwners(Model model) {
		model.addAttribute("owners", ownerService.findAll());
		return "owners/ownerList";
	}
}
