package com.unla.Grupo23OO22021.models;

import java.time.LocalDate;
import java.util.Set;

import com.unla.Grupo23OO22021.entities.Persona;
import com.unla.Grupo23OO22021.entities.Lugar;

public class PermisoDiarioModel extends PermisoModel {
	
	private String motivo;
	
	public PermisoDiarioModel() {}

	public PermisoDiarioModel(int idPermiso, Persona persona, LocalDate fecha, Set<Lugar> desdeHasta, String motivo) {
		super();
		this.motivo = motivo;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	@Override
	public String toString() {
		return "PermisoDiarioModel [motivo=" + motivo + "]";
	}
	

}
