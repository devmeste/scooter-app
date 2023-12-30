package com.arqui.integrador.utils;

import com.arqui.integrador.dto.ScooterDto;
import com.arqui.integrador.model.Scooter;

public final class ScooterMapper {
	private ScooterMapper() {}
	
	public static ScooterDto entityToDto(Scooter scooter) {
		return ScooterDto.builder()
				.id(scooter.getScooterId())
				.enabled(scooter.isAvailable())
				.kmsTraveled(scooter.getKmsTraveled())
				.usedTime(scooter.getUsedTime())
				.latitude(scooter.getLatitude())
				.longitude(scooter.getLongitude())
				.stationId(scooter.getStationId())
				.build();
	}
	
	public static Scooter dtoToEntity(ScooterDto scooterDto) {
		return Scooter.builder()
				.scooterId(scooterDto.getId())
				.available(scooterDto.isEnabled())
				.kmsTraveled(scooterDto.getKmsTraveled())
				.usedTime(scooterDto.getUsedTime())
				.latitude(scooterDto.getLatitude())
				.longitude(scooterDto.getLongitude())
				.stationId(scooterDto.getStationId())
				.build();
	}
	
	public static Scooter dtoToEntityNoId(ScooterDto scooterDto) {
		return Scooter.builder()
				.available(scooterDto.isEnabled())
				.kmsTraveled(scooterDto.getKmsTraveled())
				.usedTime(scooterDto.getUsedTime())
				.latitude(scooterDto.getLatitude())
				.longitude(scooterDto.getLongitude())
				.stationId(scooterDto.getStationId())
				.build();
	}
	
	
}
