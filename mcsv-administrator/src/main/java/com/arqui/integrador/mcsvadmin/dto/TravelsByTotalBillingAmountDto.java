package com.arqui.integrador.mcsvadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TravelsByTotalBillingAmountDto {

    private Double total;


}
