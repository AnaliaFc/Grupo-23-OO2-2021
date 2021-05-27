package com.unla.Grupo23OO22021.converters;

import org.springframework.stereotype.Component;

import com.unla.Grupo23OO22021.entities.Usuario;
import com.unla.Grupo23OO22021.models.UsuarioModel;

@Component("usuarioConverter")
public class UsuarioConverter {
	public Usuario modelToEntity(UsuarioModel usuarioModel) {
		return new Usuario(usuarioModel.getIdPersona(), usuarioModel.getApellido(), usuarioModel.getNombre(),
				usuarioModel.getDni(), usuarioModel.getEmail(), usuarioModel.getUsername(),
				usuarioModel.getPassword(), usuarioModel.getTipoDocumento(),
				new PerfilConverter().modelToEntity(usuarioModel.getPerfil()));
	}

	public UsuarioModel entityToModel(Usuario usuario) {
		return new UsuarioModel(usuario.getIdPersona(), usuario.getDni(), usuario.getNombre(), usuario.getApellido(),
				usuario.getEmail(), usuario.getUsername(), usuario.getPassword(), usuario.getTipoDocumento(),
				new PerfilConverter().entityToModel(usuario.getPerfil()));
	}
}
