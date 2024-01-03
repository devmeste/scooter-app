package com.arqui.integrador.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "station")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Station {
	
	@Id
	@Column(name = "station_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String location;
	
	private double latitude;
	
	private double longitude;
}
