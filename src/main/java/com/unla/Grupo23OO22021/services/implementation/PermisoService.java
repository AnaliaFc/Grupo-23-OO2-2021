 package com.unla.Grupo23OO22021.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unla.Grupo23OO22021.converters.PermisoDiarioConverter;
import com.unla.Grupo23OO22021.converters.PermisoPeriodoConverter;
import com.unla.Grupo23OO22021.entities.Permiso;
import com.unla.Grupo23OO22021.entities.PermisoDiario;
import com.unla.Grupo23OO22021.entities.PermisoPeriodo;
import com.unla.Grupo23OO22021.models.PermisoDiarioModel;
import com.unla.Grupo23OO22021.models.PermisoModel;
import com.unla.Grupo23OO22021.models.PermisoPeriodoModel;
import com.unla.Grupo23OO22021.models.PersonaModel;
import com.unla.Grupo23OO22021.repositories.IPermisoRepository;
import com.unla.Grupo23OO22021.services.IPermisoService;

@Service("permisoService")
public class PermisoService implements IPermisoService{
	
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
		for(Permiso permiso : permisoRepository.findAll()) {
			if(permiso instanceof PermisoPeriodo)
				permisos.add(permisoPeriodoConverter.entityToModel((PermisoPeriodo)permiso));
			else if(permiso instanceof PermisoDiario)
				permisos.add(permisoDiarioConverter.entityToModel((PermisoDiario)permiso));
		}
			
		return permisos;
	}

	@Override
	public PermisoModel findById(int id) {
		Permiso permiso = permisoRepository.findById(id);
		if(permiso instanceof PermisoPeriodo)
			return permisoPeriodoConverter.entityToModel((PermisoPeriodo)permiso);
		else if(permiso instanceof PermisoDiario)
			return permisoDiarioConverter.entityToModel((PermisoDiario)permiso);
		
		return null;
	}

	@Override
	public PermisoModel insertOrUpdate(PermisoModel permisoModel) {
		if(permisoModel instanceof PermisoDiarioModel)
			return permisoDiarioConverter.entityToModel(
						permisoRepository.save(
									permisoDiarioConverter.modelToEntity((PermisoDiarioModel)permisoModel)
								)
					);
		else if(permisoModel instanceof PermisoPeriodoModel)
			return permisoPeriodoConverter.entityToModel(
						permisoRepository.save(
									permisoPeriodoConverter.modelToEntity((PermisoPeriodoModel)permisoModel)
								)
					);
		return null;
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
