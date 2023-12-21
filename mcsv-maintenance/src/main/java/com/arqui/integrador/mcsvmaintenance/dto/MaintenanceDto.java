package com.arqui.integrador.mcsvmaintenance.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceDto {
	
    private long maintenanceId;

    @NotNull
	private LocalDate startDate;

	private LocalDate endDate;

    @NotNull
    private Long scooterId;
    
    @NotNull
    private float scooterKms;
    
}

