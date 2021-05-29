package com.unla.Grupo23OO22021.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo23OO22021.converters.PermisoConverter;
import com.unla.Grupo23OO22021.converters.PermisoDiarioConverter;
import com.unla.Grupo23OO22021.converters.PermisoPeriodoConverter;
import com.unla.Grupo23OO22021.entities.Permiso;
import com.unla.Grupo23OO22021.models.PermisoModel;
import com.unla.Grupo23OO22021.models.PersonaModel;
import com.unla.Grupo23OO22021.repositories.IPermisoRepository;
import com.unla.Grupo23OO22021.services.IPermisoService;

@Service("permisoService")
public class PermisoService implements IPermisoService{
	
	@Autowired
	@Qualifier("permisoConverter")
	private PermisoConverter permisoConverter;
	
	@Autowired
	@Qualifier("permisoDiarioConverter")
	private PermisoDiarioConverter permisoDiarioConverter;
	
	@Autowired
	@Qualifier("permisoPeriodoConverter")
	private PermisoPeriodoConverter permisoPeriodoConverter;
	
	
	@Autowired
	@Qualifier("permisoRepository")
	private IPermisoRepository permisoRepository;

	@Override
	public List<PermisoModel> findAll() {
		List<PermisoModel> permisos = new ArrayList<PermisoModel>();
		for(Permiso permiso : permisoRepository.findAll())
			permisos.add(permisoConverter.entityToModel(permiso));
		return permisos;
	}

	@Override
	public PermisoModel findById(int id) {
		
		return permisoConverter.entityToModel(permisoRepository.findById(id));
	}

	@Override
	public PermisoModel insertOrUpdate(PermisoModel permisoModel) {
		
		return permisoConverter.entityToModel(permisoRepository.save(permisoConverter.modelToEntity(permisoModel)));
	}

	@Override
	public boolean remove(int id) {
		try {
			permisoRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
