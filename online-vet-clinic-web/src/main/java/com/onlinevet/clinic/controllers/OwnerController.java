package com.onlinevet.clinic.controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlinevet.clinic.model.Owner;
import com.onlinevet.clinic.services.OwnerService;

@RequestMapping("/owners")
@Controller
public class OwnerController {
	private final OwnerService ownerService;

	// no need for @Autowired after Spring 4.2 if there is only 1 constructor
	public OwnerController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("id");
	}

	/*
	 * @RequestMapping({ "", "/", "/owners", "/ownerList", "/ownersList.html" })
	 * public String listOwners(Model model) { model.addAttribute("owners",
	 * ownerService.findAll()); return "owners/ownerList"; }
	 */

	@GetMapping("/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
		ModelAndView mav = new ModelAndView("owners/ownerDetails");
		mav.addObject(ownerService.findById(ownerId)); // remove owner if not working
		return mav;
	}

	@RequestMapping("/find")
	public String initFindForm(Model model) {
		model.addAttribute("owner", new Owner());
		return "owners/findOwners";
	}

	@GetMapping
	public String processFindForm(Owner owner, BindingResult bindingResult, Model model) {
		// allow parameterless GET request for /owners to return all records
		if (Objects.isNull(owner.getLastName()))
			owner.setLastName("");

		List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");
		if (results.isEmpty()) {
			bindingResult.rejectValue("lastName", "notFound", "not found");
			return "owners/findOwners";
		} else if (results.size() == 1) {
			owner = results.iterator().next();
			return "redirect:/owners/" + owner.getId();
		} else {
			model.addAttribute("selections", results);
			return "owners/ownersList";
		}
	}
}
