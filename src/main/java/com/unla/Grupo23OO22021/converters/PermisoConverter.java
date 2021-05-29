package com.unla.Grupo23OO22021.converters;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unla.Grupo23OO22021.entities.Lugar;
import com.unla.Grupo23OO22021.entities.Permiso;
import com.unla.Grupo23OO22021.models.LugarModel;
import com.unla.Grupo23OO22021.models.PermisoModel;

@Component("permisoConverter")
public class PermisoConverter {
	
	@Autowired
	private LugarConverter lugarConverter;
	
	@Autowired
	private PersonaConverter personaConverter;
	
	
	public Permiso modelToEntity(PermisoModel permisoModel) {
		List<Lugar> lugares=new ArrayList<Lugar>();
		for(LugarModel lugarModel : permisoModel.getDesdeHasta()) {
			lugares.add(lugarConverter.modelToEntity(lugarModel));
		}
		return new Permiso(permisoModel.getIdPermiso(),personaConverter.modelToEntity(permisoModel.getPersona()), permisoModel.getFecha().toLocalDate(), lugares);
	}
	
	public PermisoModel entityToModel(Permiso permiso) {
		List<LugarModel> lugares=new ArrayList<LugarModel>();
		for(Lugar lugar : permiso.getDesdeHasta()) {
			lugares.add(lugarConverter.entityToModel(lugar));
		}
		return new PermisoModel(permiso.getIdPermiso(),personaConverter.entityToModel(permiso.getPersona()),Date.valueOf(permiso.getFecha()),lugares);
	}

}
