package com.arqui.integrador.mcsvmaintenance.dto;

import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScooterReportDto {
	
	@NotNull
    private Long scooterId;
    
	@NotNull
    private float kmsTraveled;
    
	@NotNull
    private LocalTime usedTime;
    
    private LocalTime pausedTime;
    
    @NotNull
    private boolean available;
}
