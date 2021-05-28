package com.unla.Grupo23OO22021.services;

import java.util.List;

import com.unla.Grupo23OO22021.models.PermisoModel;
import com.unla.Grupo23OO22021.models.PersonaModel;

public interface IPermisoService {
	public List<PermisoModel> findAll();
	public PermisoModel findById(int id);
	public PermisoModel insertOrUpdate(PermisoModel permisoModel);
	public boolean remove(int id);
}
