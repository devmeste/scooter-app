package com.arqui.integrador.exception;

import org.springframework.http.HttpStatus;

public class NoRoleException extends CustomException{

	private static final long serialVersionUID = 1L;

	public NoRoleException(String error, String description) {
		super(HttpStatus.BAD_REQUEST, error, description);
	}
}
