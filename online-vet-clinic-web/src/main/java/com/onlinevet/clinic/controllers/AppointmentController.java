package com.onlinevet.clinic.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.doctorAppointmentBookingSystem.model.viewModel.AppointmentTypeViewModel;
import com.doctorAppointmentBookingSystem.model.viewModel.DoctorSelectViewModel;
import com.onlinevet.clinic.exceptions.AppointmentNotFoundException;
import com.onlinevet.clinic.exceptions.InvalidAppointmentDateException;
import com.onlinevet.clinic.model.Appointment;
import com.onlinevet.clinic.model.Owner;
import com.onlinevet.clinic.model.Pet;
import com.onlinevet.clinic.model.User;
import com.onlinevet.clinic.model.Vet;
import com.onlinevet.clinic.service.AppointmentService;
import com.onlinevet.clinic.service.OwnerService;
import com.onlinevet.clinic.service.PetService;
import com.onlinevet.clinic.service.UserService;
import com.onlinevet.clinic.service.VetService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/appointments")
public class AppointmentController {
	@Autowired
	AppointmentService appointmentService;
	
	@Autowired
	VetService vetService;
	
	@Autowired
	PetService petService;
	
	@Autowired
	OwnerService ownerService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/pet/appointments")
	public String getPetAppointmentHomePage(Authentication authentication, Model model, @PageableDefault(size = 8) Pageable pageable) {
		String name = authentication.getName();
		Long userId = userService.findByUsername(name).orElseThrow().getId();
		Owner owner = ownerService.findByUserId(userId);
		model.addAttribute("appointments", appointmentService.findAllByOwnerIdOrderByDate(owner.getId(), pageable));
		return "/appointments/appointment/appointments";
	}
	
    @GetMapping("/vet/appointments")
    public String getVetAppointmentHomePage(Authentication authentication, Model model, @PageableDefault(size = 8) Pageable pageable) {
		String name = authentication.getName();
		Long userId = userService.findByUsername(name).orElseThrow().getId();
		Vet vet = vetService.findByUserId(userId);
		model.addAttribute("appointments",appointmentService.findAllByVetById(vet.getId(), pageable));
    	return "/appointments/appointment/appointments";
    }
    
    @GetMapping("/vet/{appointmentId}")
    public String getVetAppointment(@PathVariable Long appointmentId, Authentication authentication, Model model) {
		String name = authentication.getName();
		Long userId = userService.findByUsername(name).orElseThrow().getId();
		Vet vet = vetService.findByUserId(userId);
		Appointment appointment = appointmentService.findById(appointmentId);
        
		//If appointment does not belong to this vet
		if (!appointment.getVet().getId().equals(vet.getId())) {
            return "redirect:/appointments/appointment/appointments";
        }
		model.addAttribute("appointment",appointmentService.findById(appointmentId));
        return "/appointments/appointment/appointment";
    }
    
    @GetMapping("/pet/{appointmentId}")
    public String getPetAppointment(@PathVariable Long appointmentId, Authentication authentication, Model model) {
		String name = authentication.getName();
		Long userId = userService.findByUsername(name).orElseThrow().getId();
		Owner owner = ownerService.findByUserId(userId);
		Appointment appointment = appointmentService.findById(appointmentId);
        
		//If appointment does not belong to this owner
		if (!appointment.getOwner().getId().equals(owner.getId())) {
            return "redirect:/appointments/appointment/appointments";
        }
		model.addAttribute("appointment",appointmentService.findById(appointmentId));
        return "/appointments/appointment/appointment";
    }

    @GetMapping("/")
    public String getAppointment(@RequestParam("date") @DateTimeFormat(pattern = "MM/dd/yyyy hh:mm:ss a") Date date, Model model,
    								Authentication authentication, HttpServletRequest request) {
    	Long vetId = getVetId(authentication, request);
    	Appointment appointment = appointmentService.findByDateAndVetId(date, vetId);
    	model.addAttribute("appointment", appointment);
    	return "/appointments/appointment/appointment";
    }

    @GetMapping("/pet/add")
    public String getPetAddAppointment(Principal authentication, @RequestParam("date") @DateTimeFormat(pattern = "MM/dd/yyyy hh:mm:ss a") Date date,
    											@ModelAttribute Appointment appointment, Model model) {
        if (date.before(new Date())) {
            throw new InvalidAppointmentDateException();
        }

        appointment.setDate(date);
		String name = authentication.getName();
		Long userId = userService.findByUsername(name).orElseThrow().getId();
		Owner owner = ownerService.findByUserId(userId);
        List<Pet> pets = petService.findAllByOwnerId(owner.getId());
        DoctorSelectViewModel doctorSelectViewModel = this.doctorService.getModelByUserId(patient.getDoctor().getUser().getId());
        model.addAttribute("doctorSelectViewModel", doctorSelectViewModel);

        List<AppointmentTypeViewModel> appointmentTypes = this.appointmentTypeService.getAll();
        model.addAttribute("pets", pets);
    	
    	return "appointments/appointment/add";
    }

    @PostMapping("/pet/add")
    public String petAddAppointment(@RequestParam("date") @DateTimeFormat(pattern = "MM/dd/yyyy hh:mm:ss a") Date date,
                                        @Validated @ModelAttribute Appointment appointment,
                                        BindingResult bindingResult, Authentication authentication, Model model) {
    		return "/appointments/appointment/add";
    		//return "redirect:/schedule/";
    }

    @GetMapping("/vet/add")
    public String getVetAddAppointment(Principal principal,
                                          @RequestParam("date") @DateTimeFormat(pattern = "MM/dd/yyyy hh:mm:ss a") Date date,
                                          @ModelAttribute Appointment appointment, Model model) {
    	return "/appointments/appointment/add";
    }

    @PostMapping("/vet/add")
    public String vetAddAppointment(@RequestParam("date") @DateTimeFormat(pattern = "MM/dd/yyyy hh:mm:ss a") Date date,
                                       @Validated @ModelAttribute Appointment appointment,
                                       BindingResult bindingResult, Authentication authentication, Model model) {
    	return "/appointments/appointment/add";
    	//return "redirect:/schedule/";
    }

    @GetMapping("/getForDate")
    public ResponseEntity<List<Appointment>> getWeekSchedule(
    		@RequestParam("date") @DateTimeFormat(pattern = "MM/dd/yyyy") Date date, Authentication authentication, HttpServletRequest request) {
        //return ResponseEntity.ok(appointmentDateViewModels);
        return null;
    }

    private Long getVetId(Authentication authentication, HttpServletRequest request) {
        if (authentication != null) {
            long userId = ((User) authentication.getPrincipal()).getId();
            if (request.isUserInRole("ROLE_VET")) {
                return this.vetService.findByUserId(userId).getId();
            } else if (request.isUserInRole("ROLE_OWNER")) {
                return this.ownerService.findByUserId(userId).getPet(null).getVet().getId();
            }
        }
    	
    	return 0L;
    }

    @ExceptionHandler(AppointmentNotFoundException.class)
    public String catchAppointmentNotFoundException() {
        return "error/appointment-not-found";
    }

    @ExceptionHandler(InvalidAppointmentDateException.class)
    public String catchInvalidAppointment() {
        return "error/invalid-appointment-date";
    }
}
