package com.onlinevet.clinic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such role.")
public class RoleNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1720775448006661594L;
}
