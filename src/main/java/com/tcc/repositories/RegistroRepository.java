package com.tcc.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.domain.Registro;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Integer>{

	@Transactional (readOnly = true)
	public List<Registro> findBySensorId(Integer sensor_id);
	
	@Transactional (readOnly = true)
	@Query("SELECT obj "
		 + "FROM Registro obj "
	 	 + "WHERE "
		 	+ "obj.sensor.id = :idSensor AND "
	 	 	+ "YEAR(obj.dataHora) = YEAR(:data) AND "
	 	 	+ "MONTH(obj.dataHora) = MONTH(:data) AND "
	 	 	+ "DAY(obj.dataHora) = DAY(:data) "
 	 	 + "ORDER BY obj.dataHora")
	public List<Registro> searchByData(@Param("idSensor") Integer sensor_id, @Param("data") Date data);
	
	@Transactional (readOnly = true)
	@Query("SELECT obj "
		 + "FROM Registro obj "
	 	 + "WHERE "
		 	+ "obj.sensor.id = :idSensor AND "
	 	 	+ "obj.dataHora BETWEEN :dataInicial AND :dataFinal "
 	 	 + "ORDER BY obj.dataHora")
	public List<Registro> searchByDataInterval(@Param("idSensor") Integer sensor_id, @Param("dataInicial") Date dataInicial, @Param("dataFinal") Date dataFinal);
	
	@Transactional (readOnly = true)
	@Query("SELECT DISTINCT obj FROM Registro obj INNER JOIN obj.sensor sen WHERE sen.id = :sensorId")
	Page<Registro> search(@Param("sensorId") Integer sensorId, Pageable pageRequest);
	
}