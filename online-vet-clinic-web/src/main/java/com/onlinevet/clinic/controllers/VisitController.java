package com.onlinevet.clinic.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.onlinevet.clinic.model.Pet;
import com.onlinevet.clinic.model.Visit;
import com.onlinevet.clinic.service.PetService;
import com.onlinevet.clinic.service.VisitService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class VisitController {

	@Autowired
	private final VisitService visitService;
	@Autowired
	private final PetService petService;

	@InitBinder
	public void dataBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");

		/*
		 * dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport()
		 * {
		 * 
		 * @Override public void setAsText(String text) throws IllegalArgumentException
		 * { setValue(LocalDate.parse(text)); } });
		 */
	}

	/**
	 * Called before each and every @RequestMapping annotated method. 2 uses: -
	 * Make sure we always have fresh data - Since we do not use the session scope,
	 * make sure that Pet object always has an id (Even though id is not part of the
	 * form fields)
	 */
	@ModelAttribute("visit")
	public Visit loadPetWithVisit(@PathVariable("petId") Long petId, Map<String, Object> model) {
		Pet pet = petService.findById(petId);
		model.put("pet", pet);
		Visit visit = new Visit();
		pet.getVisits().add(visit);
		visit.setPetId(pet.getId());
		return visit;
	}

	// Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is
	// called
	@GetMapping("/owners/*/pets/{petId}/visits/new")
	public String initNewVisitForm(@PathVariable("petId") Long petId, Map<String, Object> model) {
		return "pets/createOrUpdateVisitForm";
	}

	// Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is
	// called
	@PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
	public String processNewVisitForm(@Validated Visit visit, BindingResult result) {
		if (result.hasErrors()) {
			return "pets/createOrUpdateVisitForm";
		} else {
			visitService.save(visit);

			return "redirect:/owners/{ownerId}";
		}
	}
}
