package com.arqui.integrador.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.arqui.integrador.dto.StationDto;
import com.arqui.integrador.service.IStationService;

@RestController
public class StationController implements IStationController{
	
	private IStationService stationService;
	
	private static final Logger LOG = LoggerFactory.getLogger(StationController.class);
	
	public StationController(IStationService stationService) {
		this.stationService = stationService;
	}

	@Override
	public ResponseEntity<List<StationDto>> getAll(String order) {
		LOG.info("Getting all stations ordered by: {}",  order);
		
		return ResponseEntity.ok(this.stationService.getAll(order));
	}

	@Override
	public ResponseEntity<StationDto> getById(Long id) {
		LOG.info("Getting station by id: {}", id);
		
		return ResponseEntity.ok(this.stationService.getById(id));
	}

	@Override
	public ResponseEntity<StationDto> add(StationDto station) {
		LOG.info("Creating station with request body: {}", station);
		
		return new ResponseEntity<>(this.stationService.add(station), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<StationDto> update(Long id, StationDto station) {
		LOG.info("Editing station by id: {} with request body: {}", id, station);
		
		return ResponseEntity.ok(this.stationService.update(id, station));
	}

	@Override
	public void delete(Long id) {
		LOG.info("Deleting station by id: {}", id);
		
		this.stationService.delete(id);
	}

}
