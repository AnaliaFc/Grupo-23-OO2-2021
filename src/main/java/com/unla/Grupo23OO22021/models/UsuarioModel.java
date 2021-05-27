package com.unla.Grupo23OO22021.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.unla.Grupo23OO22021.enums.TipoDocumento;

public class UsuarioModel extends PersonaModel {

	private String email;

	@Size(min = 3, max = 20)
	private String username;
	private String password;

	private TipoDocumento tipoDocumento;
	private PerfilModel perfil;

	public UsuarioModel() {
	}

	public UsuarioModel(long idPersona, @Min(6) int dni, @Size(min = 3, max = 20) String nombre,
			@Size(min = 3, max = 20) String apellido, String email,
			@Size(min = 3, max = 20) String username, String password, TipoDocumento tipoDocumento,
			PerfilModel perfil) {
		super(idPersona, dni, nombre, apellido);
		this.email = email;
		this.username = username;
		this.password = password;
		this.tipoDocumento = tipoDocumento;
		this.perfil = perfil;
	}

	public UsuarioModel(@Min(6) int dni, @Size(min = 3, max = 20) String nombre,
			@Size(min = 3, max = 20) String apellido, String email,
			@Size(min = 3, max = 20) String username, String password, TipoDocumento tipoDocumento,
			PerfilModel perfil) {
		super(dni, nombre, apellido);
		this.email = email;
		this.username = username;
		this.password = password;
		this.tipoDocumento = tipoDocumento;
		this.perfil = perfil;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public PerfilModel getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilModel perfil) {
		this.perfil = perfil;
	}

	
}
