package com.unla.Grupo23OO22021.services;

import java.util.List;

import com.unla.Grupo23OO22021.models.PersonaModel;

public interface IPersonaService {
	
	public List<PersonaModel> traerPersonas();
	public PersonaModel insertOrUpdate(PersonaModel persona);
	public PersonaModel traerId(long id);
	public PersonaModel traerDni(int dni);

}
