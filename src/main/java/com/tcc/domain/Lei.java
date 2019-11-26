package com.tcc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Lei implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String idExterno;
	private String nome;
	private String descricao;
	
	@ManyToMany(mappedBy = "leis")
	@JsonIgnore
	private List<Sensor> sensores = new ArrayList<>();

	public Lei() {
		super();
	}

	public Lei(Integer id, String idExterno, String nome, String descricao) {
		super();
		this.id = id;
		this.idExterno = idExterno;
		this.nome = nome;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdExterno() {
		return idExterno;
	}

	public void setIdExterno(String idExterno) {
		this.idExterno = idExterno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Sensor> getSensores() {
		return sensores;
	}

	public void setSensores(List<Sensor> dispositivos) {
		this.sensores = dispositivos;
	}
	
}