package com.arqui.integrador.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScooterReportDto {
	
	private Long id;
	
	@NotNull
	private Float kmsTraveled;
	
	private Long pauseTime;
	
	public ScooterReportDto(Long id, Float kmsTraveled) {
        this.id = id;
        this.kmsTraveled = kmsTraveled;
    }
	
}
