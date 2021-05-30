package com.onlinevet.clinic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such owner.")
public class OwnerNotFoudException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4689814967753031398L;

}
