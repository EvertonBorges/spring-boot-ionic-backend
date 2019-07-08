package com.evertonborges.cursomc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.evertonborges.cursomc.services.DBService;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private DBService dbService;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dbService.instantiateTestDatabase();
	}

}
