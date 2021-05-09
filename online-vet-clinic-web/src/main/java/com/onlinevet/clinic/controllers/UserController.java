package com.onlinevet.clinic.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.onlinevet.clinic.helper.MessageHelper;
import com.onlinevet.clinic.model.User;
import com.onlinevet.clinic.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class UserController {
	private static final String LOGIN_SIGNUP = "loginSignup";

	private static final String MESSAGE = "message";

	private static final String REDIRECT_LOGIN_SIGNUP = "redirect:loginSignup";

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/*
	 * @PostMapping("/doLogin") public String doLogin(@ModelAttribute("user") User
	 * user, Model model) { log.debug("USERNAME = " + user);
	 * System.out.println("User = " + user); return "/index"; }
	 */

	@PostMapping("/doRegister")
	public String doRegistration(@ModelAttribute("user") User user, Model model, HttpSession session,
			BindingResult result) {

		if (result.hasErrors())
			return REDIRECT_LOGIN_SIGNUP;

		System.out.println(user.getUsername());

		if (!(userService.existsUserByUsername(user.getUsername()) || userService.existsUserByEmail(user.getEmail()))) {
			if (user.getUsername().isEmpty()) {
				model.addAttribute("error", "please enter username");
				log.debug(user.toString());
				return REDIRECT_LOGIN_SIGNUP;
			}
			user.setRole("ROLE_ADMIN");
			user.setActive('Y');
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			userService.save(user);
			model.addAttribute("user", User.builder().build());
			session.setAttribute(MESSAGE, MessageHelper.builder()
					.content("Successfully registered. Login to continue.").type("alert-success").build());
			return LOGIN_SIGNUP;

		} else if (userService.existsUserByUsername(user.getUsername())) {
			model.addAttribute("user", user);
			session.setAttribute(MESSAGE,
					MessageHelper.builder().content("Username  \"" + user.getUsername() + "\"  is already taken.")
							.type("alert-danger").build());
			return LOGIN_SIGNUP;

		} else {
			model.addAttribute("user", user);
			session.setAttribute(MESSAGE, MessageHelper.builder()
					.content("Email  \"" + user.getEmail() + "\"  is already taken.").type("alert-danger").build());
			return LOGIN_SIGNUP;
		}
	}
	
    @GetMapping("/user/change-password")
    public String getChangePasswordPage(@ModelAttribute User user) {
        return "/appointments/change-password";
    }
    
    @PostMapping("/user/change-password")
    public String registerUser(@Validated @ModelAttribute User user, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "/appointments/change-password";
        }

        Long userId = ((User)((Authentication) principal).getPrincipal()).getId(); 
        boolean oldPasswordEqualsNew = bCryptPasswordEncoder.encode(user.getPassword()).equals(userService.findById(userId).getPassword());
        if (oldPasswordEqualsNew) {
            FieldError fieldError = new FieldError("user", "password", "New password cannot be same as old password.");
            bindingResult.addError(fieldError);
            return "change-password";
        }
        userService.updatePassword(user, user.getPassword());
        return "redirect:/";
    }
}
