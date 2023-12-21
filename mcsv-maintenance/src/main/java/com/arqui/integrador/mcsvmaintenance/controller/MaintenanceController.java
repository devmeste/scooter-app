
package com.arqui.integrador.mcsvmaintenance.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.arqui.integrador.mcsvmaintenance.dto.MaintenanceDto;
import com.arqui.integrador.mcsvmaintenance.dto.ScooterReportDto;
import com.arqui.integrador.mcsvmaintenance.service.IMaintenanceService;

import jakarta.validation.Valid;

@RestController
public class MaintenanceController implements IMaintenanceController {

    private static final Logger LOG = LoggerFactory.getLogger(MaintenanceController.class);
    private IMaintenanceService maintenanceService;

    public MaintenanceController(IMaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @Override
    public ResponseEntity<List<MaintenanceDto>> getAll() {
        List<MaintenanceDto> response = this.maintenanceService.getAll();

        LOG.info("Getting all maintenances: {}", response);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<MaintenanceDto> create(@Valid MaintenanceDto maintenenceDto) {
        MaintenanceDto response = this.maintenanceService.create(maintenenceDto);

        LOG.info("Creating maitenance : {}", response);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<MaintenanceDto> getById(Long id) {
    	LOG.info("Getting maitenance by id: {}", id);
    	
        MaintenanceDto response = this.maintenanceService.getById(id);

        return ResponseEntity.ok(response);

    }

    @Override
    public ResponseEntity<MaintenanceDto> update(Long id, @Valid MaintenanceDto maintenenceDto) {
    	LOG.info("Updating maitenance: {} with id: {}", maintenenceDto, id);
    	
        MaintenanceDto response = this.maintenanceService.update(id, maintenenceDto);

        return ResponseEntity.ok(response);
    }

    @Override
    public void delete(Long id) {
    	LOG.info("Deleting maintenance with id : {}", id);

    	this.maintenanceService.delete(id);
    }

    @Override
    public List<ScooterReportDto> getMaintenanceReport(Boolean available) {
    	LOG.info("Getting maintenance report");
    	
        return this.maintenanceService.getMaintenanceReport(available);
    }

    @Override
    public void finalizeMaintenance(List<Long> ids) {
    	LOG.info("Finalizing maintenance of scooters with ids: {}", ids);
    	
        this.maintenanceService.finalizeMaintenance(ids);
    }
}
