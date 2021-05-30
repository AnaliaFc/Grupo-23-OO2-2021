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

	@GetMapping("/periodo/new")
	public ModelAndView formPeriodo() {
		ModelAndView modelAndView = new ModelAndView("permiso/form-periodo");
		modelAndView.addObject("permiso", new PermisoPeriodoModel());
		
		//TODO: Cambiar esto por los servicios
		List<PersonaModel> personas = new ArrayList<PersonaModel>();
		personas.add(new PersonaModel(1, 3, "Pepe", "Jose"));
		personas.add(new PersonaModel(2, 4, "Roberto", "Jose"));
		modelAndView.addObject("personas", personas);

		List<RodadoModel> rodados = rodadoService.traerRodados();
		modelAndView.addObject("rodados", rodados);

		return modelAndView;
	}

	@PostMapping("/periodo/save")
	public RedirectView savePeriodo(@Valid @ModelAttribute("permiso") PermisoPeriodoModel permisoModel,
			BindingResult bindingResult) {
		RedirectView redirectView = new RedirectView("/");

		permisoModel.setFecha(Date.valueOf(permisoModel.getFechaString()));

		// TODO: Traer a la persona correspondiente
		permisoModel.setRodado(rodadoService.traerId(permisoModel.getRodado().getIdRodado()));
		
		revisarLugares(permisoModel);

		System.out.println(permisoModel);
		
		//TODO: Guardarlo cuando se tengan disponible los servicios
		if (bindingResult.hasErrors())
			redirectView.setUrl("/permiso/periodo/new");

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
		
		//TODO: Cambiar esto por los servicios
		List<PersonaModel> personas = new ArrayList<PersonaModel>();
		personas.add(new PersonaModel(1, 3, "Pepe", "Jose"));
		personas.add(new PersonaModel(2, 4, "Roberto", "Jose"));
		modelAndView.addObject("personas", personas);

		return modelAndView;
	}

	@PostMapping("/dia/save")
	public RedirectView saveDia(@Valid @ModelAttribute("permiso") PermisoDiarioModel permisoModel,
			BindingResult bindingResult) {
		RedirectView redirectView = new RedirectView("/");

		permisoModel.setFecha(Date.valueOf(permisoModel.getFechaString()));

		// TODO: Traer a la persona correspondiente
		revisarLugares(permisoModel);

		System.out.println(permisoModel);
		
		//TODO: Guardarlo cuando se tengan disponible los servicios
		if (bindingResult.hasErrors())
			redirectView.setUrl("/permiso/periodo/new");

		return redirectView;
	}
	
	@GetMapping("/listar")
	public ModelAndView traerEntreFechas() {
		ModelAndView modelAndView = new ModelAndView("permiso/listar");
		List<PermisoDiarioModel> permisoDiarioModels = new ArrayList<PermisoDiarioModel>();
		List<PermisoPeriodoModel> permisoPeriodoModels = new ArrayList<PermisoPeriodoModel>();
		
		List<LugarModel> lugarModels = new ArrayList<LugarModel>();
		lugarModels.add(new LugarModel(1, "San juan", "1820"));
		lugarModels.add(new LugarModel(1, "Costa", "2020"));
		
		permisoPeriodoModels.add(new PermisoPeriodoModel(1, new PersonaModel(1, 43200625, "Pepe", "Luis"), Date.valueOf("2021-05-29"), lugarModels, 10, false, 
				new RodadoModel(3, "Citroen", "AAA333")));
		
		permisoDiarioModels.add(new PermisoDiarioModel(1, new PersonaModel(1, 43200626, "Pepe", "Eduardo"), Date.valueOf("2021-05-29"), lugarModels, "Fiesta"));
		
		modelAndView.addObject("permisosDiario", permisoDiarioModels);
		modelAndView.addObject("permisoPeriodo", permisoPeriodoModels);
		return modelAndView;
	}

}
