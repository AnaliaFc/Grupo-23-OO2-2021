package com.unla.Grupo23OO22021.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "perfil")
public class Perfil {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPerfil;
	
	@Column(name = "tipo")
	private String tipo;
	
	@Column(name = "createat")
	@CreationTimestamp
	private LocalDateTime createAt;
	
	@Column(name = "updateat") 
	@UpdateTimestamp
	private LocalDateTime updateAt;
	
	public Perfil() {}
	
	public Perfil(long idPerfil, String tipo) {
		super();
		this.idPerfil = idPerfil;
		this.tipo = tipo;
	}

	public Perfil(String tipo) {
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

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}
	
	
}
