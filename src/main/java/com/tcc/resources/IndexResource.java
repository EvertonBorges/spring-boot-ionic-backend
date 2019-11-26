package com.tcc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.tcc.domain.Registro;
import com.tcc.domain.Sensor;
import com.tcc.services.RegistroService;
import com.tcc.services.SensorService;

@RestController
@RequestMapping(value = "/index")
public class IndexResource {

	@Autowired
	private SensorService service;
	
	@Autowired
	private RegistroService registroService;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> find() {
		Sensor obj = service.find(1);
		
		Integer quantidadeAbaixoDe299 = 0;
		Integer quantidadeEntre300E499 = 0;
		Integer quantidadeIgualOuAcimaDe500 = 0;
		for(Registro reg: obj.getRegistros()) {
			if (reg.getRegistro() < 300) {
				quantidadeAbaixoDe299++;
			} else if (reg.getRegistro() < 500) {
				quantidadeEntre300E499++;
			} else {
				quantidadeIgualOuAcimaDe500++;
			}
		}
		
		String statusSensor = null;
		if (quantidadeAbaixoDe299 >= 3) {
			statusSensor = "Ruim";
		} else if (quantidadeEntre300E499 >= 3) {
			statusSensor = "Regular";
		} else {
			statusSensor = "Bom";
		}
		
		Context context = new Context();
		context.setVariable("registros", obj.getRegistros());
		context.setVariable("sensorStatus", statusSensor);
		String body = templateEngine.process("principal/index", context);
		
		return ResponseEntity.ok(body);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> findBySensor(@PathVariable Integer id) {
		Page<Registro> obj = registroService.search(id, 0, 20, "id", "DESC");
		
		Integer quantidadeAbaixoDe299 = 0;
		Integer quantidadeEntre300E499 = 0;
		Integer quantidadeIgualOuAcimaDe500 = 0;
		for(Registro reg: obj) {
			if (reg.getRegistro() < 300) {
				quantidadeAbaixoDe299++;
			} else if (reg.getRegistro() < 500) {
				quantidadeEntre300E499++;
			} else {
				quantidadeIgualOuAcimaDe500++;
			}
		}
		
		String statusSensor = null;
		if (quantidadeAbaixoDe299 >= 3) {
			statusSensor = "Ruim";
		} else if (quantidadeEntre300E499 >= 3) {
			statusSensor = "Regular";
		} else {
			statusSensor = "Bom";
		}
		
		Context context = new Context();
		context.setVariable("registros", obj);
		context.setVariable("sensorStatus", statusSensor);
		String body = templateEngine.process("principal/index", context);
		
		return ResponseEntity.ok(body);
	}
	
}