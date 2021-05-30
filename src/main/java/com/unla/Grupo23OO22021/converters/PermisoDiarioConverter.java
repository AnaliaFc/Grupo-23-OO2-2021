package com.unla.Grupo23OO22021.converters;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unla.Grupo23OO22021.entities.Lugar;
import com.unla.Grupo23OO22021.entities.PermisoDiario;
import com.unla.Grupo23OO22021.models.LugarModel;
import com.unla.Grupo23OO22021.models.PermisoDiarioModel;

@Component("permisoDiarioConverter")
public class PermisoDiarioConverter {
	
	@Autowired
	private LugarConverter lugarConverter;
	
	@Autowired
	private PersonaConverter personaConverter;
	
	public PermisoDiario modelToEntity(PermisoDiarioModel permisoModel) {
		Set<Lugar> lugares=new LinkedHashSet<Lugar>();
		for(LugarModel lugarModel : permisoModel.getDesdeHasta()) {
			lugares.add(lugarConverter.modelToEntity(lugarModel));
		}
		return new PermisoDiario(permisoModel.getIdPermiso(),personaConverter.modelToEntity(permisoModel.getPersona()), permisoModel.getFecha().toLocalDate(), lugares, permisoModel.getMotivo());
	}
	
	public PermisoDiarioModel entityToModel(PermisoDiario permiso) {
		List<LugarModel> lugares=new ArrayList<LugarModel>();
		for(Lugar lugar : permiso.getDesdeHasta()) {
			lugares.add(lugarConverter.entityToModel(lugar));
		}
		return new PermisoDiarioModel(permiso.getIdPermiso(), personaConverter.entityToModel(permiso.getPersona()),Date.valueOf(permiso.getFecha()), lugares, permiso.getMotivo());
	}

}
