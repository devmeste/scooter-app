package com.arqui.integrador.mcsvadmin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import com.arqui.integrador.mcsvadmin.dto.FareDto;
import com.arqui.integrador.mcsvadmin.dto.ScooterOperationDto;
import com.arqui.integrador.mcsvadmin.dto.TravelsByTotalBillingAmountDto;
import com.arqui.integrador.mcsvadmin.dto.TravelsByYearsDto;
import com.arqui.integrador.mcsvadmin.service.IAdminService;

@RestController
public class AdminController implements IAdminController {

    IAdminService adminService;
    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    public AdminController(IAdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void unauthorizeAccount(Long id) {
        this.adminService.updateStatusAccount(id , "unauthorize");
    }

    @Override
    public void authorizeAccount(Long id) {
       this.adminService.updateStatusAccount( id , "authorize");
    }

    @Override
    public List<TravelsByYearsDto> getTravelsByYears( int year , int quantity ) {
       return this.adminService.getTravelsByYears( year ,  quantity );
    }

    @Override
    public TravelsByTotalBillingAmountDto getTravelsByTotalBillingAmounts(int year, int month1, int month2) {
        
        return this.adminService.getTravelsByTotalBillingAmounts(year,month1,month2);
    }

    @Override
    public List<Long> getAndSetScootersInMaintenance( Boolean available) {
        return this.adminService.getAndSetScootersInMaintenance( available );
    }

    @Override
    public List<ScooterOperationDto> getScooterInOperation() {
        return this.adminService.getScooterInOperation();
    }

    @Override
    public void setNewFare( FareDto f) {
        this.adminService.setNewFare(f);
    }

}
