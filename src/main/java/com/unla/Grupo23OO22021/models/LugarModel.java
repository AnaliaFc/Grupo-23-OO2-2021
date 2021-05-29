package com.unla.Grupo23OO22021.models;

import javax.validation.constraints.Size;

import com.unla.Grupo23OO22021.entities.Lugar;

public class LugarModel {

	private long idLugar;
	
	@Size(min=3,max=20)
	private String lugar;
	
	@Size(min=4,max=5)
	private String codigoPostal;
	
	
	public LugarModel(){}


	
	
	public LugarModel(long idLugar, @Size(min = 3, max = 20) String lugar,
			@Size(min = 4, max = 5) String codigoPostal) {
		super();
		this.idLugar = idLugar;
		this.lugar = lugar;
		this.codigoPostal = codigoPostal;
	}




	public LugarModel(String lugar, String codigoPostal) {
		this.lugar = lugar;
		this.codigoPostal = codigoPostal;
	}


	public long getIdLugar() {
		return idLugar;
	}


	protected void setIdLugar(long idLugar) {
		this.idLugar = idLugar;
	}


	public String getLugar() {
		return lugar;
	}


	public void setLugar(String lugar) {
		this.lugar = lugar;
	}


	public String getCodigoPostal() {
		return codigoPostal;
	}


	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idLugar ^ (idLugar >>> 32));
		return result;
	}

	
	public boolean equals(LugarModel lugar) {
		return codigoPostal.equalsIgnoreCase(lugar.codigoPostal) && this.lugar.equalsIgnoreCase(lugar.lugar);
	}


	@Override
	public String toString() {
		return "Nombre: " + lugar + "| Codigo Postal: " + codigoPostal + "]";
	}
	
	
	
}