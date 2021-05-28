package com.unla.Grupo23OO22021.converters;

import org.springframework.stereotype.Component;

import com.unla.Grupo23OO22021.entities.Lugar;
import com.unla.Grupo23OO22021.models.LugarModel;

@Component("lugarConverter")
public class LugarConverter {
	
	public Lugar modelToEntity(LugarModel lugarModel) {
		
		return new Lugar(lugarModel.getIdLugar(), lugarModel.getLugar(), lugarModel.getCodigoPostal());
	}
	
	public LugarModel entityToModel(Lugar lugar) {
		
		return new LugarModel(lugar.getIdLugar(),lugar.getLugar(),lugar.getCodigoPostal());
	}

}