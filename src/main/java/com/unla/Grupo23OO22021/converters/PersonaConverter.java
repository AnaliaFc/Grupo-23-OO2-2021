package com.unla.Grupo23OO22021.converters;

import org.springframework.stereotype.Component;

import com.unla.Grupo23OO22021.entities.Persona;
import com.unla.Grupo23OO22021.models.PersonaModel;

@Component("personaConverter")
public class PersonaConverter {
	public PersonaModel entityToModel(Persona persona) {
		return new PersonaModel(persona.getIdPersona(), persona.getDni(), persona.getNombre(), persona.getApellido());
	}
	
	public Persona modelToEntity(PersonaModel personaModel) {
		return new Persona(personaModel.getIdPersona(), personaModel.getNombre(), personaModel.getApellido(), personaModel.getDni());
	}
}
