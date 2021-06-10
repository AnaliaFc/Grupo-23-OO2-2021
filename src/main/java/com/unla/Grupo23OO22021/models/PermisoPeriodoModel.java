package com.unla.Grupo23OO22021.models;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Min;

import com.unla.Grupo23OO22021.entities.Persona;
import com.sun.istack.NotNull;
import com.unla.Grupo23OO22021.entities.Lugar;
import com.unla.Grupo23OO22021.entities.Rodado;

public class PermisoPeriodoModel extends PermisoModel{
	
	@Min(1)
	private int cantDias;
	private boolean vacaciones;
	
	@NotNull
	private RodadoModel rodado;
	
	public PermisoPeriodoModel() {
		super();
	}
	
	public PermisoPeriodoModel(int idPermiso, PersonaModel persona, Date fecha, List<LugarModel> desdeHasta, int cantDias, boolean vacaciones, RodadoModel rodado) {
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

	public RodadoModel getRodado() {
		return rodado;
	}

	public void setRodado(RodadoModel rodado) {
		this.rodado = rodado;
	}

	@Override
	public String toString() {
		return super.toString() + " -> PermisoPeriodoModel [cantDias=" + cantDias + ", vacaciones=" + vacaciones + ", rodado=" + rodado + "]";
	}
	
	@Override
	public String generarUrl()
	{
		LocalDate fin=fecha.toLocalDate();
		fin=fin.plusDays(cantDias);
		String fechaFin=fin.getDayOfMonth()+"-"+fin.getMonthValue()+"-"+fin.getYear();
		return super.generarUrl()+"&fin="+fechaFin
		+"&vehiculo="+rodado.getVehiculo()+"&dominio="+rodado.getDominio()+"&vacaciones="+vacaciones;
	}
	

}