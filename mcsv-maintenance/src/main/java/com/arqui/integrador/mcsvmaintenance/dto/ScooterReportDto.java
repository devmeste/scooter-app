package com.arqui.integrador.mcsvmaintenance.dto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScooterReportDto {
	
    private Long scooterId;
    
    private float kmsTraveled;
    
    private LocalTime usedTime;
    
    private LocalTime pausedTime;
    
    private boolean available;
}
