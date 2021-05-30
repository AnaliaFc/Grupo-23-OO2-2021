package com.unla.Grupo23OO22021.models;

import org.springframework.format.annotation.DateTimeFormat;

public class FilterModel {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String fechaInicio;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String fechaFin;
	
	public FilterModel() {
		
	}

	public FilterModel(String fechaInicio, String fechaFin) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	
}
