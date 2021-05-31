package com.unla.Grupo23OO22021.models;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import com.unla.Grupo23OO22021.entities.Persona;
import com.unla.Grupo23OO22021.entities.Lugar;

public class PermisoDiarioModel extends PermisoModel {
	
	@NotEmpty
	private String motivo;
	
	public PermisoDiarioModel() {
		super();
	}

	public PermisoDiarioModel(int idPermiso, PersonaModel persona, Date fecha, List<LugarModel> desdeHasta, String motivo) {
		super(idPermiso, persona, fecha, desdeHasta);
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
		return super.toString()+" -> PermisoDiarioModel [motivo=" + motivo + "]";
	}
	

}