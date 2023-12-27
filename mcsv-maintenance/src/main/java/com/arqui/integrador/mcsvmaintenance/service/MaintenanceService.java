package com.arqui.integrador.mcsvmaintenance.service;

import static com.arqui.integrador.mcsvmaintenance.util.MaintenanceMapper.dtoToEntity;
import static com.arqui.integrador.mcsvmaintenance.util.MaintenanceMapper.entityToDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.arqui.integrador.mcsvmaintenance.dto.MaintenanceDto;
import com.arqui.integrador.mcsvmaintenance.dto.ScooterReportDto;
import com.arqui.integrador.mcsvmaintenance.exception.FeignClientCustomException;
import com.arqui.integrador.mcsvmaintenance.exception.ItemNotFoundException;
import com.arqui.integrador.mcsvmaintenance.feign.IScooterFeignClient;
import com.arqui.integrador.mcsvmaintenance.model.Maintenance;
import com.arqui.integrador.mcsvmaintenance.repository.IMaintenanceRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MaintenanceService implements IMaintenanceService {

    private static final Logger LOG = LoggerFactory.getLogger(MaintenanceService.class);
    
    private IMaintenanceRepository maintenanceRepository;
    
    private IScooterFeignClient scooterFeignClient;
    
    public MaintenanceService(IMaintenanceRepository maintenanceRepository, IScooterFeignClient scooterFeignClient) {
        this.maintenanceRepository = maintenanceRepository;
        this.scooterFeignClient = scooterFeignClient;
    }

    @Override
    public List<MaintenanceDto> getAll() {
        List<MaintenanceDto> response = this.maintenanceRepository.findAll()
        		.stream().map(e -> entityToDto(e)).toList();
        
        LOG.info("Maintenances: {}", response);
        
        return response;
    }

    @Override
    public MaintenanceDto getById(Long id) {
        Maintenance response = this.findById(id);

        LOG.info("Maintenance: {}", response);

        return entityToDto(response);
    }

    @Override
    public MaintenanceDto create(MaintenanceDto maintenanceDto) {
        Maintenance response = this.maintenanceRepository.save(dtoToEntity(maintenanceDto));
        
        this.scooterFeignClient.disableScooter(maintenanceDto.getScooterId());

        LOG.info("Maintenance created: {}", response);

        return entityToDto(response);
    }

    @Override
    public MaintenanceDto update(Long id, MaintenanceDto maintenanceDto) {
        Maintenance maintenance = this.findById(id);

        maintenanceDto.setMaintenanceId(maintenance.getMaintenanceId());

        this.maintenanceRepository.save(dtoToEntity(maintenanceDto));

        LOG.info("Maintenance updated: {}", maintenanceDto);

        return maintenanceDto;
    }

    @Override
    public void delete(Long id) {
        Maintenance maintenance = this.findById(id);
        
        this.scooterFeignClient.enableScooters(Arrays.asList(maintenance.getScooterId()));

        this.maintenanceRepository.delete(maintenance);

        LOG.info("Maintenance deleted: {}", maintenance);
    }

    @Override
    public List<ScooterReportDto> getMaintenanceReport(Boolean available) {
    	ResponseEntity<List<ScooterReportDto>> response = this.scooterFeignClient.getScooterReport(available);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
        	LOG.error("Feign client error has ocurred getting scooter report.");
        	throw new FeignClientCustomException("Feign client error", "Feign client error has ocurred getting scooter report.");
        }
    }

    @Override
    public void finalizeMaintenance(List<Long> ids) {
    	List<Long> scooterIds = new ArrayList<>();
    	
    	ids.forEach(id -> {
    		Maintenance m = this.findById(id);
    		
    		m.setEndDate(LocalDate.now());
    		
    		this.maintenanceRepository.save(m);
    		
    		scooterIds.add(m.getScooterId());
    		
    		LOG.info("Maintenance finalized: {}", m);
    	});
    	
    	this.scooterFeignClient.enableScooters(scooterIds);
    }
    
    private Maintenance findById(Long id) {
        return this.maintenanceRepository.findById(id).orElseThrow(
                () -> new ItemNotFoundException("Item not found", "Maintenance with id: " + id + " not found."));
    }
}
