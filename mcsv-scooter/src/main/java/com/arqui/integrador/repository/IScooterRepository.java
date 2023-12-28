package com.arqui.integrador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arqui.integrador.dto.ScooterDto;
import com.arqui.integrador.dto.ScooterNearestDto;
import com.arqui.integrador.dto.ScooterOperationDto;
import com.arqui.integrador.dto.ScooterReportDto;
import com.arqui.integrador.model.Scooter;

public interface IScooterRepository extends JpaRepository<Scooter, Integer>{

	@Query("SELECT new com.arqui.integrador.dto.ScooterReportDTO(s.id, s.kmsTraveled)"
			+ "FROM com.arqui.integrador.model.Scooter s ")
	List<ScooterReportDto> getScooterReport();

	@Query("SELECT new com.arqui.integrador.dto.ScooterOperationDTO(COUNT(s), s.enabled)"
			+ "FROM com.arqui.integrador.model.Scooter s "
			+ "GROUP BY s.enabled ")
	List<ScooterOperationDto> getScooterInOperation();
	
	@Query("SELECT new com.arqui.integrador.dto.ScooterNearestDTO(s.id, s.latitude, s.longitude, s.stationId)"
			+ "FROM com.arqui.integrador.model.Scooter s "
			+ "WHERE s.enabled = true "
			+ "AND (s.latitude BETWEEN :minLatitude AND :maxLatitude)"
			+ "AND (s.longitude BETWEEN :minLongitude AND :maxLongitude)")
	List<ScooterNearestDto> getNearestScooters(@Param ("minLatitude") double minLatitude,
											@Param ("maxLatitude") double maxLatitude,
											@Param ("minLongitude") double minLongitude,
											@Param ("maxLongitude") double maxLongitude);

	@Query("SELECT new com.arqui.integrador.dto.ScooterDTO( "
			+ "s.id, s.enabled, s.kmsTraveled, s.usedTime, s.latitude, "
			+ "s.longitude, s.stationId) "
			+ "FROM com.arqui.integrador.model.Scooter s "
			+ "WHERE s.enabled = :enabled "
			+ "ORDER BY :order")
	List<ScooterDto> findAllAvailable(@Param ("order") String order,
										@Param ("enabled") boolean enabled);
	
	@Modifying
	@Query("UPDATE Scooter s SET s.enabled = true "
			+ "WHERE s.id IN (:list) ")
	void enableScooters(@Param ("list") List<Long> list);
	
	@Modifying
	@Query("UPDATE Scooter s SET s.enabled = false "
			+ "WHERE s.id = :id ")
	void disableScooter(@Param ("id") Long id);
}
