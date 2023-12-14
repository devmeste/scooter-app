package com.arqui.integrador.mcsvadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelsByYearsDto {
    private int id_scooter;
    private Long travel_quantity;
}
