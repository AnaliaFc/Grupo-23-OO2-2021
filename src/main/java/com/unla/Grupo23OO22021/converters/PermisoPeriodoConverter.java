package com.unla.Grupo23OO22021.converters;

import com.unla.Grupo23OO22021.entities.PermisoPeriodo;
import com.unla.Grupo23OO22021.models.PermisoPeriodoModel;

public class PermisoPeriodoConverter {
	
	public PermisoPeriodo modelToEntity(PermisoPeriodoModel permisoModel) {
		return new PermisoDiario(PermisoPeriodoModel.getIdPermiso(),new PersonaConverter.modelToEntity(PermisoPeriodoModel.getPersona()), PermisoPeriodoModel.getFecha(), PermisoPeriodoModel.getDesdeHasta(),PermisoPeriodoModel.getCantDias(), PermisoPeriodoModel.isVacaciones(), PermisoPeriodoModel.getRodado());
	}
	
	public  PermisoPeriodoModel entityToModel( PermisoPeriodo permiso) {
		return new PermisoDiarioModel(PermisoPeriodo.getIdPermiso(),new PersonaConverter.entityToModel(PermisoPeriodo.getPersona()),PermisoPeriodo.getFecha(),PermisoPeriodo.getDesdeHasta(), PermisoPeriodo.getCantDias(), PermisoPeriodo.isVacaciones(), PermisoPeriodo.getRodado());
	}

}
