package com.unla.Grupo23OO22021.converters;

import org.springframework.stereotype.Component;

import com.unla.Grupo23OO22021.entities.Perfil;
import com.unla.Grupo23OO22021.models.PerfilModel;

@Component("perfilConverter")
public class PerfilConverter {
	public Perfil modelToEntity(PerfilModel perfilModel) {
		return new Perfil(perfilModel.getIdPerfil(), perfilModel.getTipo());
	}
	
	public PerfilModel entityToModel(Perfil perfil) {
		return new PerfilModel(perfil.getIdPerfil(), perfil.getTipo());
	}
}
