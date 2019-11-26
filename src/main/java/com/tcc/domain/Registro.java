package com.tcc.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcc.enums.UnidadeEnum;

@Entity
public class Registro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataHora;
	private Integer registro;
	private Integer unidade;
	
	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name = "sensor_id")
	private Sensor sensor;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "sublocal_id")
	private Sublocal sublocal;
	
	public Registro() {
		super();
		unidade = UnidadeEnum.LX.getCod();
	}

	public Registro(Integer id, Date dataHora, Integer registro, UnidadeEnum unidade, Sensor sensor, Sublocal sublocal) {
		super();
		this.id = id;
		this.dataHora = dataHora;
		this.registro = registro;
		this.unidade = (unidade == null) ? null : unidade.getCod();;
		this.sensor = sensor;
		this.sublocal = sublocal;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public Integer getRegistro() {
		return registro;
	}

	public void setRegistro(Integer registro) {
		this.registro = registro;
	}

	public UnidadeEnum getUnidade() {
		return UnidadeEnum.toEnum(unidade);
	}

	public void setUnidade(UnidadeEnum unidade) {
		this.unidade = unidade.getCod();
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public Sublocal getSublocal() {
		return sublocal;
	}

	public void setSublocal(Sublocal sublocal) {
		this.sublocal = sublocal;
	}
	
}