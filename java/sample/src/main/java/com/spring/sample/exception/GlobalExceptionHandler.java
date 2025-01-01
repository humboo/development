package com.spring.sample.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//https://www.codejava.net/frameworks/spring-boot/global-exception-handler-examples

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(
            GlobalExceptionHandler.class);
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneralError(HttpServletRequest request,
	        HttpServletResponse response, Exception ex) {
	     
	    LOGGER.error(ex.getMessage(), ex);
	     
	    // do something with request and response
	     
	    //return "general_error";
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	}
	
	@ResponseBody
    @ExceptionHandler(ArithmeticException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(HttpServletRequest request,
            HttpServletResponse response, Exception ex) {
         
        // do something with request or response   
         
        return ex.getMessage();
    }
}
