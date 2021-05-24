package com.unla.Grupo23OO22021.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.Grupo23OO22021.entities.Perfil;
import com.unla.Grupo23OO22021.repositories.IPerfilRepository;
import com.unla.Grupo23OO22021.services.IPerfilService;

@Service
public class PerfilService implements IPerfilService{
	
	@Autowired
	private IPerfilRepository data;

	@Override
	public List<Perfil> listar() {
		return (List<Perfil>)data.findAll();
	}

	@Override
	public Optional<Perfil> listarId(long idPerfil) {
		return data.findById(idPerfil);
	}

	@Override
	public boolean save(Perfil p) {
		boolean flag = false;
		Perfil perfil = data.save(p);
		if (perfil!=null) {
			flag=true;
		}
		return flag;
	}

	@Override
	public void delete(long id) {
		data.deleteById(id);
	}
	
	
	
}
