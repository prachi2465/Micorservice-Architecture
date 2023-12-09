package com.prachi.user.service.exception;

public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException() {
		super("Resource not found exception");
	}

}
