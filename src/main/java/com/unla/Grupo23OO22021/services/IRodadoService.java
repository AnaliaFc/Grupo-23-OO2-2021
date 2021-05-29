package com.unla.Grupo23OO22021.services;

import java.util.List;

import com.unla.Grupo23OO22021.models.RodadoModel;

public interface IRodadoService {
	
	public List<RodadoModel> traerRodados();
	
	public RodadoModel traerDominio(String dominio);

	public RodadoModel traerId(long id);

	public RodadoModel insertOrUpdate(RodadoModel rodadoModel);
		
	public boolean remove(long id);

}
