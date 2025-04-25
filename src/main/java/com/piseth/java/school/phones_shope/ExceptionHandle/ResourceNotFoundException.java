package com.piseth.java.school.phones_shope.ExceptionHandle;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException {
	private final HttpStatus status;

	public ResourceNotFoundException(String message,HttpStatus status) {
		super(message);
		this.status = status;
	}
}
