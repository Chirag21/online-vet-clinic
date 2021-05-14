package com.onlinevet.clinic.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlinevet.clinic.model.Owner;
import com.onlinevet.clinic.model.Vet;
import com.onlinevet.clinic.service.OwnerService;
import com.onlinevet.clinic.service.VetService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@AllArgsConstructor
public class OwnerController {
	private static final String OWNER = "owner";
	private static final String REDIRECT_OWNERS = "redirect:/owners/";
	private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";

	@Autowired
	private final OwnerService ownerService;

	@Autowired
	private final VetService vetService;

	@InitBinder
	public void setAllowedFields(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("id");
	}

	/*
	 * @RequestMapping({ "", "/", "/owners", "/ownerList", "/ownersList.html" })
	 * public String listOwners(Model model) { model.addAttribute("owners",
	 * ownerService.findAll()); return "owners/ownerList"; }
	 */

	@GetMapping(path = "/register-owner")
    public String getOwnerRegister(@ModelAttribute Owner owner, Model model) {
        Set<Vet> vets = vetService.findAll();
        model.addAttribute("vets", vets);
        return "owners/register";
    }

	@PostMapping("/register-owner")
	public String registerOwner(@Validated @ModelAttribute Owner owner, BindingResult bindingResult, Model model
				, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("owner", owner);
			return "owners/register";
		}
		ownerService.create(owner);
		redirectAttributes.addFlashAttribute("confirmationMessage","Successfully Registered. You can login now.");
		return "redirect:/";
	}
	
	@GetMapping("/owners/{ownerId}")
	public ModelAndView showOwner(@PathVariable Long ownerId) { // if not working out("ownerId")
		ModelAndView mav = new ModelAndView("owners/ownerDetails");
		mav.addObject(ownerService.findById(ownerId)); // remove owner if not working
		return mav;
	}

	@RequestMapping("/owners/find")
	public String initFindForm(Model model) {
		model.addAttribute(OWNER, new Owner());
		return "owners/findOwners";
	}

	@GetMapping("/owners")
	public String processFindForm(Owner owner, BindingResult bindingResult, Model model) {
		// allow parameterless GET request for /owners to return all records
			

		String value = owner.getLastName();
		List<Owner> telephoneResults = ownerService.findByTelephoneLike("%" + value.trim() + "%");
		List<Owner> lastNameResults = ownerService.findAllByLastNameLike("%" + value.trim() + "%");
		List<Owner> firstNameResults = ownerService.findAllByFirstNameLike("%" + value.trim() + "%");

		if (telephoneResults.size() > 1) {
			model.addAttribute("selections", telephoneResults);
			return "owners/ownersList";
		} else if (lastNameResults.size() > 1) {
			model.addAttribute("selections", lastNameResults);
			return "owners/ownersList";
		} else if(firstNameResults.size() > 1) {
			model.addAttribute("selections",firstNameResults);
			return "owners/ownersList";
		}
		else if (telephoneResults.size() == 1) {
			owner = telephoneResults.iterator().next();
			return REDIRECT_OWNERS + owner.getId();
		} else if (lastNameResults.size() == 1) {
			owner = lastNameResults.iterator().next();
			return REDIRECT_OWNERS + owner.getId();
		} else if (firstNameResults.size() == 1) {
			owner = firstNameResults.iterator().next();
			return REDIRECT_OWNERS + owner.getId();
		} 
		else {
			bindingResult.rejectValue("telephone", "notFound", "not found");
			return "owners/findOwners";
		}
	}

	@GetMapping("/owners/new")
	public String initCreationForm(Model model) {
		model.addAttribute(OWNER, Owner.builder().build());
		return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
	}

	@PostMapping("/owners/new")
	public String processCreationForm(@Validated Owner owner, BindingResult result) {
		if (result.hasErrors())
			return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
		else {
			Owner savedOwner = ownerService.save(owner);
			return REDIRECT_OWNERS + savedOwner.getId();
		}
	}

	@GetMapping("/owners/{ownerId}/edit")
	public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
		model.addAttribute(OWNER, ownerService.findById(ownerId));
		return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
	}

	@PostMapping("/owners/{ownerId}/edit")
	public String processUpdateOwnerForm(@Validated Owner owner, @PathVariable Long ownerId, BindingResult result) {
		if (result.hasErrors())
			return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
		else {
			owner.setId(ownerId);
			Owner savedOwner = ownerService.save(owner);
			return REDIRECT_OWNERS + savedOwner.getId();
		}
	}

	@GetMapping("/owners/{ownerId}/delete")
	public String deleteOwner(@PathVariable Long ownerId, Model model) {
		log.debug("Deleting owner id:" + ownerId);
		ownerService.deleteById(ownerId);
		// model.addAttribute(OWNER,Owner.builder().build());
		return "redirect:owners/findOwners";
	}
}