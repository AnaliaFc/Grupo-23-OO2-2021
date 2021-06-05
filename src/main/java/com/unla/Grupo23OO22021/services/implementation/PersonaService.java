package com.unla.Grupo23OO22021.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo23OO22021.converters.PersonaConverter;
import com.unla.Grupo23OO22021.entities.Persona;
import com.unla.Grupo23OO22021.entities.Usuario;
import com.unla.Grupo23OO22021.models.PersonaModel;
import com.unla.Grupo23OO22021.models.UsuarioModel;
import com.unla.Grupo23OO22021.repositories.IPersonaRepository;
import com.unla.Grupo23OO22021.services.IPersonaService;

@Service("personaService")
public class PersonaService implements IPersonaService  {
	
	@Autowired
	@Qualifier("personaConverter")
	private PersonaConverter personaConverter;
	
	@Autowired
	@Qualifier("personaRepository")
	private IPersonaRepository personaRepository;

	@Override
	public List<PersonaModel> traerPersonas() {
		List<PersonaModel> aux = new ArrayList<PersonaModel>();
		for (Persona personas : personaRepository.findAll()) {
			aux.add(personaConverter.entityToModel(personas));
		}
		return aux;
	}
	
	@Override
	public List<PersonaModel> traerPersonasSinUsuario() {
		List<PersonaModel> list = new ArrayList<PersonaModel>();;
		for (Persona persona : personaRepository.findAll()) {
			if(!(persona instanceof Usuario))
				list.add(personaConverter.entityToModel(persona));
		}
		return list;
	}
	
	

	@Override
	public PersonaModel insertOrUpdate(PersonaModel persona) {
		Persona personaEntity = personaConverter.modelToEntity(persona);
		return personaConverter.entityToModel(personaRepository.save(personaEntity));
	}

	@Override
	public PersonaModel traerId(long id) {
		Persona personaExistente = personaRepository.findById(id);
		PersonaModel model = null;
		if(personaExistente!=null)
		{
			model=personaConverter.entityToModel(personaExistente);
		}
		return model;
	}

	@Override
	public PersonaModel traerDni(int dni) {
		Persona personaExistente = personaRepository.findByDni(dni);
		PersonaModel model = null;
		if(personaExistente!=null)
		{
			model=personaConverter.entityToModel(personaExistente);
		}
		return model;
	}

}
