package com.arqui.integrador.mcsvadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.arqui.integrador.mcsvadmin.dto.FareDto;
import com.arqui.integrador.mcsvadmin.dto.ScooterOperationDto;
import com.arqui.integrador.mcsvadmin.dto.TravelsByTotalBillingAmountDto;
import com.arqui.integrador.mcsvadmin.dto.TravelsByYearsDto;

@Service
public class AdminService implements IAdminService {

    private RestTemplate restTemplate;
    private static final Logger LOG = LoggerFactory.getLogger(AdminService.class);

    public AdminService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void updateStatusAccount(Long id, String status) {

        HttpEntity<Void> requestEntity = getRequestEntity();
        
        restTemplate.exchange(
                "lb://mcsv-user:8080/accounts/" + id + "/" + status,
                HttpMethod.PUT,
                requestEntity,
                new ParameterizedTypeReference<Void>() {
                });
    }                

    @Override
    public List<TravelsByYearsDto> getTravelsByYears(int year, int quantity) {

        HttpEntity<Void> requestEntity = getRequestEntity();

        ResponseEntity<List<TravelsByYearsDto>> result = restTemplate.exchange(
                "lb://mcsv-travel:8080/travels/filter?year=" + year + "&quantity=" + quantity,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<TravelsByYearsDto>>() {
                });
        if (result.getStatusCode().is2xxSuccessful()) {
            return result.getBody();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public TravelsByTotalBillingAmountDto getTravelsByTotalBillingAmounts(int year, int month1, int month2) {

        HttpEntity<Void> requestEntity = getRequestEntity();

        ResponseEntity<TravelsByTotalBillingAmountDto> result = restTemplate.exchange(
                "lb://mcsv-travel:8080/travels/billing?year=" + year + "&month1=" + month1 + "&month2=" + month2,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<TravelsByTotalBillingAmountDto>() {
                });
        if (result.getStatusCode().is2xxSuccessful()) {
        	LOG.info("travels: {}", result.getBody());
            return result.getBody();
        } else {
            return null;
        }
    }

    @Override
    public List<Long> getAndSetScootersInMaintenance(Boolean available) {

        HttpEntity<Void> requestEntity = getRequestEntity();

        ResponseEntity<List<Long>> allNewsInMaintenance = restTemplate.exchange(
                "lb://mcsv-maintenance:8080/maintenance/scooters-for-maintenance",
                HttpMethod.GET, requestEntity,
                new ParameterizedTypeReference<List<Long>>() {
                });

        if (allNewsInMaintenance.getStatusCode().is2xxSuccessful()) {
            return allNewsInMaintenance.getBody();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<ScooterOperationDto> getScooterInOperation() {
        HttpEntity<Void> requestEntity = getRequestEntity();
        ResponseEntity<List<ScooterOperationDto>> result = restTemplate.exchange(
                "lb://mcsv-scooter:8080/scooters/in-operation",
                HttpMethod.GET, requestEntity,
                new ParameterizedTypeReference<List<ScooterOperationDto>>() {
                });

        if (result.getStatusCode().is2xxSuccessful()) {
            return result.getBody();
        } else {
            return new ArrayList<>();
        }
    }

    private HttpEntity<Void> getRequestEntity() {

        HttpHeaders headers = new HttpHeaders();

        return new HttpEntity<>(headers);
    }

    @Override
    public void setNewFare(FareDto f) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<FareDto> requestEntity = new HttpEntity<>(f, headers);

         restTemplate.exchange(
                "lb://mcsv-travel:8080/travels/price",
                HttpMethod.POST, requestEntity,
                new ParameterizedTypeReference<Void>() {
                });
    }
}
