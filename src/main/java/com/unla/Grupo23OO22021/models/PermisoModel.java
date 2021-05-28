package com.unla.Grupo23OO22021.models;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import com.unla.Grupo23OO22021.entities.Persona;
import com.unla.Grupo23OO22021.entities.Lugar;

public class PermisoModel {
	
	protected int idPermiso;
	@NotEmpty
	protected Persona persona;
	@NotEmpty
	protected LocalDate fecha;
	protected Set<Lugar> desdeHasta;
	
	public PermisoModel() {}
	
	public PermisoModel(int idPermiso, Persona persona, LocalDate fecha, Set<Lugar> desdeHasta) {
		super();
		this.idPermiso = idPermiso;
		this.persona = persona;
		this.fecha = fecha;
		this.desdeHasta = desdeHasta;
	}

	public int getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Set<Lugar> getDesdeHasta() {
		return desdeHasta;
	}

	public void setDesdeHasta(Set<Lugar> desdeHasta) {
		this.desdeHasta = desdeHasta;
	}

	@Override
	public String toString() {
		return "Permiso [idPermiso=" + idPermiso + ", fecha=" + fecha + "]";
	}
	
}

