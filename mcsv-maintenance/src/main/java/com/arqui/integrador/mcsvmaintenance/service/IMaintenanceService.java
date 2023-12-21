package com.arqui.integrador.mcsvmaintenance.service;

import java.util.List;

import com.arqui.integrador.mcsvmaintenance.dto.MaintenanceDto;
import com.arqui.integrador.mcsvmaintenance.dto.ScooterReportDto;

public interface IMaintenanceService {
    
    List<MaintenanceDto> getAll();
	
	MaintenanceDto getById(Long id);
	
	MaintenanceDto create(MaintenanceDto maintenanceDTO);
	
	MaintenanceDto update(Long id, MaintenanceDto maintenanceDTO);
		
	void delete(Long id);

    List<ScooterReportDto> getMaintenanceReport(Boolean available);

    void finalizeMaintenance(List<Long> id);
	
}
