package com.arqui.integrador.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class SpecialUserCreateDto extends UserCreateDto{

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String role;
	
	public SpecialUserCreateDto(String username, String password, String role) {
		super(username, password);
		this.role = role;
	}
}
