package com.unla.Grupo23OO22021.services.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.unla.Grupo23OO22021.converters.UsuarioConverter;
import com.unla.Grupo23OO22021.entities.Perfil;
import com.unla.Grupo23OO22021.entities.Usuario;
import com.unla.Grupo23OO22021.models.UsuarioModel;
import com.unla.Grupo23OO22021.repositories.IUsuarioRepository;



@Service("usuarioService")
public class UsuarioService implements UserDetailsService {

	@Autowired
	@Qualifier("usuarioRepository")
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	@Qualifier("usuarioConverter")
	private UsuarioConverter usuarioConverter;
	
	public List<UsuarioModel> traerUsuarios() {
		List<UsuarioModel> models = new ArrayList<UsuarioModel>();
		for (Usuario user : usuarioRepository.findAll()) {
			models.add(usuarioConverter.entityToModel(user));
		}
		return models;
	}

	
	public UsuarioModel traerId(long id) {
		return usuarioConverter.entityToModel(usuarioRepository.findById(id));
	}

	public UsuarioModel insertOrUpdate(UsuarioModel userModel) {
	
		Usuario existente = usuarioRepository.findById(userModel.getIdUsuario());
		if(existente!=null)
		{
			existente.setApellido(userModel.getApellido());
			existente.setNombre(userModel.getNombre());
			existente.setUsername(userModel.getUsername());
			existente.setEmail(userModel.getEmail());
			usuarioRepository.save(existente);
		}else {
		     existente = usuarioRepository.save(usuarioConverter.modelToEntity(userModel));}
		return usuarioConverter.entityToModel(existente);
	}

	
	public boolean remove(long id) {
		try {
			usuarioRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	
	public UsuarioModel traerDocumento(int dni) {
		return usuarioConverter.entityToModel(usuarioRepository.findByDocumento(dni));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsernameWithPerfil(username);
		return builUser(usuario, buildGrantedAuthorities(usuario.getPerfil()));
	}
	
	private User builUser(Usuario usuario, List<GrantedAuthority> grantedAuthorities) {
		return new User(usuario.getUsername(), usuario.getPassword(), grantedAuthorities);
	}
	
	private List<GrantedAuthority> buildGrantedAuthorities(Perfil perfil){
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(perfil.getTipo()));
		return new ArrayList<GrantedAuthority>(grantedAuthorities);
	}
	
}
