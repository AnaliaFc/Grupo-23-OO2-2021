package com.unla.Grupo23OO22021.entities;

import java.time.LocalDate;
import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "permisodiario")
@PrimaryKeyJoinColumn(name = "idPermiso")
public class PermisoDiario extends Permiso{
	
	@Column(name = "motivo")
	private String motivo;

	public PermisoDiario(int idPermiso, Persona persona, LocalDate fecha, Set<Lugar> desdeHasta, String motivo) {
		super(idPermiso, persona, fecha, desdeHasta);
		this.motivo = motivo;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

}
