package com.piseth.java.school.phones_shope.ExceptionHandle;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleApiException(ResourceNotFoundException e) {
		ApiException body = new ApiException(e.getMessage(),e.getStatus());
		return new ResponseEntity<>(body, e.getStatus()); 
//		return ResponseEntity.ok(body);   
	}

}