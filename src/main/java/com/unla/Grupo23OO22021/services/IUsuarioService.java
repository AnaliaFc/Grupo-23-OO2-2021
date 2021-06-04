package com.unla.Grupo23OO22021.services;

import java.util.List;

import com.unla.Grupo23OO22021.models.UsuarioModel;



public interface IUsuarioService {
	
public List<UsuarioModel> traerUsuarios();
public UsuarioModel traerDocumento(int dni);

public UsuarioModel traerId(long id);

public UsuarioModel traerUsername(String username);

public UsuarioModel traerEmail(String email);

public UsuarioModel insertOrUpdate(UsuarioModel userModel);
	
public boolean remove(long id);
	
	
}
