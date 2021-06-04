package com.unla.Grupo23OO22021.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unla.Grupo23OO22021.entities.Perfil;
import com.unla.Grupo23OO22021.entities.Usuario;
import com.unla.Grupo23OO22021.models.PerfilModel;
import com.unla.Grupo23OO22021.models.UsuarioModel;
import com.unla.Grupo23OO22021.repositories.IPerfilRepository;
import com.unla.Grupo23OO22021.services.implementation.PerfilService;

@Component("usuarioConverter")
public class UsuarioConverter {
	
	@Autowired
	private PerfilService perfilService;
	@Autowired
	private IPerfilRepository perfilRep;
	
	public Usuario modelToEntity(UsuarioModel usuarioModel) {
		
		Perfil perfil = perfilRep.findById(usuarioModel.getPerfil().getIdPerfil());
		return new Usuario(usuarioModel.getIdPersona(),usuarioModel.getNombre(),usuarioModel.getApellido(), 
				usuarioModel.getDni(), usuarioModel.getEmail(), usuarioModel.getUsername(),
				usuarioModel.getPassword(), usuarioModel.getTipoDocumento(),perfil);
	}

	public UsuarioModel entityToModel(Usuario usuario) {
		
		PerfilModel perfilM = perfilService.traerId(usuario.getPerfil().getIdPerfil());
		return new UsuarioModel(usuario.getIdPersona(), usuario.getDni(), usuario.getNombre(), usuario.getApellido(),
				usuario.getEmail(), usuario.getUsername(), usuario.getPassword(), usuario.getTipoDocumento(),
				perfilM);
	}
}
