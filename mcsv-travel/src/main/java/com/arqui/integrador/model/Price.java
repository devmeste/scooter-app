package com.arqui.integrador.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Price{
	
	@Id
	private Long id;
	
	private float price_by_hour;
	
	private float rate_of_increase;
	
	private LocalDate actual_date;
}
