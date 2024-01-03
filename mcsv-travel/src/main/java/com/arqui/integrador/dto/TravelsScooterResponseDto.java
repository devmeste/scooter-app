package com.arqui.integrador.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelsScooterResponseDto implements Serializable {

	private static final long serialVersionUID = -7822551077921357978L;

	private int id_scooter;

	private Long travel_quantity;

}
