package com.unla.Grupo23OO22021.services;

import java.time.LocalDate;
import java.util.List;

import com.unla.Grupo23OO22021.models.PermisoDiarioModel;
import com.unla.Grupo23OO22021.models.PermisoModel;
import com.unla.Grupo23OO22021.models.PermisoPeriodoModel;
import com.unla.Grupo23OO22021.models.PersonaModel;
import com.unla.Grupo23OO22021.models.RodadoModel;

public interface IPermisoService {
	public List<PermisoModel> findAll();
	public PermisoModel findById(int id);
	
	public List<PermisoDiarioModel> findByFechaBetween(LocalDate inicio, LocalDate fin);
	public List<PermisoPeriodoModel> findByFecha(LocalDate inicio, LocalDate fin);
	
	public List<PermisoPeriodoModel> findByDominio(RodadoModel rodadoModel);
	public List<PermisoPeriodoModel> findByPersonaPeriodo(PersonaModel persona);
	public List<PermisoDiarioModel> findByPersonaDiario(PersonaModel persona);
	
	public String generarUrlQR(int id);
	
	public PermisoModel insertOrUpdate(PermisoModel permisoModel);
	public boolean remove(int id);
	
}
