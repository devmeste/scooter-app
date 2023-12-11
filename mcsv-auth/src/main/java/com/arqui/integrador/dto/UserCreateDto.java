package com.arqui.integrador.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class UserCreateDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	public UserCreateDto(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
