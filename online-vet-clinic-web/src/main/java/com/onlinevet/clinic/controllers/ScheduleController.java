package com.onlinevet.clinic.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlinevet.clinic.model.Owner;
import com.onlinevet.clinic.model.Vet;
import com.onlinevet.clinic.model.WeekSchedule;
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

    @GetMapping("/schedule")
    public String getSchedule(Authentication authentication, Model model, HttpServletRequest request) {
        String name = authentication.getName();
        Long userId = userService.findByUsername(name).orElseThrow(ScheduleNotFoundException::new).getId();
        if (request.isUserInRole(ROLE_VET)) {
            Vet vet = this.vetService.findByUserId(userId);
            model.addAttribute("vet", vet);
        } else if (request.isUserInRole(ROLE_OWNER)) {
            Owner owner = this.ownerService.findByUserId(userId);
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

	
	@GetMapping("/schedule/week")
	public ResponseEntity<WeekSchedule> getWeekSchedule(Authentication authentication, HttpServletRequest request) {
		Long weekScheduleId = getWeekScheduleId(authentication, request);
		WeekSchedule editWeekScheduleModel = this.weekScheduleService.findById(weekScheduleId);
		return ResponseEntity.ok(editWeekScheduleModel);
	}

	private Long getWeekScheduleId(Authentication authentication, HttpServletRequest request) {
        String name = authentication.getName();
        Long userId = userService.findByUsername(name).orElseThrow(ScheduleNotFoundException::new).getId();
		if (request.isUserInRole(ROLE_VET)) {
			Vet vet = this.vetService.findByUserId(userId);
			return vet.getWeekSchedule().getId();
		} else if (request.isUserInRole(ROLE_OWNER)) {
			Owner owner = this.ownerService.findByUserId(userId);
			return owner.getPets().get(0).getVet().getWeekSchedule().getId();
		}
		return 0L;
	}
	 
}
