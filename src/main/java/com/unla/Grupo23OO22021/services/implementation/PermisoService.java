 package com.unla.Grupo23OO22021.services.implementation;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unla.Grupo23OO22021.converters.LugarConverter;
import com.unla.Grupo23OO22021.converters.PermisoDiarioConverter;
import com.unla.Grupo23OO22021.converters.PermisoPeriodoConverter;
import com.unla.Grupo23OO22021.entities.Lugar;
import com.unla.Grupo23OO22021.entities.Permiso;
import com.unla.Grupo23OO22021.entities.PermisoDiario;
import com.unla.Grupo23OO22021.entities.PermisoPeriodo;
import com.unla.Grupo23OO22021.models.LugarModel;
import com.unla.Grupo23OO22021.models.PermisoDiarioModel;
import com.unla.Grupo23OO22021.models.PermisoModel;
import com.unla.Grupo23OO22021.models.PermisoPeriodoModel;
import com.unla.Grupo23OO22021.models.PersonaModel;
import com.unla.Grupo23OO22021.models.RodadoModel;
import com.unla.Grupo23OO22021.repositories.IPermisoRepository;
import com.unla.Grupo23OO22021.services.IPermisoService;

@Service("permisoService")
public class PermisoService implements IPermisoService{
	
	@Autowired
	@Qualifier("permisoDiarioConverter")
	private PermisoDiarioConverter permisoDiarioConverter;
	
	@Autowired
	@Qualifier("permisoPeriodoConverter")
	private PermisoPeriodoConverter permisoPeriodoConverter;
	
	@Autowired
	private LugarConverter lugarConverter;
	
	@Autowired
	@Qualifier("permisoRepository")
	private IPermisoRepository permisoRepository;
	
	@Override
	public List<PermisoModel> findAll() {
		List<PermisoModel> permisos = new ArrayList<PermisoModel>();
		for(Permiso permiso : permisoRepository.findAll()) {
			if(permiso instanceof PermisoPeriodo)
				permisos.add(permisoPeriodoConverter.entityToModel((PermisoPeriodo)permiso));
			else if(permiso instanceof PermisoDiario)
				permisos.add(permisoDiarioConverter.entityToModel((PermisoDiario)permiso));
		}
			
		return permisos;
	}

	@Override
	public PermisoModel findById(int id) {
		Permiso permiso = permisoRepository.findById(id);
		if(permiso instanceof PermisoPeriodo)
			return permisoPeriodoConverter.entityToModel((PermisoPeriodo)permiso);
		else if(permiso instanceof PermisoDiario)
			return permisoDiarioConverter.entityToModel((PermisoDiario)permiso);
		
		return null;
	}

	
	@Override
	public PermisoModel insertOrUpdate(PermisoModel permisoModel) {
		if(permisoModel instanceof PermisoDiarioModel)
			return permisoDiarioConverter.entityToModel(
						permisoRepository.save(
									permisoDiarioConverter.modelToEntity((PermisoDiarioModel)permisoModel)
								)
					);
		else if(permisoModel instanceof PermisoPeriodoModel)
			return permisoPeriodoConverter.entityToModel(
						permisoRepository.save(
									permisoPeriodoConverter.modelToEntity((PermisoPeriodoModel)permisoModel)
								)
					);
		return null;
	}

	@Override
	public boolean remove(int id) {
		try {
			permisoRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<PermisoDiarioModel> findByFechaBetween(LocalDate inicio, LocalDate fin) {
		List<PermisoDiarioModel> permisos = new ArrayList<PermisoDiarioModel>();
		for(PermisoDiario permisoDiario : permisoRepository.findByFechaBetween(inicio, fin))
			permisos.add(permisoDiarioConverter.entityToModel(permisoDiario));
		return permisos;
	}

	@Override
	public List<PermisoPeriodoModel> findByFecha(LocalDate inicio, LocalDate fin) {
		List<PermisoPeriodoModel> permisos = new ArrayList<PermisoPeriodoModel>();
		for(PermisoPeriodo permisoPeriodo : permisoRepository.findByFecha(inicio, fin))
			permisos.add(permisoPeriodoConverter.entityToModel(permisoPeriodo));
		return permisos;
	}
	
	
	public List<PermisoPeriodoModel> findByFechaAndLugar(LocalDate inicio, LocalDate fin, LugarModel lugar){
		List<PermisoPeriodoModel> permisoPeriodoModels = findByFecha(inicio, fin);
		List<PermisoPeriodoModel> list = new ArrayList<PermisoPeriodoModel>();
		
		for(PermisoPeriodoModel periodoModel : permisoPeriodoModels) {
			if(periodoModel.getDesdeHasta().get(0).equals(lugar) || periodoModel.getDesdeHasta().get(1).equals(lugar))
				list.add(periodoModel);
		}
		
		return list;
	}
	
	public List<PermisoDiarioModel> findByFechaBetweenAndLugar(LocalDate inicio, LocalDate fin, LugarModel lugar){
		List<PermisoDiarioModel> permisoDiarioModels = findByFechaBetween(inicio, fin);
		List<PermisoDiarioModel> list = new ArrayList<PermisoDiarioModel>();
		
		for(PermisoDiarioModel permisoDiarioModel : permisoDiarioModels) {
			System.out.println(permisoDiarioModel.getDesdeHasta());
			System.out.println(lugar);
			
			LugarModel desde = permisoDiarioModel.getDesdeHasta().get(0);
			LugarModel hasta = permisoDiarioModel.getDesdeHasta().get(1);
			
			if(desde.equals(lugar))
				list.add(permisoDiarioModel);
			else if(hasta.equals(lugar))
				list.add(permisoDiarioModel);
			
		}
		System.out.println(list);
		return list;
	}
	
	public List<PermisoPeriodoModel> findByDominio(RodadoModel rodadoModel) {
		List<PermisoPeriodoModel> permisos = new ArrayList<PermisoPeriodoModel>();
		for(PermisoPeriodo permisoPeriodo : permisoRepository.findByDominio(rodadoModel.getDominio()))
			permisos.add(permisoPeriodoConverter.entityToModel(permisoPeriodo));
		return permisos;
	}
	
	@Override
	public List<PermisoPeriodoModel> findByPersonaPeriodo(PersonaModel persona){
		List<PermisoPeriodoModel> aux = new ArrayList<PermisoPeriodoModel>();
		for(PermisoPeriodo permiso : permisoRepository.findByPersonaPeriodo(persona.getDni())) {
				aux.add(permisoPeriodoConverter.entityToModel(permiso));
		}
		return aux;
	}
	
	@Override
	public List<PermisoDiarioModel> findByPersonaDiario(PersonaModel persona){
		List<PermisoDiarioModel> aux = new ArrayList<PermisoDiarioModel>();
		for(PermisoDiario permisodia : permisoRepository.findByPersonaDiario(persona.getDni())) {
				aux.add(permisoDiarioConverter.entityToModel(permisodia));
		}
		return aux;
	}
	
	@Override
	public String generarUrlQR(int idPermiso)
	{
		String url="";
		
		PermisoModel permiso= findById(idPermiso); 
    	
		if(permiso instanceof PermisoPeriodoModel)
	    	{
				PermisoPeriodoModel pp = (PermisoPeriodoModel) permiso;
	    		url = pp.generarUrl();
	    	}else if(permiso instanceof PermisoDiarioModel)
	    	{
	    		PermisoDiarioModel pd = (PermisoDiarioModel) permiso;
	    		url = pd.generarUrl();
	    	}
		return url;
	}
	
}
