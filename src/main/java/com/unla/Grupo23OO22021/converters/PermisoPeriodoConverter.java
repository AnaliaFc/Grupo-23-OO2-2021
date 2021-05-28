package com.unla.Grupo23OO22021.converters;

import com.unla.Grupo23OO22021.entities.PermisoPeriodo;
import com.unla.Grupo23OO22021.models.PermisoPeriodoModel;

public class PermisoPeriodoConverter {
	
	public PermisoPeriodo modelToEntity(PermisoPeriodoModel permisoModel) {
		return new PermisoPeriodo(permisoModel.getIdPermiso(),permisoModel.getPersona(), permisoModel.getFecha(), permisoModel.getDesdeHasta(),permisoModel.getCantDias(), permisoModel.isVacaciones(), permisoModel.getRodado());
	}
	
	public  PermisoPeriodoModel entityToModel(PermisoPeriodo permiso) {
		return new PermisoPeriodoModel(permiso.getIdPermiso(),permiso.getPersona(),permiso.getFecha(),permiso.getDesdeHasta(), permiso.getCantDias(), permiso.isVacaciones(), permiso.getRodado());
	}

}
