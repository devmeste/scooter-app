package com.arqui.integrador.mcsvmaintenance.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.arqui.integrador.mcsvmaintenance.dto.ScooterReportDto;


@FeignClient(name = "mcsv-scooter", url = "localhost:8080/scooters")
public interface IScooterFeignClient {
	
	@GetMapping("/report")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<List<ScooterReportDto>> getScooterReport(@RequestParam(value = "pause-time", defaultValue = "true") Boolean pause_time);
}
