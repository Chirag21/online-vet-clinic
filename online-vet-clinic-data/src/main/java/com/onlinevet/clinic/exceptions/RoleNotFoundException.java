package com.onlinevet.clinic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such role.")
public class RoleNotFoundException extends RuntimeException{
}
