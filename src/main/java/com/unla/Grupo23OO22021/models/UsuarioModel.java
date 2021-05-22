package com.unla.Grupo23OO22021.models;

import com.unla.Grupo23OO22021.entities.Perfil;
import com.unla.Grupo23OO22021.enums.TipoDocumento;

public class UsuarioModel {

	private long idUsuario;
	
	private int dni;
	private String nombre;
	private String apellido;
	private String email;
	private String username;
	private String password;
	
	private TipoDocumento tipoDocumento;
	private PerfilModel perfil;
	
	public UsuarioModel(long idUsuario, int dni, String nombre, String apellido, String email, String username,
			String password, TipoDocumento tipoDocumento, PerfilModel perfil) {
		super();
		this.idUsuario = idUsuario;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.username = username;
		this.password = password;
		this.tipoDocumento = tipoDocumento;
		this.perfil = perfil;
	}
	
	public UsuarioModel(int dni, String nombre, String apellido, String email, String username, String password,
			TipoDocumento tipoDocumento, PerfilModel perfil) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.username = username;
		this.password = password;
		this.tipoDocumento = tipoDocumento;
		this.perfil = perfil;
	}

	public UsuarioModel() {}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
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
