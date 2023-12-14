package com.arqui.integrador.mcsvadmin.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.arqui.integrador.mcsvadmin.dto.FareDto;
import com.arqui.integrador.mcsvadmin.dto.ScooterOperationDto;
import com.arqui.integrador.mcsvadmin.dto.TravelsByTotalBillingAmountDto;
import com.arqui.integrador.mcsvadmin.dto.TravelsByYearsDto;

@RequestMapping("/admin")
public interface IAdminController {

	// MESTE
	@PatchMapping(value = "/accounts/{id}/unauthorize")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void unauthorizeAccount(@PathVariable(name = "id") Long id);

	@PatchMapping(value = "/accounts/{id}/authorize")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void authorizeAccount(@PathVariable(name = "id") Long id);

	// FRAN 8005
	@GetMapping("/travels/filter")
	@ResponseStatus(HttpStatus.OK)
	List<TravelsByYearsDto> getTravelsByYears( @RequestParam (value = "year") int year , @RequestParam (value = "quantity") int quantity );

	@GetMapping("/travels/billing")
	@ResponseStatus(HttpStatus.OK)
	TravelsByTotalBillingAmountDto getTravelsByTotalBillingAmounts( 	
		@RequestParam (value = "year") int year , 
		@RequestParam (value = "month1") int month1,
		@RequestParam (value = "month2") int month2);

	@GetMapping("/scooters/for-maintenance?available={available}")												
	@ResponseStatus(HttpStatus.OK)
	List<Long>getAndSetScootersInMaintenance(@PathVariable (name = "available") Boolean available);
	
	@GetMapping("/scooters/in-operation")
	@ResponseStatus(HttpStatus.OK)
	List<ScooterOperationDto>getScooterInOperation();
	
	@PostMapping(value= "/travels/price" , consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	void setNewFare(@RequestBody FareDto f );
}
