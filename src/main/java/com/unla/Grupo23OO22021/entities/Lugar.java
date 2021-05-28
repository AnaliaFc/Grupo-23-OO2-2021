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
@Table(name="lugar")
public class Lugar {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idLugar;
	
	@Column(name = "lugar", nullable = false)
	private String lugar;
	
	@Column(name = "codigoPostal", nullable = false)
	private String codigoPostal;
	
	@Column(name = "createat")
	@CreationTimestamp
	private LocalDateTime createAt;
	
	@Column(name = "updateat") 
	@UpdateTimestamp
	private LocalDateTime updateAt;
	
	public Lugar(){}

	public Lugar(long idLugar, String lugar, String codigoPostal) {
		super();
		this.idLugar = idLugar;
		this.lugar = lugar;
		this.codigoPostal = codigoPostal;
	}

	public Lugar(String lugar, String codigoPostal) {
		this.lugar = lugar;
		this.codigoPostal = codigoPostal;
	}


	public long getIdLugar() {
		return idLugar;
	}


	protected void setIdLugar(long idLugar) {
		this.idLugar = idLugar;
	}


	public String getLugar() {
		return lugar;
	}


	public void setLugar(String lugar) {
		this.lugar = lugar;
	}


	public String getCodigoPostal() {
		return codigoPostal;
	}


	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}


}