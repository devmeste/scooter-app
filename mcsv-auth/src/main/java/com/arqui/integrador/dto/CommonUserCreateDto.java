package com.arqui.integrador.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper=true)
public class CommonUserCreateDto extends UserCreateDto{

	private static final long serialVersionUID = 1L;

	@NotBlank
	private String firstname;
	
	@NotBlank
	private String surname;
	
	@NotNull
	private Long cellphone;
	
	@NotBlank
	@Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
	private String email;
	
	public CommonUserCreateDto(
			String username, 
			String password, 
			String firstname, 
			String surname,
			Long cellphone,
			String email) {
		super(username, password);
		this.firstname = firstname;
		this.surname = surname;
		this.cellphone = cellphone;
		this.email = email;
	}
	
}
