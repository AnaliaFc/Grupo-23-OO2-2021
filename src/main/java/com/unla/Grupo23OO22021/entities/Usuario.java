package com.unla.Grupo23OO22021.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import com.unla.Grupo23OO22021.enums.TipoDocumento;

@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "usuario")
@PrimaryKeyJoinColumn(name = "idPersona")
public class Usuario extends Persona{
	
	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "username", unique = true, nullable = false, length = 45)
	private String username;
	
	@Column(name = "password", nullable = false, length = 100)
	private String password;
	
	@Column(name = "tipodocumento")
	private TipoDocumento tipoDocumento;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
	@JoinColumn(name = "perfil_idPerfil", nullable = false)
	private Perfil perfil;
	
	public Usuario() {}
	
	public Usuario(long idPersona,String nombre, String apellido, int dni, String email, String username, String password,
			TipoDocumento tipoDocumento, Perfil perfil) {
		super(idPersona,nombre, apellido, dni);
		this.email = email;
		this.username = username;
		this.password = password;
		this.tipoDocumento = tipoDocumento;
		this.perfil = perfil;
	}

	public Usuario(String nombre, String apellido, int dni, String email, String username, String password,
			TipoDocumento tipoDocumento, Perfil perfil) {
		super(nombre, apellido, dni);
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

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	
	
}
