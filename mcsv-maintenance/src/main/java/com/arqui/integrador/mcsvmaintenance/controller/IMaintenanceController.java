package com.arqui.integrador.mcsvmaintenance.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.arqui.integrador.mcsvmaintenance.dto.MaintenanceDto;
import com.arqui.integrador.mcsvmaintenance.dto.ScooterReportDto;

import jakarta.validation.Valid;

@RequestMapping("/maintenance")
public interface IMaintenanceController {

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<List<MaintenanceDto>> getAll();

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<MaintenanceDto> getById(@PathVariable(name = "id") Long id);

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	ResponseEntity<MaintenanceDto> create(@Valid @RequestBody MaintenanceDto maintenenceDto);

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<MaintenanceDto> update(@PathVariable(name = "id") Long id,
			@Valid @RequestBody MaintenanceDto maintenenceDto);

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@PathVariable(name = "id") Long id);

	@GetMapping(value = "/scooters/report")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	List<ScooterReportDto> getMaintenanceReport(@RequestParam(value = "pause-time", defaultValue = "true") Boolean available);

	@PutMapping(value = "/finalize", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void finalizeMaintenance(@RequestBody List<Long> id);
}
