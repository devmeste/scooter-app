package com.arqui.integrador.service;

import static com.arqui.integrador.utils.ScooterMapper.dtoToEntity;
import static com.arqui.integrador.utils.ScooterMapper.entityToDto;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.arqui.integrador.dto.ScooterDto;
import com.arqui.integrador.dto.ScooterNearestDto;
import com.arqui.integrador.dto.ScooterOperationDto;
import com.arqui.integrador.dto.ScooterReportDto;
import com.arqui.integrador.exception.ItemNotFoundException;
import com.arqui.integrador.model.Scooter;
import com.arqui.integrador.repository.IScooterRepository;

@Service
public class ScooterService implements IScooterService{

	private static final Logger LOG = LoggerFactory.getLogger(ScooterService.class);
	
	@Value("${distance}")
	private int DISTANCE;
	
	private IScooterRepository scooterRepository;
	
	public ScooterService(IScooterRepository scooterRepository) {
		this.scooterRepository = scooterRepository;
	}
	
	@Override
	public List<ScooterDto> getAll(String order) {
		List<ScooterDto> response = new ArrayList<>();
		
		this.scooterRepository.findAll(Sort.by(Sort.Direction.ASC, order)).forEach(e -> 
			response.add(entityToDto(e)));
		
		LOG.info("Scooters: {} Quantity: {}", response, response.size());
		
		return response;
	}

	@Override
	public ScooterDto getById(Long id) {
		Scooter response = this.findById(id);
		
		LOG.info("Scooter: {}", response);
		
		return entityToDto(response);
	}

	@Override
	public ScooterDto add(ScooterDto scooterDto) {
		Scooter response = this.scooterRepository.save(dtoToEntity(scooterDto));
		
		LOG.info("Scooter added: {}", response);
		
		return entityToDto(response);
	}

	@Override
	public ScooterDto update(Long id, ScooterDto scooterDto) {
		Scooter scooter = this.findById(id);
		
		scooterDto.setId(scooter.getScooterId());
		
		this.scooterRepository.save(dtoToEntity(scooterDto));
		
		LOG.info("Scooter edited: {}", scooterDto);
		
		return scooterDto;
	}

	@Override
	public void delete(Long id) {
		
		this.scooterRepository.delete(this.findById(id));
		
		LOG.info("Scooter succesfully deleted.");
	}

	@Override
	public List<ScooterReportDto> getScooterReport(Boolean pause_time) {
		List<ScooterReportDto> response = new ArrayList<>();
		
		if(pause_time) {
			response = this.scooterRepository.getScooterReportWithPause();
			response.forEach(e -> {
				e.setTotalTime(e.getUsedTime() + e.getPausedTime());
			});
		} else {
			response = this.scooterRepository.getScooterReport();
		}
		
		return response;
	}

	@Override
	public List<ScooterOperationDto> getScooterInOperation() {
		return this.scooterRepository.getScooterInOperation();
	}

	@Override
	public List<ScooterNearestDto> getNearestScooters(double latitude, double longitude) {
		
		LOG.info("Getting nearest scooters - Latitude between {} and {}; Longitude between {} and {}", latitude-DISTANCE, latitude+DISTANCE, longitude-DISTANCE, longitude+DISTANCE);
		
		return this.scooterRepository.getNearestScooters(latitude-DISTANCE, latitude+DISTANCE, longitude-DISTANCE, longitude+DISTANCE);

	}
	
	private Scooter findById(Long id) {
		return this.scooterRepository.findById(id.intValue()).orElseThrow(() ->
			new ItemNotFoundException("Item not found.", "Item with id: " + id + "not found.")
		);
	}

	@Override
	public List<ScooterDto> getAllAvailable(String order) {
		List<ScooterDto> response = new ArrayList<>();
		
		this.scooterRepository.findAllAvailable(order, true).forEach(e -> 
			response.add(e));
		
		LOG.info("Scooters: {} Quantity: {}", response, response.size());
		
		return response;
	}

	@Override
	public List<ScooterDto> getAllDisable(String order) {
		List<ScooterDto> response = new ArrayList<>();
		
		this.scooterRepository.findAllAvailable(order, false).forEach(e -> 
			response.add(e));
		
		LOG.info("Scooters: {} Quantity: {}", response, response.size());
		
		return response;
	}
	
	@Override
	public void enableScooters(List<Long> scooterIds) {
		this.scooterRepository.enableScooters(scooterIds);
		
		LOG.info("Scooters succesfully enabled.");
	}
	
	@Override
	public void disableScooter(Long scooterId) {
		this.scooterRepository.disableScooter(scooterId);
		
		LOG.info("Scooter succesfully disabled.");
	}
}
