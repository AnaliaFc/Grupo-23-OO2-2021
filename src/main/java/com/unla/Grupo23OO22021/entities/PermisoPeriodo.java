package com.unla.Grupo23OO22021.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "permisoperiodo")
@PrimaryKeyJoinColumn(name = "idPermiso")
public class PermisoPeriodo extends Permiso {
	
	@Column(name = "cantDias")
	private int cantDias;
	
	@Column(name = "vacaciones")
	private boolean vacaciones;
	
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "idRodado", nullable = false)
	private Rodado rodado;
	
	public PermisoPeriodo(int idPermiso, Persona persona, LocalDate fecha, Set<Lugar> desdeHasta, int cantDias,
			boolean vacaciones, Rodado rodado) {
		super(idPermiso, persona, fecha, desdeHasta);
		this.cantDias = cantDias;
		this.vacaciones = vacaciones;
		this.rodado = rodado;
	}

	public int getCantDias() {
		return cantDias;
	}

	public void setCantDias(int cantDias) {
		this.cantDias = cantDias;
	}

	public boolean isVacaciones() {
		return vacaciones;
	}

	public void setVacaciones(boolean vacaciones) {
		this.vacaciones = vacaciones;
	}

	public Rodado getRodado() {
		return rodado;
	}

	public void setRodado(Rodado rodado) {
		this.rodado = rodado;
	}

}
