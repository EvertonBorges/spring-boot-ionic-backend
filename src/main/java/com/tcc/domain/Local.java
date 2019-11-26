package com.tcc.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Local implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	@JsonIgnore
	@OneToMany (mappedBy = "local")
	private Set<Sublocal> sublocais = new HashSet<>();

	public Local() {
		super();
	}

	public Local(String nome) {
		super();
		this.id = null;
		this.nome = nome;
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

	public Set<Sublocal> getSublocais() {
		return sublocais;
	}

	public void setSublocais(Set<Sublocal> sublocais) {
		this.sublocais = sublocais;
	}
	
}