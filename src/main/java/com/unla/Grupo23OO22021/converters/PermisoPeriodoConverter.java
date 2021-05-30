package com.unla.Grupo23OO22021.converters;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unla.Grupo23OO22021.entities.Lugar;
import com.unla.Grupo23OO22021.entities.PermisoPeriodo;
import com.unla.Grupo23OO22021.models.LugarModel;
import com.unla.Grupo23OO22021.models.PermisoPeriodoModel;

@Component("permisoPeriodoConverter")
public class PermisoPeriodoConverter {
	
	@Autowired
	private LugarConverter lugarConverter;
	
	@Autowired
	private PersonaConverter personaConverter;
	
	@Autowired
	private RodadoConverter rodadoConverter;
	
	public PermisoPeriodo modelToEntity(PermisoPeriodoModel permisoModel) {
		Set<Lugar> lugares=new HashSet<Lugar>();
		for(LugarModel lugarModel : permisoModel.getDesdeHasta()) {
			lugares.add(lugarConverter.modelToEntity(lugarModel));
		}
		return new PermisoPeriodo(permisoModel.getIdPermiso(),
				personaConverter.modelToEntity(permisoModel.getPersona())
				, permisoModel.getFecha().toLocalDate(), lugares,permisoModel.getCantDias(), permisoModel.isVacaciones(), 
				rodadoConverter.modelToEntity(permisoModel.getRodado()));
	}
	
	public  PermisoPeriodoModel entityToModel(PermisoPeriodo permiso) {
		List<LugarModel> lugares=new ArrayList<LugarModel>();
		for(Lugar lugar : permiso.getDesdeHasta()) {
			lugares.add(lugarConverter.entityToModel(lugar));
		}
		return new PermisoPeriodoModel(permiso.getIdPermiso(),
				personaConverter.entityToModel(permiso.getPersona())
				,Date.valueOf(permiso.getFecha())
				,lugares, permiso.getCantDias(), permiso.isVacaciones(),
				rodadoConverter.entityToModel(permiso.getRodado()));
	}

}
