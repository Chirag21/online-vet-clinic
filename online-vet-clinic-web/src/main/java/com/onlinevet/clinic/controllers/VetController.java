package com.onlinevet.clinic.controllers;

import java.util.Set;

import com.onlinevet.clinic.model.Owner;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.onlinevet.clinic.model.Vet;
import com.onlinevet.clinic.service.VetService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class VetController {
	@Autowired
	private final VetService vetService;

	@RequestMapping({ "/vets", "/vets/index", "/vets/index.html", "/vets.html" })
	public String listVets(Model model) {
		model.addAttribute("vets", vetService.findAll());
		return "vets/vetList";
	}

	@GetMapping("/appointments/vet/vets")
	public String getVetsPage(Model model,@PageableDefault(size = 8) Pageable pageable) {
		Page<Vet> vets = vetService.findAll(pageable);
		model.addAttribute("vets",vets);
		return "/appointments/vet/vets";
	}
	
	@GetMapping("/api/vets")
	public @ResponseBody Set<Vet> getVetsJson() {
		return vetService.findAll();
	}
	
    @GetMapping("/vets/{id}")
    public String getVet(@PathVariable Long id, Model model) {
        model.addAttribute("vet", vetService.findById(id));
        return "/appointments/vet/vet";
    }
	
	@GetMapping("/register-vet")
	public String getVetRegister(@ModelAttribute Vet vet, Model model) {
		return "/appointments/vet/register";
	}

	@PostMapping("/register-vet")
	public String registerVet(@Validated @ModelAttribute Vet vet, BindingResult bindingResult, Model model
			, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("vet", vet);
			return "/appointments/vet/register";
		}
		vetService.create(vet);
		redirectAttributes.addFlashAttribute("confirmationMessage","Successfully Registered. You can login now.");
		return "redirect:/";
	}
}
