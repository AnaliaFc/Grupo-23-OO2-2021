package com.unla.Grupo23OO22021.converters;

import java.time.Period;

import org.springframework.stereotype.Component;

import com.unla.Grupo23OO22021.entities.Perfil;
import com.unla.Grupo23OO22021.entities.Permiso;
import com.unla.Grupo23OO22021.models.PerfilModel;
import com.unla.Grupo23OO22021.models.PermisoModel;

@Component("permisoConverter")
public class PermisoConverter {
	
	public Permiso modelToEntity(PermisoModel permisoModel) {
		return new Permiso(permisoModel.getIdPermiso(),new PersonaConverter.modelToEntity(permisoModel.getPersona()), permisoModel.getFecha(), permisoModel.getDesdeHasta());
	}
	
	public PermisoModel entityToModel(Permiso permiso) {
		return new PermisoModel(permiso.getIdPermiso(),new PersonaConverter.entityToModel(permiso.getPersona()),permiso.getFecha(),permiso.getDesdeHasta());
	}

}
