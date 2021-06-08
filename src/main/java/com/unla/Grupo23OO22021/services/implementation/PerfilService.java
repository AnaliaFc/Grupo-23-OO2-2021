package com.unla.Grupo23OO22021.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo23OO22021.converters.PerfilConverter;
import com.unla.Grupo23OO22021.entities.Perfil;
import com.unla.Grupo23OO22021.entities.Usuario;
import com.unla.Grupo23OO22021.models.PerfilModel;
import com.unla.Grupo23OO22021.models.UsuarioModel;
import com.unla.Grupo23OO22021.repositories.IPerfilRepository;
import com.unla.Grupo23OO22021.services.IPerfilService;

@Service("perfilService")
public class PerfilService implements IPerfilService{
	
	@Autowired
	@Qualifier("perfilRepository")
	private IPerfilRepository perfilRepository;
	
	@Autowired
	@Qualifier("perfilConverter")
	private PerfilConverter perfilConverter;
	
		@Override
		public List<PerfilModel> traerPerfiles() {
			List<PerfilModel> models = new ArrayList<PerfilModel>();
			for (Perfil perfil : perfilRepository.findAll()) {
				models.add(perfilConverter.entityToModel(perfil));
			}
			return models;
		}
		
		@Override
		public PerfilModel traerId(long id) {
			Perfil perfil = perfilRepository.findById(id);
			PerfilModel model=null;
			if (perfil!=null)
			{
				model=perfilConverter.entityToModel(perfil);
			}
			return model; 
		}
		
		@Override
		public PerfilModel traerTipo(String tipo) {
			Perfil perfil = perfilRepository.findByTipo(tipo);
			PerfilModel model=null;
			if (perfil!=null)
			{
				model=perfilConverter.entityToModel(perfil);
			}
			return model; 
		}

		@Override
		public PerfilModel insertOrUpdate(PerfilModel perfilModel) {
			Perfil perfil = perfilRepository.save(perfilConverter.modelToEntity(perfilModel));
			return perfilConverter.entityToModel(perfil);
		}

		@Override
		public boolean remove(long id) {
			try {
				perfilRepository.deleteById(id);
				return true;
			}catch (Exception e) {
				return false;
			}
		}
	
}
