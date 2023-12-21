package com.arqui.integrador.mcsvmaintenance.util;

import com.arqui.integrador.mcsvmaintenance.dto.MaintenanceDto;
import com.arqui.integrador.mcsvmaintenance.model.Maintenance;

public class MaintenanceMapper {
    
    public static MaintenanceDto entityToDto(Maintenance maintenance) {
        return MaintenanceDto.builder()
                .maintenanceId(maintenance.getMaintenanceId())
                .startDate(maintenance.getStartDate())
                .endDate(maintenance.getEndDate())
                .scooterId(maintenance.getScooterId())
                .scooterKms(maintenance.getScooterKms())
                .build();
    }
    
    public static Maintenance dtoToEntity(MaintenanceDto maintenanceDto) {
        return Maintenance.builder()
                .maintenanceId(maintenanceDto.getMaintenanceId())
                .startDate(maintenanceDto.getStartDate())
                .endDate(maintenanceDto.getEndDate())
                .scooterId(maintenanceDto.getScooterId())
                .scooterKms(maintenanceDto.getScooterKms())
                .build();
    }
}
	

