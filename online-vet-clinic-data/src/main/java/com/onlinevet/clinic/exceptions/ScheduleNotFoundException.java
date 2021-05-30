package com.onlinevet.clinic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such schedule.")
public class ScheduleNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2098310073055941849L;
}
