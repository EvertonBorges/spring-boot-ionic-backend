package com.tcc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.domain.Sensor;
import com.tcc.repositories.SensorRepository;
import com.tcc.services.exceptions.ObjectNotFoundException;

@Service
public class SensorService {

	@Autowired
	private SensorRepository repo;
	
	public Sensor find(Integer id)  {
		Optional<Sensor> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " +
				id + ", Tipo: " + Sensor.class.getName()));
	}
	
	public Sensor findByIdExterno(String idExterno) {
		return repo.findByIdExterno(idExterno);
	}
	
	public List<Sensor> findAll() {
		return repo.findAll();
	}
	
}