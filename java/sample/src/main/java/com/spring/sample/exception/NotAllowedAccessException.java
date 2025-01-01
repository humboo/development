package com.spring.sample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotAllowedAccessException extends RuntimeException {

	private static final long serialVersionID = 1L;
	
	public NotAllowedAccessException(String exception) {
		super(exception);
	}
}
