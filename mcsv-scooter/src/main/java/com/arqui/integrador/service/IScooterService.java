package com.arqui.integrador.service;

import java.util.List;

import com.arqui.integrador.dto.ScooterDto;
import com.arqui.integrador.dto.ScooterNearestDto;
import com.arqui.integrador.dto.ScooterOperationDto;
import com.arqui.integrador.dto.ScooterReportDto;

public interface IScooterService {

	List<ScooterDto> getAll(String order);

	ScooterDto getById(Long id);

	ScooterDto add(ScooterDto scooter);

	ScooterDto update(Long id, ScooterDto scooter);

	void delete(Long id);

	List<ScooterReportDto> getScooterReport(Boolean pause_time);

	List<ScooterOperationDto> getScooterInOperation();
	
	List<ScooterNearestDto> getNearestScooters(double latitude, double longitude);

	List<ScooterDto> getAllAvailable(String order);
	
	List<ScooterDto> getAllDisable(String order);
	
	void enableScooters(List<Long> scooterIds);
	
	void disableScooter(Long scooterId);

}
