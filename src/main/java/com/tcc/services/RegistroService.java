package com.tcc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tcc.domain.Registro;
import com.tcc.domain.Sensor;
import com.tcc.domain.Sublocal;
import com.tcc.dto.RegistroDTO;
import com.tcc.enums.UnidadeEnum;
import com.tcc.repositories.RegistroRepository;
import com.tcc.services.exceptions.AuthorizationException;
import com.tcc.services.exceptions.DateFormatException;
import com.tcc.services.exceptions.ObjectNotFoundException;

@Service
public class RegistroService {
	
	@Autowired
	private RegistroRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private SensorService dispositivoService;
	
	public Registro find(Integer id)  {
		Optional<Registro> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " +
				id + ", Tipo: " + Registro.class.getName()));
	}

	public Registro insert(RegistroDTO registroDto) {
		Sensor sensor = dispositivoService.findByIdExterno(registroDto.getIdExternoDispositivo());
		boolean senhaCorreta = pe.matches(registroDto.getSenha(), sensor.getSenha());
		
		if (sensor == null || !senhaCorreta) {
			throw new AuthorizationException("Acesso negado!");
		}
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			Registro registro = new Registro();
			registro.setId(null);
			registro.setDataHora(sdf.parse(registroDto.getInstante()));
			registro.setUnidade(UnidadeEnum.valueOf(registroDto.getUnidade()));
			registro.setRegistro(registroDto.getRegistro());
			registro.setSensor(sensor);
			registro.setSublocal(sensor.getSublocal());
			
			return repo.save(registro);
		} catch(ParseException e) {
			throw new DateFormatException("Data e hora deve estar no formado yyyy-MM-dd HH:mm:ss");
		}
	}
	
	public List<Sublocal> searchBySensor(Integer idSensor) {
		List<Sublocal> sublocais = new ArrayList<>();
		for (Registro reg: repo.findBySensorId(idSensor)) {
			if (!sublocais.contains(reg.getSublocal())) {
				sublocais.add(reg.getSublocal());
			}			
		}
		
		List<Registro> remover = new ArrayList<>();
		for (Sublocal sub: sublocais) {
			for (Registro reg: sub.getRegistros()) {
				if (!reg.getSensor().getId().equals(idSensor)) {
					remover.add(reg);
				}
			}
		}
		
		for (Sublocal sub: sublocais) {
			sub.getRegistros().removeAll(remover);
		}
		
		return sublocais;
	}
	
	public List<Registro> searchByData(Integer idSensor, String data) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			return repo.searchByData(idSensor, sdf.parse(data));
		} catch (ParseException e) {
			throw new DateFormatException("Erro ao converter a data"); 
		}
	}
	
	public List<Registro> searchByDataInterval(Integer idSensor, String data1, String data2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			if (data1.length() <= 10) {
				data1 += " 00:00:00";
			}
			if (data2.length() <= 10) {
				data2 += " 23:59:59";
			}
			
			Calendar dataInicial = Calendar.getInstance(); 
			dataInicial.setTime(sdf.parse(data1));
			Calendar dataFinal = Calendar.getInstance(); 
			dataFinal.setTime(sdf.parse(data2));
			return repo.searchByDataInterval(idSensor, dataInicial.getTime(), dataFinal.getTime());
		} catch (ParseException e) {
			throw new DateFormatException("Erro ao converter a data"); 
		}
	}
	
	public Page<Registro> search(Integer id, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.search(id, pageRequest);
	}
	
}
