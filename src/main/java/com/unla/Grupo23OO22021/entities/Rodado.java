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
@Table(name="rodado")
public class Rodado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idRodado;
	
	@Column(name = "dominio", nullable = false)
	private String dominio;
	
	@Column(name = "vehiculo", nullable = false)
	private String vehiculo;
	
	@Column(name = "createat")
	@CreationTimestamp
	private LocalDateTime createAt;
	
	@Column(name = "updateat") 
	@UpdateTimestamp
	private LocalDateTime updateAt;
	
	public Rodado() {}
	
	public Rodado(String dominio, String vehiculo)
	{
		this.dominio=dominio;
		this.vehiculo=vehiculo;
	}
	
	public Rodado(long idRodado, String dominio, String vehiculo) {
		super();
		this.idRodado = idRodado;
		this.dominio = dominio;
		this.vehiculo = vehiculo;
	}

	public long getIdRodado() {
		return idRodado;
	}

	protected void setIdRodado(long idRodado) {
		this.idRodado = idRodado;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
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