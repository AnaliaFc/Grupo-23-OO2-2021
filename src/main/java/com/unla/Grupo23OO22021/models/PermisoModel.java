package com.unla.Grupo23OO22021.models;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.unla.Grupo23OO22021.entities.Persona;
import com.unla.Grupo23OO22021.entities.Lugar;

public class PermisoModel {
	
	protected int idPermiso;
	
	protected PersonaModel persona;	
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	protected Date fecha;
	
	protected List<LugarModel> desdeHasta;
	
	private String fechaString;
	
	public PermisoModel() {
		this.desdeHasta=new ArrayList<LugarModel>();
		for(int i=0;i<2;i++) {
			desdeHasta.add(new LugarModel());
		}
	}
	
	public PermisoModel(int idPermiso, PersonaModel persona, Date fecha, List<LugarModel> desdeHasta) {
		super();
		this.idPermiso = idPermiso;
		this.persona = persona;
		this.fecha = fecha;
		this.desdeHasta = desdeHasta;
	}
	

	public int getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}

	public PersonaModel getPersona() {
		return persona;
	}

	public void setPersona(PersonaModel persona) {
		this.persona = persona;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<LugarModel> getDesdeHasta() {
		return desdeHasta;
	}

	public void setDesdeHasta(List<LugarModel> desdeHasta) {
		this.desdeHasta = desdeHasta;
	}

	@Override
	public String toString() {
		return "PermisoModel [idPermiso=" + idPermiso + ", persona=" + persona + ", fecha=" + fecha + ", desdeHasta="
				+ desdeHasta + "]";
	}

	public String getFechaString() {
		return fechaString;
	}

	public void setFechaString(String fechaString) {
		this.fechaString = fechaString;
	}

	
	
	
}

