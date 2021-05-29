package com.unla.Grupo23OO22021.services;

import java.util.List;

import com.unla.Grupo23OO22021.models.LugarModel;

public interface ILugarService {
	public List<LugarModel> findAll();
	public LugarModel findByIdLugar(long id);
	public LugarModel findByLugarAndCodigoPostal(String lugar, String codigoPostal);
	public LugarModel inserterOrUpdate(LugarModel lugarModel);
	public boolean remove(long id);
}
