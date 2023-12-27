package com.arqui.integrador.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.arqui.integrador.dto.ScooterDto;
import com.arqui.integrador.dto.ScooterNearestDto;
import com.arqui.integrador.dto.ScooterOperationDto;
import com.arqui.integrador.dto.ScooterReportDto;
import com.arqui.integrador.service.IScooterService;

@RestController
public class ScooterController implements IScooterController {
	
	private IScooterService scooterService;
	
	private static final Logger LOG = LoggerFactory.getLogger(ScooterController.class);
	
	public ScooterController(IScooterService scooterService) {
		this.scooterService = scooterService;
	}

	@Override
	public ResponseEntity<List<ScooterDto>> getAll(String order, Boolean available) {
		if(available!=null) {
			if(available) {
				List<ScooterDto> response = this.scooterService.getAllAvailable(order);
				
				LOG.info("Getting all enable scooters: {} order by: {}", response, order);
				
				return ResponseEntity.ok(response);
			}else {
				List<ScooterDto> response = this.scooterService.getAllDisable(order);
				
				LOG.info("Getting all disable scooters: {} order by: {}", response, order);
				
				return ResponseEntity.ok(response);
			}
		}else {
			List<ScooterDto> response = this.scooterService.getAll(order);
			
			LOG.info("Getting all scooters: {} order by: {}", response, order);
				
			return ResponseEntity.ok(response);
		}
		
	}

	@Override
	public ResponseEntity<ScooterDto> getById(Long id) {
		
		ScooterDto response = this.scooterService.getById(id);
		
		LOG.info("Getting scooters by id: {}", id);
		
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<ScooterDto> add(ScooterDto scooter) {
		
		ScooterDto response = this.scooterService.add(scooter);
		
		LOG.info("Creating scooter with request body: {}", scooter);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ScooterDto> update(Long id, ScooterDto scooter) {
		
		LOG.info("Editing scooter by id: {} with request body: {}", id, scooter);
		
		return ResponseEntity.ok(this.scooterService.update(id, scooter));
	}

	@Override
	public void delete(Long id) {
	
		this.scooterService.delete(id);
		
		LOG.info("Deleting scooter by id: {}", id);
		
	}

	@Override
	public ResponseEntity<List<ScooterReportDto>> getScooterReport(Boolean pause_time) {
		
		List<ScooterReportDto> response = this.scooterService.getScooterReport(pause_time);
		
		LOG.info("Getting all scooters for report: {}", response);
		
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<List<ScooterOperationDto>> getScooterInOperation() {
		List<ScooterOperationDto> response = this.scooterService.getScooterInOperation();
		
		LOG.info("Getting quantity of scooters in operation and disabled: {}", response);
		
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<List<ScooterNearestDto>> getNearestScooters(double latitude, double longitude){
		List<ScooterNearestDto> response = this.scooterService.getNearestScooters(latitude, longitude);
		
		LOG.info("Getting nearest scooters: {}", response);
		
		return ResponseEntity.ok(response);
	}
	
	@Override
	public void enableScooters(List<Long> scooterIds) {
		LOG.info("Enable scooters with ids: {}", scooterIds);
		
		this.scooterService.enableScooters(scooterIds);
	}
	
	@Override
	public void disableScooter(Long scooterId) {
		LOG.info("Disable scooter with id: {}", scooterId);
		
		this.scooterService.disableScooter(scooterId);
	}
}
