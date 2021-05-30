package com.unla.Grupo23OO22021.services;

import java.time.LocalDate;
import java.util.List;

import com.unla.Grupo23OO22021.models.PermisoDiarioModel;
import com.unla.Grupo23OO22021.models.PermisoModel;
import com.unla.Grupo23OO22021.models.PermisoPeriodoModel;

public interface IPermisoService {
	public List<PermisoModel> findAll();
	public PermisoModel findById(int id);
	
	public List<PermisoDiarioModel> findByFechaBetween(LocalDate inicio, LocalDate fin);
	public List<PermisoPeriodoModel> findByFecha(LocalDate inicio, LocalDate fin);
	
	public PermisoModel insertOrUpdate(PermisoModel permisoModel);
	public boolean remove(int id);
}
