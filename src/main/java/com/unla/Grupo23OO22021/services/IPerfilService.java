package com.unla.Grupo23OO22021.services;

import java.util.List;

import com.unla.Grupo23OO22021.models.PerfilModel;

public interface IPerfilService {

	public List<PerfilModel> traerPerfiles();

	public PerfilModel traerId(long id);

	public PerfilModel insertOrUpdate(PerfilModel perfilModel);
		
	public boolean remove(long id);

}
