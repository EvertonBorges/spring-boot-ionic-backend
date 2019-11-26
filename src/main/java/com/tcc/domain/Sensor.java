package com.tcc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Sensor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	@JsonIgnore
	private String senha;
	private String idExterno;
	
	@JsonIgnore
	@OneToMany(mappedBy = "sensor")
	private List<Registro> registros = new ArrayList<>();
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "sublocal_id")
	private Sublocal sublocal;
	
	@ManyToMany
	@JoinTable(name = "DISPOSITIVO_LEI", 
	   joinColumns = @JoinColumn(name = "dispositivo_id"), 
	   inverseJoinColumns = @JoinColumn(name = "lei_id"))
	private List<Lei> leis = new ArrayList<>();

	public Sensor() {
		super();
	}

	public Sensor(Integer id, String nome, String senha, String idExterno, Sublocal sublocal) {
		super();
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.idExterno = idExterno;
		this.sublocal = sublocal;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getIdExterno() {
		return idExterno;
	}

	public void setIdExterno(String idExterno) {
		this.idExterno = idExterno;
	}

	public List<Registro> getRegistros() {
		return registros;
	}

	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}

	public Sublocal getSublocal() {
		return sublocal;
	}

	public void setSublocal(Sublocal sublocal) {
		this.sublocal = sublocal;
	}

	public List<Lei> getLeis() {
		return leis;
	}

	public void setLeis(List<Lei> leis) {
		this.leis = leis;
	}
	
}