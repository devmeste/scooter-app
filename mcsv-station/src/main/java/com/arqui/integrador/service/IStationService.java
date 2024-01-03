package com.arqui.integrador.service;

import java.util.List;

import com.arqui.integrador.dto.StationDto;

public interface IStationService {
	List<StationDto> getAll(String order);

	StationDto getById(Long id);

	StationDto add(StationDto station);

	StationDto update(Long id, StationDto station);

	void delete(Long id);
}
