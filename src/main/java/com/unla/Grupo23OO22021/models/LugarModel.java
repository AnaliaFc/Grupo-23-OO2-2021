package com.unla.Grupo23OO22021.models;

import javax.validation.constraints.Size;

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


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LugarModel other = (LugarModel) obj;
		if (idLugar != other.idLugar)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Nombre: " + lugar + "| Codigo Postal: " + codigoPostal + "]";
	}
	
	
	
}