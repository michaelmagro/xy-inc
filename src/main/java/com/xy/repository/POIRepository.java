package com.xy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xy.domain.POI;

public interface POIRepository extends JpaRepository<POI, String> {
	
	@Query("SELECT p FROM POI p WHERE SQRT((p.coordinateX - :x) * (p.coordinateX - :x) + (p.coordinateY - :y) * (p.coordinateY - :y)) < :range")
	public List<POI> listByProximity(@Param("x") Integer coordinateX, @Param("y") Integer coordinateY, @Param("range") Double range);
}
