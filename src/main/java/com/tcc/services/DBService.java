package com.tcc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tcc.domain.Lei;
import com.tcc.domain.Local;
import com.tcc.domain.Registro;
import com.tcc.domain.Sensor;
import com.tcc.domain.Sublocal;
import com.tcc.enums.UnidadeEnum;
import com.tcc.repositories.LeiRepository;
import com.tcc.repositories.LocalRepository;
import com.tcc.repositories.RegistroRepository;
import com.tcc.repositories.SensorRepository;
import com.tcc.repositories.SublocalRepository;

@Service
public class DBService {
	
	private static final Logger LOG = LoggerFactory.getLogger(DBService.class);
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private LeiRepository leiRepository;
	
	@Autowired
	private SensorRepository sensorRepository;
	
	@Autowired
	private RegistroRepository registroRepository;
	
	@Autowired
	private LocalRepository localRepository;
	
	@Autowired
	private SublocalRepository sublocalRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		LOG.info("Populando o banco de dados de TESTE...");
		
		Local local1 = new Local("Setor de Compras");
		Local local2 = new Local("Setor de RH");
		Local local3 = new Local("Setor de Logística");
		Local local4 = new Local("Setor de Produção");
		Local local5 = new Local("Setor de Qualidade");
		
		Sublocal sublocal1 = new Sublocal("Lâmpada 1", local1);
		Sublocal sublocal2 = new Sublocal("Lâmpada 2", local1);
		Sublocal sublocal3 = new Sublocal("Lâmpada 3", local1);
		Sublocal sublocal4 = new Sublocal("Lâmpada 1", local2);
		Sublocal sublocal5 = new Sublocal("Lâmpada 2", local2);
		Sublocal sublocal6 = new Sublocal("Lâmpada 1", local3);
		Sublocal sublocal7 = new Sublocal("Lâmpada 2", local3);
		Sublocal sublocal8 = new Sublocal("Lâmpada 1", local4);
		Sublocal sublocal9 = new Sublocal("Lâmpada 2", local4);
		Sublocal sublocal10 = new Sublocal("Lâmpada 1", local5);
		
		local1.getSublocais().addAll(Arrays.asList(sublocal1, sublocal2, sublocal3));
		local2.getSublocais().addAll(Arrays.asList(sublocal4, sublocal5));
		local3.getSublocais().addAll(Arrays.asList(sublocal6, sublocal7));
		local4.getSublocais().addAll(Arrays.asList(sublocal8, sublocal9));
		local5.getSublocais().addAll(Arrays.asList(sublocal10));
		
		Lei lei1 = new Lei(null, "NBR 001", "Lei 1", "Descrição da lei 1");
		Lei lei2 = new Lei(null, "NBR 002", "Lei 2", "Descrição da lei 2");
		
		Sensor sensor1 = new Sensor(null, "Sensor 1", pe.encode("123456"), "001", sublocal1);
		Sensor sensor2 = new Sensor(null, "Sensor 2", pe.encode("654321"), "002", sublocal5);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		Registro r1 = new Registro(null, sdf.parse("09/10/2019 10:00:00"), 400, UnidadeEnum.LX, sensor1, sublocal1);
		Registro r2 = new Registro(null, sdf.parse("09/10/2019 10:00:05"), 415, UnidadeEnum.LX, sensor1, sublocal1);
		Registro r3 = new Registro(null, sdf.parse("09/10/2019 10:00:10"), 410, UnidadeEnum.LX, sensor1, sublocal1);
		Registro r4 = new Registro(null, sdf.parse("09/10/2019 10:00:15"), 420, UnidadeEnum.LX, sensor1, sublocal1);
		Registro r5 = new Registro(null, sdf.parse("09/10/2019 10:00:20"), 417, UnidadeEnum.LX, sensor1, sublocal1);
		Registro r6 = new Registro(null, sdf.parse("09/10/2019 10:00:25"), 426, UnidadeEnum.LX, sensor1, sublocal1);
		Registro r7 = new Registro(null, sdf.parse("09/10/2019 10:00:30"), 490, UnidadeEnum.LX, sensor1, sublocal1);
		Registro r8 = new Registro(null, sdf.parse("09/10/2019 10:00:35"), 250, UnidadeEnum.LX, sensor1, sublocal1);
		Registro r9 = new Registro(null, sdf.parse("09/10/2019 10:00:40"), 205, UnidadeEnum.LX, sensor1, sublocal1);
		Registro r10 = new Registro(null, sdf.parse("09/10/2019 10:00:45"), 300, UnidadeEnum.LX, sensor1, sublocal1);
		Registro r11 = new Registro(null, sdf.parse("09/10/2019 10:00:50"), 440, UnidadeEnum.LX, sensor1, sublocal1);
		Registro r12 = new Registro(null, sdf.parse("09/10/2019 10:00:55"), 426, UnidadeEnum.LX, sensor1, sublocal1);
		Registro r13 = new Registro(null, sdf.parse("09/10/2019 10:01:00"), 410, UnidadeEnum.LX, sensor1, sublocal1);
		Registro r14 = new Registro(null, sdf.parse("09/10/2019 10:01:05"), 417, UnidadeEnum.LX, sensor1, sublocal1);
		Registro r15 = new Registro(null, sdf.parse("09/10/2019 10:01:10"), 441, UnidadeEnum.LX, sensor1, sublocal1);
		Registro r16 = new Registro(null, sdf.parse("09/10/2019 10:01:15"), 432, UnidadeEnum.LX, sensor1, sublocal1);
		Registro r17 = new Registro(null, sdf.parse("09/10/2019 10:01:20"), 413, UnidadeEnum.LX, sensor1, sublocal1);
		Registro r18 = new Registro(null, sdf.parse("09/10/2019 10:01:25"), 510, UnidadeEnum.LX, sensor1, sublocal1);
		Registro r19 = new Registro(null, sdf.parse("09/10/2019 10:01:30"), 430, UnidadeEnum.LX, sensor1, sublocal1);
		Registro r20 = new Registro(null, sdf.parse("09/10/2019 10:01:35"), 411, UnidadeEnum.LX, sensor1, sublocal1);
		Registro r21 = new Registro(null, sdf.parse("09/10/2019 10:01:40"), 419, UnidadeEnum.LX, sensor1, sublocal1);
		Registro r22 = new Registro(null, sdf.parse("09/10/2019 10:01:45"), 418, UnidadeEnum.LX, sensor1, sublocal1);
		Registro r23 = new Registro(null, sdf.parse("09/10/2019 10:01:50"), 406, UnidadeEnum.LX, sensor1, sublocal1);
		Registro r24 = new Registro(null, sdf.parse("09/10/2019 10:01:55"), 407, UnidadeEnum.LX, sensor1, sublocal1);
		Registro r25 = new Registro(null, sdf.parse("09/10/2019 10:02:00"), 431, UnidadeEnum.LX, sensor1, sublocal1);
		Registro r26 = new Registro(null, sdf.parse("09/10/2019 10:00:00"), 240, UnidadeEnum.LX, sensor2, sublocal5);
		Registro r27 = new Registro(null, sdf.parse("09/10/2019 10:00:05"), 210, UnidadeEnum.LX, sensor2, sublocal5);
		Registro r28 = new Registro(null, sdf.parse("09/10/2019 10:00:10"), 260, UnidadeEnum.LX, sensor2, sublocal5);
		Registro r29 = new Registro(null, sdf.parse("09/10/2019 10:00:15"), 251, UnidadeEnum.LX, sensor2, sublocal5);
		Registro r30 = new Registro(null, sdf.parse("09/10/2019 10:00:20"), 246, UnidadeEnum.LX, sensor2, sublocal5);
		Registro r31 = new Registro(null, sdf.parse("09/10/2019 10:00:25"), 270, UnidadeEnum.LX, sensor2, sublocal5);
		Registro r32 = new Registro(null, sdf.parse("09/10/2019 10:00:30"), 280, UnidadeEnum.LX, sensor2, sublocal5);
		Registro r33 = new Registro(null, sdf.parse("09/10/2019 10:00:35"), 200, UnidadeEnum.LX, sensor2, sublocal5);
		Registro r34 = new Registro(null, sdf.parse("09/10/2019 10:00:40"), 243, UnidadeEnum.LX, sensor2, sublocal5);
		Registro r35 = new Registro(null, sdf.parse("09/10/2019 10:00:45"), 211, UnidadeEnum.LX, sensor2, sublocal5);
		Registro r36 = new Registro(null, sdf.parse("09/10/2019 10:00:50"), 216, UnidadeEnum.LX, sensor2, sublocal5);
		Registro r37 = new Registro(null, sdf.parse("09/10/2019 10:00:55"), 221, UnidadeEnum.LX, sensor2, sublocal5);
		Registro r38 = new Registro(null, sdf.parse("09/10/2019 10:01:00"), 254, UnidadeEnum.LX, sensor2, sublocal5);
		Registro r39 = new Registro(null, sdf.parse("09/10/2019 10:01:05"), 241, UnidadeEnum.LX, sensor2, sublocal5);
		Registro r40 = new Registro(null, sdf.parse("09/10/2019 10:01:10"), 219, UnidadeEnum.LX, sensor2, sublocal5);
		Registro r41 = new Registro(null, sdf.parse("09/10/2019 10:01:15"), 263, UnidadeEnum.LX, sensor2, sublocal5);
		Registro r42 = new Registro(null, sdf.parse("09/10/2019 10:01:20"), 278, UnidadeEnum.LX, sensor2, sublocal5);
		Registro r43 = new Registro(null, sdf.parse("09/10/2019 10:01:25"), 233, UnidadeEnum.LX, sensor2, sublocal5);
		Registro r44 = new Registro(null, sdf.parse("09/10/2019 10:01:30"), 217, UnidadeEnum.LX, sensor2, sublocal5);
		Registro r45 = new Registro(null, sdf.parse("09/10/2019 10:01:35"), 243, UnidadeEnum.LX, sensor2, sublocal5);
		Registro r46 = new Registro(null, sdf.parse("09/10/2019 10:01:40"), 251, UnidadeEnum.LX, sensor2, sublocal5);
		Registro r47 = new Registro(null, sdf.parse("09/10/2019 10:01:45"), 234, UnidadeEnum.LX, sensor2, sublocal5);
		Registro r48 = new Registro(null, sdf.parse("09/10/2019 10:01:50"), 216, UnidadeEnum.LX, sensor2, sublocal5);
		Registro r49 = new Registro(null, sdf.parse("09/10/2019 10:01:55"), 227, UnidadeEnum.LX, sensor2, sublocal5);
		Registro r50 = new Registro(null, sdf.parse("09/10/2019 10:02:00"), 205, UnidadeEnum.LX, sensor2, sublocal5);
		
		sublocal1.getSensores().addAll(Arrays.asList(sensor1));
		sublocal5.getSensores().addAll(Arrays.asList(sensor2));
		
		sublocal1.getRegistros().addAll(Arrays.asList(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, 
				r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, 
				r21, r22, r23, r24, r25));
		sublocal5.getRegistros().addAll(Arrays.asList(r26, r27, r28, r29, r30, 
				r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, 
				r41, r42, r43, r44, r45, r46, r47, r48, r49, r50));
		
		lei1.getSensores().addAll(Arrays.asList(sensor1));
		lei2.getSensores().addAll(Arrays.asList(sensor2));
		
		sensor1.getRegistros().addAll(Arrays.asList(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, 
				r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, 
				r21, r22, r23, r24, r25));
		sensor2.getRegistros().addAll(Arrays.asList(r26, r27, r28, r29, r30, 
				r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, 
				r41, r42, r43, r44, r45, r46, r47, r48, r49, r50));
		sensor1.getLeis().addAll(Arrays.asList(lei1));
		sensor2.getLeis().addAll(Arrays.asList(lei2));
		
		localRepository.saveAll(Arrays.asList(local1, local2, local3, local4, local5));
		sublocalRepository.saveAll(Arrays.asList(sublocal1, sublocal2, sublocal3, sublocal4, sublocal5, sublocal6, sublocal7, 
				sublocal8, sublocal9, sublocal10));
		leiRepository.saveAll(Arrays.asList(lei1, lei2));
		sensorRepository.saveAll(Arrays.asList(sensor1, sensor2));
		registroRepository.saveAll(Arrays.asList(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, 
				r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, 
				r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, 
				r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, 
				r41, r42, r43, r44, r45, r46, r47, r48, r49, r50));
		
		LOG.info("Banco de TESTE populado com sucesso!!!");
		
	}
	
}
