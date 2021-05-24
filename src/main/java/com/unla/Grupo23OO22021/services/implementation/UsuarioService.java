package com.unla.Grupo23OO22021.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo23OO22021.converters.UsuarioConverter;
import com.unla.Grupo23OO22021.entities.Usuario;
import com.unla.Grupo23OO22021.models.UsuarioModel;
import com.unla.Grupo23OO22021.repositories.IUsuarioRepository;
import com.unla.Grupo23OO22021.services.IUsuarioService;



@Service("usuarioService")
public class UsuarioService implements IUsuarioService {

	@Autowired
	@Qualifier("usuarioRepository")
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	@Qualifier("usuarioConverter")
	private UsuarioConverter usuarioConverter;

	@Override
	public List<UsuarioModel> traerUsuarios() {
		List<UsuarioModel> models = new ArrayList<UsuarioModel>();
		for (Usuario user : usuarioRepository.findAll()) {
			models.add(usuarioConverter.entityToModel(user));
		}
		return models;
	}

	@Override
	public UsuarioModel traerId(long id) {
		return usuarioConverter.entityToModel(usuarioRepository.findById(id));
	}

	@Override
	public UsuarioModel insertOrUpdate(UsuarioModel userModel) {
		Usuario user = usuarioRepository.save(usuarioConverter.modelToEntity(userModel));
		return usuarioConverter.entityToModel(user);
	}

	@Override
	public boolean remove(long id) {
		try {
			usuarioRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public UsuarioModel traerDocumento(int dni) {
		return usuarioConverter.entityToModel(usuarioRepository.findByDocumento(dni));
	}
	
}
