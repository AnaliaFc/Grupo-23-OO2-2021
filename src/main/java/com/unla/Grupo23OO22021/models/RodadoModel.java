package com.unla.Grupo23OO22021.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RodadoModel {
	
	private long idRodado;
	
	@NotNull(message="Campo dominio no puede ser vacio")
	@Pattern(regexp = "^[A-Z]{3}[0-9]{3}|[A-Z]{2}[0-9]{3}[A-Z]{2}$", message ="Patente debe ser formato AAA999 o AA123AA")
	private String dominio;
	
	@NotNull(message="Campo vehiculo no puede ser vacio")
	@Size(min=5,max=50, message="Longitud del vehiculo debe ser entre 5 y 50 caracteres")
	private String vehiculo;
	
	public RodadoModel() {}
	
	public RodadoModel(long idRodado, @Size(min = 6, max = 10) String dominio,
			@Size(min = 5, max = 50) String vehiculo) {
		super();
		this.idRodado = idRodado;
		this.dominio = dominio;
		this.vehiculo = vehiculo;
	}

	public RodadoModel(String dominio, String vehiculo)
	{
		super();
		this.dominio=dominio;
		this.vehiculo=vehiculo;
	}

	public long getIdRodado() {
		return idRodado;
	}

	public void setIdRodado(long idRodado) {
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
		return true;
	}

	@Override
	public String toString() {
		return "Rodado con Dominio:" + dominio + "y tipo de Vehiculo:" + vehiculo;
	}
	
	
	

}