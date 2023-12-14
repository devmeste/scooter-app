package com.arqui.integrador.mcsvadmin.service;

import java.util.List;

import com.arqui.integrador.mcsvadmin.dto.FareDto;
import com.arqui.integrador.mcsvadmin.dto.ScooterOperationDto;
import com.arqui.integrador.mcsvadmin.dto.TravelsByTotalBillingAmountDto;
import com.arqui.integrador.mcsvadmin.dto.TravelsByYearsDto;

public interface IAdminService {

     void updateStatusAccount(Long id, String status);

    List<TravelsByYearsDto> getTravelsByYears(int year, int quantity);

    TravelsByTotalBillingAmountDto getTravelsByTotalBillingAmounts(int year, int month1, int month2);

    List<Long> getAndSetScootersInMaintenance( Boolean available);

    List<ScooterOperationDto> getScooterInOperation();

    void setNewFare(FareDto f);
}
