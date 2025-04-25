package com.piseth.java.school.phones_shope.ExceptionHandle;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ApiException{
	private final String message;
	private final HttpStatus status;
}
