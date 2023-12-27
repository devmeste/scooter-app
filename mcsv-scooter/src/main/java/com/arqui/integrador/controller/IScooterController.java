package com.arqui.integrador.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import com.arqui.integrador.dto.ScooterDto;
import com.arqui.integrador.dto.ScooterNearestDto;
import com.arqui.integrador.dto.ScooterOperationDto;
import com.arqui.integrador.dto.ScooterReportDto;

import jakarta.validation.Valid;

@Validated
@RequestMapping("/scooters")
public interface IScooterController {
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	ResponseEntity<List<ScooterDto>> getAll(
			@RequestParam(value = "orderBy", defaultValue = "id") String order,
			@RequestParam(value = "available", required = false) Boolean available);
	
	@GetMapping(value = "/report" ,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	ResponseEntity<List<ScooterReportDto>> getScooterReport(@RequestParam(value = "pause-time", defaultValue = "false") Boolean pause_time);
	
	@GetMapping(value = "/in-operation", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	ResponseEntity<List<ScooterOperationDto>> getScooterInOperation();
	
	@GetMapping(value = "/nearest/lat/{lat}/long/{long}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	ResponseEntity<List<ScooterNearestDto>> getNearestScooters(@PathVariable("lat") double latitude, @PathVariable("long") double longitude);
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	ResponseEntity<ScooterDto> getById(@PathVariable("id") Long id);
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	ResponseEntity<ScooterDto> add(@RequestBody @Valid ScooterDto scooter);
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	ResponseEntity<ScooterDto> update(@PathVariable("id") Long id,@RequestBody @Valid ScooterDto scooter);
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@PathVariable("id") Long id);
	
	@PutMapping(value = "/enable-scooters", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	void enableScooters(List<Long> scooterIds);
	
	@PutMapping(value = "/disable-scooter", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	void disableScooter(Long scooterId);
}
