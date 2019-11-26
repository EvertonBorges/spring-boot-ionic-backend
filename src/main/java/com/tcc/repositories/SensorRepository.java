package com.tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.domain.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer>{

	@Transactional (readOnly = true)
	Sensor findByIdExterno(String idExterno);
	
}