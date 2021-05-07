package com.onlinevet.clinic.controllers;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.onlinevet.clinic.helper.URLHelper;
import com.onlinevet.clinic.model.User;
import com.onlinevet.clinic.service.UserService;

import net.bytebuddy.utility.RandomString;

@Controller
public class ForgotPasswordController {
	private static final String FORGOT_PASSWORD_FORM = "forgotPasswordForm";

	private static final String MESSAGE = "message";

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private UserService userService;

	@GetMapping("/forgotPassword")
	public String showForgotPasswordForm() {
		return FORGOT_PASSWORD_FORM;
	}

	@PostMapping("/forgotPassword")
	public String processForgotPassword(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");
		if(Objects.isNull(userService.findByEmail(email))) {
			model.addAttribute("error", "Email is not registered with us.");
			return FORGOT_PASSWORD_FORM;
		}
		
		String token = RandomString.make(30);
		try {
			userService.updateResetPasswordToken(token, email);
			String resetPasswordLink = URLHelper.getSiteURL(request) + "/resetPassword?token=" + token;
			sendEmail(email, resetPasswordLink);
			model.addAttribute(MESSAGE, "We have sent a reset password link to your email. Please check.");

		} catch (UnsupportedEncodingException | MessagingException e) {
			model.addAttribute("error", "Error while sending email");
		}

		return FORGOT_PASSWORD_FORM;
	}

	public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("contact@shopme.com", "Shopme Support");
		helper.setTo(recipientEmail);

		String subject = "Here's the link to reset your password";

		String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
				+ "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + link
				+ "\">Change my password</a></p>" + "<br>" + "<p>Ignore this email if you do remember your password, "
				+ "or you have not made the request.</p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
	}

	@GetMapping("/resetPassword")
	public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
		User customer = userService.getByResetPasswordToken(token);
		model.addAttribute("token", token);

		if (customer == null) {
			model.addAttribute(MESSAGE, "Invalid Token");
			return MESSAGE;
		}

		return "resetPasswordForm";
	}

	@PostMapping("/resetPassword")
	public String processResetPassword(HttpServletRequest request, Model model) {
		String token = request.getParameter("token");
		String password = request.getParameter("password");

		User user = userService.getByResetPasswordToken(token);
		model.addAttribute("title", "Reset your password");

		if (user == null) {
			model.addAttribute(MESSAGE, "Invalid Token");
			return MESSAGE;
		} else {
			userService.updatePassword(user, password);
			model.addAttribute(MESSAGE, "You have successfully changed your password.");
		}

		return FORGOT_PASSWORD_FORM;
	}
}
