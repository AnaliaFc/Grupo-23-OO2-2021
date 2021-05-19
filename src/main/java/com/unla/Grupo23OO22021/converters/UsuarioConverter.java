package com.unla.Grupo23OO22021.converters;

import com.unla.Grupo23OO22021.entities.Usuario;
import com.unla.Grupo23OO22021.models.UsuarioModel;

public class UsuarioConverter {
	public Usuario modelToEntity(UsuarioModel usuarioModel) {
		return new Usuario(usuarioModel.getIdUsuario(), usuarioModel.getDni(), usuarioModel.getNombre(),
				usuarioModel.getApellido(), usuarioModel.getEmail(), usuarioModel.getUsername(),
				usuarioModel.getPassword(), usuarioModel.getDocumento(), usuarioModel.getPerfil());
	}
	
	public UsuarioModel entityToModel(Usuario usuario) {
		return new UsuarioModel(usuario.getIdUsuario(), usuario.getDni(), usuario.getNombre(),
				usuario.getApellido(), usuario.getEmail(), usuario.getUsername(),
				usuario.getPassword(), usuario.getDocumento(), usuario.getPerfil());
	}
}
