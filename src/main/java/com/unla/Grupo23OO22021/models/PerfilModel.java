package com.unla.Grupo23OO22021.models;

import javax.validation.constraints.NotEmpty;

public class PerfilModel {
	
	private long idPerfil;
	
	@NotEmpty(message="El tipo no puede estar vacio")
	private String tipo;
	
	public PerfilModel(long idPerfil, String tipo) {
		super();
		this.idPerfil = idPerfil;
		this.tipo = tipo;
	}
	
	public PerfilModel() {}
	
	public PerfilModel(String tipo) {
		super();
		this.tipo = tipo;
	}
	public long getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(long idPerfil) {
		this.idPerfil = idPerfil;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return tipo;
	}
	
	
	
}
