package com.unla.Grupo23OO22021.models;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.unla.Grupo23OO22021.entities.Persona;
import com.unla.Grupo23OO22021.entities.Lugar;

public class PermisoDiarioModel extends PermisoModel {
	
	@Size(min = 5, max = 100, message = "Debe tener un motivo entre 5 y 100 caracteres")
	private String motivo;
	
	public PermisoDiarioModel() {
		super();
		motivo=new String();
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
	
	@Override
	public String generarUrl()
	{
		return super.generarUrl()+"&motivo="+motivo;
	}
	
	
}