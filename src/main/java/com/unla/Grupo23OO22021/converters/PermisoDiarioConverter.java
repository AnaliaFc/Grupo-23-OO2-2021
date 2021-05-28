package com.unla.Grupo23OO22021.converters;

import com.unla.Grupo23OO22021.entities.PermisoDiario;
import com.unla.Grupo23OO22021.models.PermisoDiarioModel;

public class PermisoDiarioConverter {
	
	public PermisoDiario modelToEntity(PermisoDiarioModel permisoModel) {
		return new PermisoDiario(PermisoDiarioModel.getIdPermiso(),new PersonaConverter.modelToEntity(PermisoDiarioModel.getPersona()), PermisoDiarioModel.getFecha(), PermisoDiarioModel.getDesdeHasta(), PermisoDiarioModel.getMotivo());
	}
	
	public PermisoDiarioModel entityToModel(PermisoDiario permiso) {
		return new PermisoDiarioModel(PermisoDiario.getIdPermiso(),new PersonaConverter.entityToModel(PermisoDiario.getPersona()),PermisoDiario.getFecha(),PermisoDiario.getDesdeHasta(),PermisoDiario.getMotivo());
	}

}
