package com.arqui.integrador.mcsvmaintenance.exception;

import org.springframework.http.HttpStatus;

public class FeignClientCustomException extends CustomException{

	private static final long serialVersionUID = 1L;

	public FeignClientCustomException(String error, String description) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, error, description);
	}

}
