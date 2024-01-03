package com.arqui.integrador.service;

import static com.arqui.integrador.utils.StationMapper.dtoToEntity;
import static com.arqui.integrador.utils.StationMapper.entityToDto;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.arqui.integrador.dto.StationDto;
import com.arqui.integrador.exception.ItemNotFoundException;
import com.arqui.integrador.model.Station;
import com.arqui.integrador.repository.IStationRepository;

@Service
public class StationService implements IStationService{
	
	private static final Logger LOG = LoggerFactory.getLogger(StationService.class);
	
	private IStationRepository stationRepository;
	
	public StationService(IStationRepository stationRepository) {
		this.stationRepository = stationRepository;
	}

	@Override
	public List<StationDto> getAll(String order) {
		
		List<StationDto> response = this.stationRepository.findAll(Sort.by(Sort.Direction.ASC, order))
				.stream().map(e -> entityToDto(e)).toList();
		
		LOG.info("Stations: {} Quantity: {}", response, response.size());
		
		return response;
	}

	@Override
	public StationDto getById(Long id) {
		Station response = this.findById(id);
		
		LOG.info("Station: {}", response);
		
		return entityToDto(response);
	}

	@Override
	public StationDto add(StationDto stationDto) {
		Station response = this.stationRepository.save(dtoToEntity(stationDto));
		
		LOG.info("Scooter added: {}", response);
		
		return entityToDto(response);
	}

	@Override
	public StationDto update(Long id, StationDto stationDto) {
		Station station = this.findById(id);
		
		stationDto.setId(station.getId());
		
		this.stationRepository.save(dtoToEntity(stationDto));
		
		LOG.info("Station edited: {}", stationDto);
		
		return stationDto;
	}

	@Override
	public void delete(Long id) {
		this.stationRepository.delete(this.findById(id));
		
		LOG.info("Station succesfully deleted.");
	}
	
	private Station findById(Long id) {
		return this.stationRepository.findById(id).orElseThrow(() ->
			new ItemNotFoundException("Item not found.", "Item with id: " + id + " not found."));
	}
}
