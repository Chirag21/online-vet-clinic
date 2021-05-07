package com.onlinevet.clinic.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.onlinevet.clinic.exceptions.AppointmentNotFoundException;
import com.onlinevet.clinic.exceptions.InvalidAppointmentDateException;
import com.onlinevet.clinic.model.Appointment;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {
	@GetMapping("/pet")
	public String getPetAppointmentHomePage(Authentication principal, Model model, @PageableDefault(size = 8) Pageable pageable) {
		return "appointments/appointments";
	}
	
    @GetMapping("/vet")
    public String getVetAppointmentHomePage(Authentication principal, Model model, @PageableDefault(size = 8) Pageable pageable) {
        return "appointments/appointments";
    }
    
    @GetMapping("/vet/{appointmentId}")
    public String getVetAppointment(@PathVariable long appointmentId, Authentication principal, Model model) {
        return "appointments/appointment";
    }
    
    @GetMapping("/pet/{appointmentId}")
    public String getPetAppointment(@PathVariable long appointmentId, Authentication principal, Model model) {
        return "appointments/appointment";
    }

    @GetMapping("/")
    public String getAppointment(@RequestParam("date") @DateTimeFormat(pattern = "MM/dd/yyyy hh:mm:ss a") Date date, Model model,
    								Authentication principal, HttpServletRequest request) {
        return "appointments/appointment";
    }

    @GetMapping("/pet/add")
    public String getPetAddAppointment(Principal principal, @RequestParam("date") @DateTimeFormat(pattern = "MM/dd/yyyy hh:mm:ss a") Date date,
    											@ModelAttribute Appointment appointment, Model model) {
    		return "appointments/add";
    }

    @PostMapping("/pet/add")
    public String petAddAppointment(@RequestParam("date") @DateTimeFormat(pattern = "MM/dd/yyyy hh:mm:ss a") Date date,
                                        @Validated @ModelAttribute Appointment appointment,
                                        BindingResult bindingResult, Authentication principal, Model model) {
    		return "appointment/add";
    		//return "redirect:/schedule/";
    }

    @GetMapping("/vet/add")
    public String getVetAddAppointment(Principal principal,
                                          @RequestParam("date") @DateTimeFormat(pattern = "MM/dd/yyyy hh:mm:ss a") Date date,
                                          @ModelAttribute Appointment appointment, Model model) {
    	return "appointment/add";
    }

    @PostMapping("/vet/add")
    public String vetAddAppointment(@RequestParam("date") @DateTimeFormat(pattern = "MM/dd/yyyy hh:mm:ss a") Date date,
                                       @Validated @ModelAttribute Appointment appointment,
                                       BindingResult bindingResult, Authentication principal, Model model) {
    	return "appointment/add";
    	//return "redirect:/schedule/";
    }

    @GetMapping("/getForDate")
    public ResponseEntity<List<Appointment>> getWeekSchedule(
    		@RequestParam("date") @DateTimeFormat(pattern = "MM/dd/yyyy") Date date, Authentication principal, HttpServletRequest request) {
        //return ResponseEntity.ok(appointmentDateViewModels);
        return null;
    }

    private long getVetId(Authentication principal, HttpServletRequest request) {
        return 0;
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
