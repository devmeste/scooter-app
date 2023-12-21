package com.arqui.integrador.mcsvmaintenance.initializer;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.arqui.integrador.mcsvmaintenance.model.Maintenance;
import com.arqui.integrador.mcsvmaintenance.repository.IMaintenanceRepository;

import jakarta.annotation.PostConstruct;

@Component
public class MaintenanceInitializer {

	private IMaintenanceRepository maintenanceRepository;

	public MaintenanceInitializer(IMaintenanceRepository maintenanceRepository) {
		this.maintenanceRepository = maintenanceRepository;
	}
	
	@PostConstruct
	public void init() {
		try {
			this.maintenanceRepository.save(Maintenance.builder().startDate(LocalDate.of(2023, 5, 14))
					.endDate(LocalDate.of(2023, 5, 16)).scooterId(210789L).scooterKms(1500).build());
			
			this.maintenanceRepository.save(Maintenance.builder().startDate(LocalDate.of(2023, 1, 02))
					.endDate(LocalDate.of(2023, 5, 05)).scooterId(210789L).scooterKms(500).build());
			
			this.maintenanceRepository.save(Maintenance.builder().startDate(LocalDate.of(2022, 11, 23))
					.endDate(null).scooterId(210789L).scooterKms(4000).build());
			
			this.maintenanceRepository.save(Maintenance.builder().startDate(LocalDate.of(2020, 6, 01))
					.endDate(LocalDate.of(2020, 6, 02)).scooterId(210789L).scooterKms(1750).build());
			
			this.maintenanceRepository.save(Maintenance.builder().startDate(LocalDate.of(2021, 8, 12))
					.endDate(LocalDate.of(2023, 5, 14)).scooterId(210789L).scooterKms(9000).build());
			
			this.maintenanceRepository.save(Maintenance.builder().startDate(LocalDate.of(2023, 5, 30))
					.endDate(LocalDate.of(2023, 6, 03)).scooterId(210789L).scooterKms(2345).build());
			
			this.maintenanceRepository.save(Maintenance.builder().startDate(LocalDate.of(2022, 3, 21))
					.endDate(LocalDate.of(2022, 3, 24)).scooterId(210789L).scooterKms(1000).build());
			
			this.maintenanceRepository.save(Maintenance.builder().startDate(LocalDate.of(2022, 4, 02))
					.endDate(LocalDate.of(2022, 4, 10)).scooterId(210789L).scooterKms(2015).build());
			
			this.maintenanceRepository.save(Maintenance.builder().startDate(LocalDate.of(2022, 12, 20))
					.endDate(LocalDate.of(2023, 1, 14)).scooterId(210789L).scooterKms(0).build());
		} catch(RuntimeException e) {
			
		}

	}
}
