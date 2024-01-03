package com.arqui.integrador.dto;

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
	
	@NotNull
    private Long scooterId;
    
	@NotNull
    private float kmsTraveled;
    
	@NotNull
    private float usedTime;
	
	private float pausedTime;
	
	private float totalTime;
    
    @NotNull
    private boolean available;

}
