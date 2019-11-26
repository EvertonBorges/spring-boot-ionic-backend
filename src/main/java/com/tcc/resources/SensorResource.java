package com.tcc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.domain.Sensor;
import com.tcc.services.SensorService;

@RestController
@RequestMapping(value = "/sensores")
public class SensorResource {
	
	@Autowired
	private SensorService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Sensor>> findAll() {
		List<Sensor> sensores = service.findAll();
		return ResponseEntity.ok().body(sensores);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Sensor> find(@PathVariable Integer id) {
		Sensor obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
}