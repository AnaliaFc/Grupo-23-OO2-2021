package com.unla.Grupo23OO22021.converters;

import com.unla.Grupo23OO22021.entities.PermisoDiario;
import com.unla.Grupo23OO22021.models.PermisoDiarioModel;

public class PermisoDiarioConverter {
	
	public PermisoDiario modelToEntity(PermisoDiarioModel permisoModel) {
		return new PermisoDiario(permisoModel.getIdPermiso(),permisoModel.getPersona(), permisoModel.getFecha(), permisoModel.getDesdeHasta(), permisoModel.getMotivo());
	}
	
	public PermisoDiarioModel entityToModel(PermisoDiario permiso) {
		return new PermisoDiarioModel(permiso.getIdPermiso(),permiso.getPersona(),permiso.getFecha(),permiso.getDesdeHasta(),permiso.getMotivo());
	}

}
