package com.arqui.integrador.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillDto implements Serializable {

	private static final long serialVersionUID = 6393167105538475504L;

	private Double total;
}
