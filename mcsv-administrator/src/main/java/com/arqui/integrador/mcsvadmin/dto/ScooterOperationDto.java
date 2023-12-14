package com.arqui.integrador.mcsvadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ScooterOperationDto {
    private Boolean enabled;
    private Long quantity;
}
