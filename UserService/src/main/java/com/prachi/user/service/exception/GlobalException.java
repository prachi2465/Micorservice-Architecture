package com.prachi.user.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException e){
		return new ResponseEntity<ApiResponse>(new ApiResponse(e.getMessage(),false),HttpStatus.NOT_FOUND);
	}

}
