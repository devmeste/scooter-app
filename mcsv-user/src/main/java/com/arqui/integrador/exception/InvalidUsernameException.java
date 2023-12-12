package com.arqui.integrador.exception;

import org.springframework.http.HttpStatus;

public class InvalidUsernameException extends CustomException{

	private static final long serialVersionUID = 1872435267288379000L;

	public InvalidUsernameException(String error, String description) {
		super(HttpStatus.BAD_REQUEST, error, description);
	}
}
