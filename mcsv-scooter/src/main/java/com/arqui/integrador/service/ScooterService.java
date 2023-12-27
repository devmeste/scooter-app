package com.arqui.integrador.service;

import static com.arqui.integrador.utils.ScooterMapper.dtoToEntity;
import static com.arqui.integrador.utils.ScooterMapper.entityToDto;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.arqui.integrador.dto.ScooterDto;
import com.arqui.integrador.dto.ScooterNearestDto;
import com.arqui.integrador.dto.ScooterOperationDto;
import com.arqui.integrador.dto.ScooterPauseDto;
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
	
	private RestTemplate restTemplate;
	
	public ScooterService(IScooterRepository scooterRepository, RestTemplate restTemplate) {
		this.scooterRepository = scooterRepository;
		this.restTemplate = restTemplate;
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
		
		scooterDto.setId(scooter.getId());
		
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
		
		List<ScooterPauseDto> responseMsTravels = new ArrayList<>();
		
		List<ScooterReportDto> list = this.scooterRepository.getScooterReport();
		
		if(pause_time) {
			HttpHeaders headers = new HttpHeaders();
			HttpEntity<List<Void>> requestEntity = new HttpEntity<>(headers);
			
			ResponseEntity<List<ScooterPauseDto>> response = restTemplate.exchange(
					"lb://mcsv-travel:8080/travels/paused", 
					HttpMethod.GET, 
					requestEntity, 
					new ParameterizedTypeReference<List<ScooterPauseDto>>() {}
			);
			if(response.getStatusCode().is2xxSuccessful()) {
				responseMsTravels.addAll(response.getBody());
			}
			
			for(ScooterReportDto scooter: list) {
				for(ScooterPauseDto scooterPause: responseMsTravels) {
					if(scooterPause.getId().equals(scooter.getId())) {
						scooter.setPauseTime(scooterPause.getPauseTime());
					}
				}
			}
		}
		
		return list;
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
