package com.arqui.integrador.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PausedTimeResponseDto implements Serializable {

	private static final long serialVersionUID = -7822551077921357978L;

	private Integer id_scooter;

	private int pause_time;
}
