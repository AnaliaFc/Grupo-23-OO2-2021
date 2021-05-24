package com.unla.Grupo23OO22021.services;

import java.util.List;
import java.util.Optional;

import com.unla.Grupo23OO22021.entities.Perfil;

public interface IPerfilService {

	public List<Perfil> listar();
	public Optional<Perfil> listarId(long id);
	public boolean save (Perfil p);
	public void delete(long id);

}
