package com.unla.Grupo23OO22021.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.Grupo23OO22021.helpers.ViewRouteHelper;
import com.unla.Grupo23OO22021.models.LugarModel;
import com.unla.Grupo23OO22021.models.PermisoDiarioModel;
import com.unla.Grupo23OO22021.models.PermisoModel;
import com.unla.Grupo23OO22021.models.PermisoPeriodoModel;
import com.unla.Grupo23OO22021.models.PersonaModel;
import com.unla.Grupo23OO22021.models.RodadoModel;
import com.unla.Grupo23OO22021.services.implementation.LugarService;
import com.unla.Grupo23OO22021.services.implementation.PermisoService;
import com.unla.Grupo23OO22021.services.implementation.PersonaService;
import com.unla.Grupo23OO22021.services.implementation.RodadoService;

@Controller
@RequestMapping("/permiso")
public class PermisoController {

	@Autowired
	@Qualifier("permisoService")
	private PermisoService permisoService;
	
	@Autowired
	private RodadoService rodadoService;
	
	@Autowired
	private LugarService lugarService;
	
	@Autowired
	private PersonaService personaService;

	@GetMapping("/periodo/new")
	public ModelAndView formPeriodo() {
		ModelAndView modelAndView = new ModelAndView("permiso/form-periodo");
		modelAndView.addObject("permiso", new PermisoPeriodoModel());
		
		List<PersonaModel> personas = personaService.traerPersonas();
		modelAndView.addObject("personas", personas);

		List<RodadoModel> rodados = rodadoService.traerRodados();
		modelAndView.addObject("rodados", rodados);

		return modelAndView;
	}

	@PostMapping("/periodo/save")
	public RedirectView savePeriodo(@Valid @ModelAttribute("permiso") PermisoPeriodoModel permisoModel,
			BindingResult bindingResult) {
		RedirectView redirectView = new RedirectView("/permiso/listar");

		permisoModel.setFecha(Date.valueOf(permisoModel.getFechaString()));
		
		permisoModel.setPersona(personaService.traerId(permisoModel.getPersona().getIdPersona()));
		
		permisoModel.setRodado(rodadoService.traerId(permisoModel.getRodado().getIdRodado()));
		
		revisarLugares(permisoModel);

		System.out.println(permisoModel);
		
		//TODO: Guardarlo cuando se tengan disponible los servicios
		if (bindingResult.hasErrors())
			redirectView.setUrl("/permiso/periodo/new");
		else {
			permisoService.insertOrUpdate(permisoModel);
		}

		return redirectView;
	}

	private void revisarLugares (PermisoModel permisoModel) {
		LugarModel desde = lugarService.findByLugarAndCodigoPostal(
				permisoModel.getDesdeHasta().get(0).getLugar(), 
				permisoModel.getDesdeHasta().get(0).getCodigoPostal());
		
		if(desde!=null) {
			permisoModel.getDesdeHasta().remove(0);
			permisoModel.getDesdeHasta().add(0, desde);
		}
			
		
		LugarModel hasta = lugarService.findByLugarAndCodigoPostal(
				permisoModel.getDesdeHasta().get(1).getLugar(), 
				permisoModel.getDesdeHasta().get(1).getCodigoPostal());
		
		if(hasta!=null) {
			permisoModel.getDesdeHasta().remove(1);
			permisoModel.getDesdeHasta().add(1, hasta);
		}
	}

	@GetMapping("/dia/new")
	public ModelAndView formDia() {
		ModelAndView modelAndView = new ModelAndView("permiso/form-dia");
		modelAndView.addObject("permiso", new PermisoDiarioModel());
		
		List<PersonaModel> personas = personaService.traerPersonas();
		modelAndView.addObject("personas", personas);

		return modelAndView;
	}

	@PostMapping("/dia/save")
	public RedirectView saveDia(@Valid @ModelAttribute("permiso") PermisoDiarioModel permisoModel,
			BindingResult bindingResult) {
		RedirectView redirectView = new RedirectView("/permiso/listar");

		permisoModel.setFecha(Date.valueOf(permisoModel.getFechaString()));

		permisoModel.setPersona(personaService.traerId(permisoModel.getPersona().getIdPersona()));
		revisarLugares(permisoModel);

		System.out.println(permisoModel);
		
		//TODO: Guardarlo cuando se tengan disponible los servicios
		if (bindingResult.hasErrors())
			redirectView.setUrl("/permiso/periodo/new");
		else {
			permisoService.insertOrUpdate(permisoModel);
		}

		return redirectView;
	}
	
	@GetMapping("/listar")
	public ModelAndView traerEntreFechas() {
		ModelAndView modelAndView = new ModelAndView("permiso/listar");
		List<PermisoDiarioModel> permisoDiarioModels = new ArrayList<PermisoDiarioModel>();
		List<PermisoPeriodoModel> permisoPeriodoModels = new ArrayList<PermisoPeriodoModel>();
		
		for(PermisoModel permisoModel : permisoService.findAll()) {
			if(permisoModel instanceof PermisoPeriodoModel)
				permisoPeriodoModels.add((PermisoPeriodoModel) permisoModel);
			else if(permisoModel instanceof PermisoDiarioModel)
				permisoDiarioModels.add((PermisoDiarioModel) permisoModel);
		}
		
		modelAndView.addObject("permisosDiario", permisoDiarioModels);
		modelAndView.addObject("permisoPeriodo", permisoPeriodoModels);
		return modelAndView;
	}

}
