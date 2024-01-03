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

public interface IScooterRepository extends JpaRepository<Scooter, Long>{
	
	@Query("SELECT new com.arqui.integrador.dto.ScooterReportDto("
			+ "s.scooterId, "
			+ "s.kmsTraveled, "
			+ "s.usedTime, "
			+ "s.available )"
			+ "FROM com.arqui.integrador.model.Scooter s ")
	List<ScooterReportDto> getScooterReport();

	@Query("SELECT new com.arqui.integrador.dto.ScooterReportDto("
			+ "s.scooterId, "
			+ "s.kmsTraveled, "
			+ "s.usedTime, "
			+ "s.pausedTime, "
			+ "s.available )"
			+ "FROM com.arqui.integrador.model.Scooter s ")
	List<ScooterReportDto> getScooterReportWithPause();

	@Query("SELECT new com.arqui.integrador.dto.ScooterOperationDto(COUNT(s), s.available)"
			+ "FROM com.arqui.integrador.model.Scooter s "
			+ "GROUP BY s.available ")
	List<ScooterOperationDto> getScooterInOperation();
	
	@Query("SELECT new com.arqui.integrador.dto.ScooterNearestDto(s.scooterId, s.latitude, s.longitude, s.stationId)"
			+ "FROM com.arqui.integrador.model.Scooter s "
			+ "WHERE s.available = true "
			+ "AND (s.latitude BETWEEN :minLatitude AND :maxLatitude)"
			+ "AND (s.longitude BETWEEN :minLongitude AND :maxLongitude)")
	List<ScooterNearestDto> getNearestScooters(@Param ("minLatitude") double minLatitude,
											@Param ("maxLatitude") double maxLatitude,
											@Param ("minLongitude") double minLongitude,
											@Param ("maxLongitude") double maxLongitude);

	@Query("SELECT new com.arqui.integrador.dto.ScooterDto( "
			+ "s.scooterId, s.available, s.kmsTraveled, s.usedTime, s.latitude, "
			+ "s.longitude, s.stationId) "
			+ "FROM com.arqui.integrador.model.Scooter s "
			+ "WHERE s.available = :available "
			+ "ORDER BY :order")
	List<ScooterDto> findAllAvailable(@Param ("order") String order,
										@Param ("available") boolean available);
	
	@Modifying
	@Query("UPDATE Scooter s SET s.available = true "
			+ "WHERE s.scooterId IN (:list) ")
	void enableScooters(@Param ("list") List<Long> list);
	
	@Modifying
	@Query("UPDATE Scooter s SET s.available = false "
			+ "WHERE s.scooterId = :id ")
	void disableScooter(@Param ("id") Long id);
}
