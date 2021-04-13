package com.onlinevet.clinic.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlinevet.clinic.model.Owner;
import com.onlinevet.clinic.model.PetType;
import com.onlinevet.clinic.services.OwnerService;
import com.onlinevet.clinic.services.PetService;
import com.onlinevet.clinic.services.PetTypeService;

import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
@RequestMapping("/owners/{ownerId}")
public class PetController {
	private static final String CREATE_OR_UPDATE_PET_FORM="pets/createOrUpdatePetForm";
	
	@Autowired
	private final PetService petService;
	
	@Autowired
	private OwnerService ownerService;

	@Autowired
	private PetTypeService petTypeService;
	
	@InitBinder("owner")
	public void initOwnerBinder(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("id");
	}
	
	@ModelAttribute(name = "types")
	public Set<PetType> populatePetTypes(){
		return petTypeService.findAll();
		}
	
	@ModelAttribute(name = "owner")
	public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
		return ownerService.findById(ownerId);
	}
	
	@RequestMapping({ "", "/", "/pets", "petList", "petList.html" })
	public String listPets(Model model) {
		model.addAttribute("pets", petService.findAll());
		return "/pets/petList";
	}
}
