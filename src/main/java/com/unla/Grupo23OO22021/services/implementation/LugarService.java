package com.unla.Grupo23OO22021.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.unla.Grupo23OO22021.converters.LugarConverter;
import com.unla.Grupo23OO22021.entities.Lugar;
import com.unla.Grupo23OO22021.models.LugarModel;
import com.unla.Grupo23OO22021.repositories.ILugarRepository;
import com.unla.Grupo23OO22021.services.ILugarService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("lugarService")
public class LugarService implements ILugarService {
	@Autowired
	private LugarConverter lugarConverter;
	
	@Autowired
	private ILugarRepository lugarRepository;

	private List<Lugar> lugares = new ArrayList<Lugar>();
	
	@Override
	public List<LugarModel> findAll() {
		List<LugarModel> lugares = new ArrayList<LugarModel>();
		for(Lugar lugar : lugarRepository.findAll())
			lugares.add(lugarConverter.entityToModel(lugar));
		return lugares;
	}

	@Override
	public LugarModel findByIdLugar(long id) {
		return lugarConverter.entityToModel(lugarRepository.findByIdLugar(id));
	}
	
	
	@Override
	public LugarModel findByLugarAndCodigoPostal(String lugar, String codigoPostal) {
		
		Lugar entity=lugarRepository.findByLugarAndCodigoPostal(lugar, codigoPostal);
		return entity==null ? null : lugarConverter.entityToModel(entity);
	}

	@Override
	public LugarModel inserterOrUpdate(LugarModel lugarModel) {
		return (LugarModel) lugarConverter.entityToModel(lugarRepository.save(lugarConverter.modelToEntity(lugarModel)));
	}
	
	
	public boolean guardarLugarEncontrado(LugarModel lugarModel) {
		boolean existeEnLaLista=false;
		if(lugares.size()<2) {
			for(Lugar lugar : lugares) {
				if(lugar.equals(lugarConverter.modelToEntity(lugarModel))) {
					existeEnLaLista=true;
					break;
				}
			}
			if(!existeEnLaLista) {
				lugares.add(lugarConverter.modelToEntity(lugarModel));
			}
		}
		return existeEnLaLista;
	}
	
	
	public boolean guardarLugar(LugarModel lugarModel) {
		boolean existeEnLaLista=false;
		if(lugares.size()<2) {
			for(Lugar lugar : lugares) {
				if(lugar.equals(lugarConverter.modelToEntity(lugarModel))) {
					existeEnLaLista=true;
					break;
				}
			}
			if(!existeEnLaLista) {
				lugares.add(lugarRepository.save(lugarConverter.modelToEntity(lugarModel)));
			}
		}
		return existeEnLaLista;
	}
	
	
	
	public List<LugarModel> getLugares() {
		List<LugarModel> list = new ArrayList<LugarModel>();
		
		for(Lugar lugar : lugares)
			list.add(lugarConverter.entityToModel(lugar));
		
		return list;
	}

	public void clearLugares() {
		this.lugares = new ArrayList<Lugar>();
	}

	@Override
	public boolean remove(long id) {
		try {
			lugarRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
}
