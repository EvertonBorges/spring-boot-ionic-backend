package com.tcc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Sublocal implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "local_id")
	private Local local;
	
	@JsonIgnore
	@OneToMany(mappedBy = "sublocal")
	private List<Sensor> sensores = new ArrayList<>();
	
	@OneToMany(mappedBy = "sublocal")
	private List<Registro> registros = new ArrayList<>();

	public Sublocal() {
		super();
	}

	public Sublocal(String nome) {
		super();
		this.id = null;
		this.nome = nome;
	}
	
	public Sublocal(String nome, Local local) {
		super();
		this.id = null;
		this.nome = nome;
		this.local = local;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public List<Sensor> getSensores() {
		return sensores;
	}

	public void setSensores(List<Sensor> sensores) {
		this.sensores = sensores;
	}

	public List<Registro> getRegistros() {
		return registros;
	}

	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}

	@Override
	public String toString() {
		return local.getNome() + " - " + nome;
	}
	
}