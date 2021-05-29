package com.unla.Grupo23OO22021.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo23OO22021.converters.RodadoConverter;
import com.unla.Grupo23OO22021.entities.Rodado;
import com.unla.Grupo23OO22021.models.RodadoModel;
import com.unla.Grupo23OO22021.repositories.IRodadoRepository;
import com.unla.Grupo23OO22021.services.IRodadoService;

@Service("rodadoService")
public class RodadoService implements IRodadoService {
	
	@Autowired
	@Qualifier("rodadoRepository")
	private IRodadoRepository rodadoRepository;
	
	@Autowired
	@Qualifier("rodadoConverter")
	private RodadoConverter rodadoConverter;

	@Override
	public List<RodadoModel> traerRodados() {
		List<RodadoModel> models = new ArrayList<RodadoModel>();
		for (Rodado rod : rodadoRepository.findAll()) {
			models.add(rodadoConverter.entityToModel(rod));
		}
		return models;
	}

	@Override
	public RodadoModel traerDominio(String dominio) {
		return rodadoConverter.entityToModel(rodadoRepository.findByDominio(dominio));
	}

	@Override
	public RodadoModel traerId(long id) {
		return rodadoConverter.entityToModel(rodadoRepository.findById(id));
	}

	@Override
	public RodadoModel insertOrUpdate(RodadoModel rodadoModel) {
		
		Rodado rodado = rodadoConverter.modelToEntity(rodadoModel);
		return rodadoConverter.entityToModel(rodadoRepository.save(rodado));
	}

	@Override
	public boolean remove(long id) {
		try {
			rodadoRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

}
