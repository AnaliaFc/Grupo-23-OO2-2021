package com.unla.Grupo23OO22021.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

public class FilterModel {
	@NotEmpty
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String fechaInicio;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotEmpty
	private String fechaFin;
	
	
	private LugarModel lugar;
	
	public FilterModel() {
		
	}

	public FilterModel(String fechaInicio, String fechaFin) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	
	

	public FilterModel(String fechaInicio, String fechaFin, LugarModel lugar) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.lugar=lugar;
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

	public LugarModel getLugar() {
		return lugar;
	}

	public void setLugar(LugarModel lugar) {
		this.lugar = lugar;
	}

	
	
	
	
}
