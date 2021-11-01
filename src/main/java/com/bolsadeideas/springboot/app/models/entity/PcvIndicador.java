package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="list_pcv")
public class PcvIndicador implements Serializable {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String fecha;
	
	private Double valorReciente;
	
	private Double valorAnterior;
	
	private Double resultado;
	
	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getFecha() {
		return fecha;
	}



	public void setFecha(String fecha) {
		this.fecha = fecha;
	}



	public Double getValorReciente() {
		return valorReciente;
	}



	public void setValorReciente(Double valorReciente) {
		this.valorReciente = valorReciente;
	}



	public Double getValorAnterior() {
		return valorAnterior;
	}



	public void setValorAnterior(Double valorAnterior) {
		this.valorAnterior = valorAnterior;
	}



	public Double getResultado() {
		return resultado;
	}



	public void setResultado(Double resultado) {
		this.resultado = resultado;
	}



	private static final long serialVersionUID = 1L;
}
