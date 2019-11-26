package com.tcc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

public class RegistroDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String idExternoDispositivo;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String instante;
	
	@PositiveOrZero
	private Integer registro;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 2, max = 2, message = "O tamanho deve ser de 2 caracteres")
	private String unidade;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String senha;

	public RegistroDTO() {
		super();
	}

	public String getIdExternoDispositivo() {
		return idExternoDispositivo;
	}

	public void setIdExternoDispositivo(String idExternoDispositivo) {
		this.idExternoDispositivo = idExternoDispositivo;
	}

	public String getInstante() {
		return instante;
	}

	public void setInstante(String instante) {
		this.instante = instante;
	}

	public Integer getRegistro() {
		return registro;
	}

	public void setRegistro(Integer registro) {
		this.registro = registro;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}