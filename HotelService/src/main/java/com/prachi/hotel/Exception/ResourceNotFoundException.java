package com.prachi.hotel.Exception;

public class ResourceNotFoundException extends RuntimeException{
	public ResourceNotFoundException() {
		super("Resource not found");
	}
}
