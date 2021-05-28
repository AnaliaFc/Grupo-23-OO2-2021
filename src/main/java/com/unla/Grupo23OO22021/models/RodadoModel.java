package com.unla.Grupo23OO22021.models;

import javax.validation.constraints.Size;

public class RodadoModel {
	
	private long idRodado;
	
	@Size(min=6,max=10)
	private String dominio;
	
	@Size(min=5,max=50)
	private String vehiculo;
	
	public RodadoModel() {}
	
	
	
	public RodadoModel(long idRodado, @Size(min = 6, max = 10) String dominio,
			@Size(min = 5, max = 50) String vehiculo) {
		this.idRodado = idRodado;
		this.dominio = dominio;
		this.vehiculo = vehiculo;
	}



	public RodadoModel(String dominio, String vehiculo)
	{
		this.dominio=dominio;
		this.vehiculo=vehiculo;
	}

	public long getIdRodado() {
		return idRodado;
	}

	protected void setIdRodado(long idRodado) {
		this.idRodado = idRodado;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dominio == null) ? 0 : dominio.hashCode());
		result = prime * result + ((vehiculo == null) ? 0 : vehiculo.hashCode());
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
		RodadoModel other = (RodadoModel) obj;
		if (dominio == null) {
			if (other.dominio != null)
				return false;
		} else if (!dominio.equals(other.dominio))
			return false;
		if (vehiculo == null) {
			if (other.vehiculo != null)
				return false;
		} else if (!vehiculo.equals(other.vehiculo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Informacion del rodado---> Dominio:" + dominio + "| Vehiculo=" + vehiculo + "\n";
	}
	
	
	

}