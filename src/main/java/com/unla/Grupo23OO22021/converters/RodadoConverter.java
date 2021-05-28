package com.unla.Grupo23OO22021.converters;

import org.springframework.stereotype.Component;

import com.unla.Grupo23OO22021.entities.Rodado;
import com.unla.Grupo23OO22021.models.RodadoModel;

@Component("rodadoConverter")
public class RodadoConverter {
	
	public Rodado modelToEntity(RodadoModel rodadoModel) {
		
		return new Rodado(rodadoModel.getIdRodado(), rodadoModel.getDominio(), rodadoModel.getVehiculo());
	}
	
	public RodadoModel entityToModel(Rodado rodado) {
		
		return new RodadoModel(rodado.getIdRodado(),rodado.getDominio(),rodado.getVehiculo());
	}
	
}