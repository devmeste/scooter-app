package com.arqui.integrador.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class Travel{
	
	@Id
	private Long id; 
	
	private int id_account;
	
	private int id_user;
	
	private Integer id_scooter;
	
	private LocalDateTime start_date;
	
	private LocalDateTime ending_date;
	
	private int pause_time;
	
	private BigDecimal km;
	
	private Double cost;
	
	private boolean paused;
}
