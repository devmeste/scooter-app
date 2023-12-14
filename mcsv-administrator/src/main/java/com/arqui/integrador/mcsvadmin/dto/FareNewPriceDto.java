package com.arqui.integrador.mcsvadmin.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FareNewPriceDto {
    private LocalDate date;
    private double price;
}
