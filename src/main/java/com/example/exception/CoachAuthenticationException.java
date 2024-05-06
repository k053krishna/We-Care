package com.example.exception;

public class CoachAuthenticationException extends RuntimeException{
	
	public CoachAuthenticationException(String message) {
		super(message);
	}
}
