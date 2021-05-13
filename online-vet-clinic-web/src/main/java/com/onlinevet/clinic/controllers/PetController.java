package com.onlinevet.clinic.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlinevet.clinic.model.Owner;
import com.onlinevet.clinic.model.Pet;
import com.onlinevet.clinic.model.PetType;
import com.onlinevet.clinic.service.OwnerService;
import com.onlinevet.clinic.service.PetService;
import com.onlinevet.clinic.service.PetTypeService;
import com.onlinevet.clinic.service.UserService;
import com.onlinevet.clinic.service.VetService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class PetController {
	private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

	@Autowired
	private final PetService petService;

	@Autowired
	private OwnerService ownerService;

	@Autowired
	private PetTypeService petTypeService;

	@Autowired
	private VetService vetService;

	@Autowired
	private UserService userService;

	@InitBinder("owner")
	public void initOwnerBinder(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("id");
	}

	@GetMapping("/mypets")
	public String getMyPetsPage(Model model, @PageableDefault(size = 8) Pageable pageable,
			Authentication authentication) {
		String name = authentication.getName();
		Long userId = userService.findByUsername(name).orElseThrow().getId();
		Owner owner = ownerService.findById(userId);
		Page<Pet> pets = petService.findAllByOwnerIdOrderByBirthDateDesc(owner.getId(), pageable);
		model.addAttribute("pets", pets);
		return "/appointments/pet/pets";
	}

	@GetMapping("/pet/{id}")
	public String getPet(@PathVariable Long id, Model model) {
		model.addAttribute("pet", petService.findById(id));
		return "/appointments/pet/pet";
	}

	@GetMapping("/pet/pets")
	public String getPetsPage(Model model, Pageable pageable) {
		model.addAttribute("pets", petService.findAllByOrderByName(pageable));
		return "/appointments/pet/pets";
	}

	@ModelAttribute(name = "types")
	public Set<PetType> populatePetTypes() {
		return petTypeService.findAll();
	}

	/*
	 * @ModelAttribute(name = "owner") public Owner
	 * findOwner(@PathVariable("ownerId") Long ownerId) { return
	 * ownerService.findById(ownerId); }
	 */

	@RequestMapping({ "", "/", "/pets", "petList", "petList.html" })
	public String listPets(Model model) {
		model.addAttribute("pets", petService.findAll());
		return "/pets/petList";
	}

	/*
	 * @GetMapping("/owners/{ownerId}/pets/new") public String
	 * initCreationForm(Owner owner, Model model) { Pet pet = new Pet();
	 * owner.getPets().add(pet); pet.setOwner(owner); model.addAttribute("pet",
	 * pet); return VIEWS_PETS_CREATE_OR_UPDATE_FORM; }
	 * 
	 * @PostMapping("/owners/{ownerId}/pets/new") public String
	 * processCreationForm(Owner owner, @Validated Pet pet, BindingResult result,
	 * Model model) { if (StringUtils.hasLength(pet.getName()) && pet.isNew() &&
	 * owner.getPet(pet.getName(), true) != null) { result.rejectValue("name",
	 * "duplicate", "already exists"); } owner.getPets().add(pet);
	 * pet.setOwner(owner); if (result.hasErrors()) {
	 * System.out.println(result.getFieldError()); result.getAllErrors().forEach(e
	 * -> System.out.println(e)); result.getFieldErrors().forEach(e ->
	 * System.out.println(e)); System.out.println();
	 * System.out.println("\n*******************************\n" +
	 * pet.getBirthDate()); model.addAttribute("pet", pet); return
	 * VIEWS_PETS_CREATE_OR_UPDATE_FORM; } else { petService.save(pet); return
	 * "redirect:/owners/" + owner.getId(); } }
	 * 
	 * @GetMapping("/owners/{ownerId}/pets/{petId}/edit") public String
	 * initUpdateForm(@PathVariable Long petId, Model model) {
	 * model.addAttribute("pet", petService.findById(petId)); return
	 * VIEWS_PETS_CREATE_OR_UPDATE_FORM; }
	 * 
	 * @PostMapping("/owners/{ownerId}/pets/{petId}/edit") public String
	 * processUpdateForm(@Validated Pet pet, BindingResult result, Owner owner,
	 * Model model) { if (result.hasErrors()) { pet.setOwner(owner);
	 * model.addAttribute("pet", pet); return VIEWS_PETS_CREATE_OR_UPDATE_FORM; }
	 * else { // owner.getPets().add(pet); pet.setOwner(owner);
	 * petService.save(pet); return "redirect:/owners/" + owner.getId(); } }
	 */

}
