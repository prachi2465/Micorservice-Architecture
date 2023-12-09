package com.prachi.rating.exception;

public class ResourceNotFoundException extends RuntimeException{
	public ResourceNotFoundException() {
		super("Resource not found");
	}
}
