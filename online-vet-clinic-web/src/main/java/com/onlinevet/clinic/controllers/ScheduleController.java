package com.onlinevet.clinic.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlinevet.clinic.model.Owner;
import com.onlinevet.clinic.model.User;
import com.onlinevet.clinic.model.Vet;
import com.onlinevet.clinic.exceptions.ScheduleNotFoundException;
import com.onlinevet.clinic.service.OwnerService;
import com.onlinevet.clinic.service.PetService;
import com.onlinevet.clinic.service.UserService;
import com.onlinevet.clinic.service.VetService;
import com.onlinevet.clinic.service.WeekScheduleService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Controller
@RequestMapping("/schedule")
public class ScheduleController {
	private static final String ROLE_OWNER = "ROLE_OWNER";

	private static final String ROLE_VET = "ROLE_VET";

	@Autowired
	private WeekScheduleService weekScheduleService;

	@Autowired
    private VetService vetService;

	@Autowired
    private PetService petService;
	
	@Autowired
    private OwnerService ownerService;
	
	@Autowired
	private UserService userService;

    @PreAuthorize("hasRole('OWNER') or hasRole('VET')")
    @GetMapping
    public String getSchedule(Authentication authentication, Model model, HttpServletRequest request) {
        String name = authentication.getName();
        User user = userService.findByUsername(name).orElseThrow(ScheduleNotFoundException::new);
        if (request.isUserInRole(ROLE_VET)) {
            Vet vet = this.vetService.findByUserId(user.getId());
            model.addAttribute("vet", vet);
        } else if (request.isUserInRole(ROLE_OWNER)) {
            Owner owner = this.ownerService.findByUserId(user.getId());
            Vet vet = vetService.findAll().iterator().next();
            model.addAttribute("vet", vet);
        }

        return "appointments/schedule/schedule";
    }

	/*
	 * @GetMapping("/edit") public String getEditSchedule(Principal principal, Model
	 * model, HttpServletRequest request) { Long weekScheduleId =
	 * getWeekScheduleId((Authentication) principal, request); EditWeekScheduleModel
	 * editWeekScheduleModel = this.weekScheduleService.getById(weekScheduleId);
	 * 
	 * model.addAttribute("editWeekScheduleModel", editWeekScheduleModel);
	 * 
	 * return "schedule/edit"; }
	 */

	/*
	 * @PostMapping("/edit") public String editSchedule(@Validated @ModelAttribute
	 * EditWeekScheduleModel editWeekScheduleModel, BindingResult bindingResult,
	 * Principal principal, HttpServletRequest request) { if
	 * (bindingResult.hasErrors()) { return "schedule/edit"; }
	 * 
	 * Long weekScheduleId = getWeekScheduleId((Authentication) principal, request);
	 * 
	 * editWeekScheduleModel.setId(weekScheduleId); EditWeekScheduleModel
	 * editWeekScheduleModelIds = this.weekScheduleService.getById(weekScheduleId);
	 * for (int i = 0; i < editWeekScheduleModel.getEditDayScheduleModels().size();
	 * i++) { editWeekScheduleModel .getEditDayScheduleModels()
	 * .get(i).setId(editWeekScheduleModelIds
	 * .getEditDayScheduleModels().get(i).getId()); }
	 * 
	 * this.weekScheduleService.save(editWeekScheduleModel);
	 * 
	 * return "redirect:/schedule/"; }
	 */

	/*
	 * @GetMapping("/week") public ResponseEntity<EditWeekScheduleModel>
	 * getWeekSchedule(Principal principal, HttpServletRequest request) { Long
	 * weekScheduleId = getWeekScheduleId((Authentication) principal, request);
	 * EditWeekScheduleModel editWeekScheduleModel =
	 * this.weekScheduleService.getById(weekScheduleId);
	 * 
	 * return ResponseEntity.ok(editWeekScheduleModel); }
	 * 
	 * private Long getWeekScheduleId(Authentication principal, HttpServletRequest
	 * request) { Long userId = ((User) principal.getPrincipal()).getId(); if
	 * (request.isUserInRole(ROLE_VET)) { Doctor vet =
	 * this.vetService.getByUserId(userId); return vet.getWeekSchedule().getId(); }
	 * else if (request.isUserInRole(ROLE_OWNER)) { Patient owner =
	 * this.petService.getByUserId(userId); return
	 * owner.getDoctor().getWeekSchedule().getId(); }
	 * 
	 * return 0; }
	 */
}
