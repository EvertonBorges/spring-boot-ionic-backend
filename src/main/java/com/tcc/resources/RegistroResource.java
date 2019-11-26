package com.tcc.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tcc.domain.Registro;
import com.tcc.domain.Sublocal;
import com.tcc.dto.RegistroDTO;
import com.tcc.services.RegistroService;

@RestController
@RequestMapping(value = "/registrar")
public class RegistroResource {
	
	@Autowired
	private RegistroService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Registro> find(@PathVariable Integer id) {
		Registro obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "/{id}/registros", method = RequestMethod.GET)
	public ResponseEntity<List<Sublocal>> findBySensor(@PathVariable Integer id) {
		List<Sublocal> obj = service.searchBySensor(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "/{id}/{data}", method = RequestMethod.GET)
	public ResponseEntity<List<Registro>> findByData(@PathVariable Integer id, @PathVariable String data) {
		List<Registro> obj = service.searchByData(id, data);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "/{id}/{data1}/{data2}", method = RequestMethod.GET)
	public ResponseEntity<List<Registro>> findByDataInterval(@PathVariable Integer id, @PathVariable String data1, @PathVariable String data2) {
		List<Registro> obj = service.searchByDataInterval(id, data1, data2);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody RegistroDTO objDto) {
		Registro obj = service.insert(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
											 .path("/{id}")
											 .buildAndExpand(obj.getId())
											 .toUri();
		return ResponseEntity.created(uri).build();
	}
	
}