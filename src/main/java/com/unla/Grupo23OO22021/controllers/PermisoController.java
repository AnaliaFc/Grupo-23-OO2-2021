package com.unla.Grupo23OO22021.controllers;

import java.sql.Date;
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

import com.unla.Grupo23OO22021.models.PermisoModel;
import com.unla.Grupo23OO22021.models.PermisoPeriodoModel;
import com.unla.Grupo23OO22021.models.PersonaModel;
import com.unla.Grupo23OO22021.models.RodadoModel;
import com.unla.Grupo23OO22021.services.implementation.PermisoService;

@Controller
@RequestMapping("/permiso")
public class PermisoController {
	
	@Autowired
	@Qualifier("permisoService")
	private PermisoService permisoService;
	
	@GetMapping("/periodo/new")
	public ModelAndView formPeriodo() {
		ModelAndView modelAndView = new ModelAndView("permiso/form-periodo");
		modelAndView.addObject("permiso", new PermisoPeriodoModel());
		
		List<PersonaModel> personas = new ArrayList<PersonaModel>();
		personas.add(new PersonaModel(1,3, "Pepe", "Jose"));
		personas.add(new PersonaModel(2,4, "Roberto", "Jose"));
		modelAndView.addObject("personas", personas);
		
		
		List<RodadoModel> rodados = new ArrayList<RodadoModel>();
		rodados.add(new RodadoModel(1, "AAA222", "Car-1"));
		rodados.add(new RodadoModel(2, "AAA333", "Car-2"));
		modelAndView.addObject("rodados", rodados);
		
		
		return modelAndView;
	}
	
	@PostMapping("/periodo/save")
	public RedirectView save(
			@Valid @ModelAttribute("permiso") PermisoPeriodoModel permisoModel, 
			BindingResult bindingResult) {
		RedirectView redirectView = new RedirectView("/");
		
		
		permisoModel.setFecha(Date.valueOf(permisoModel.getFechaString()));
		
		//TODO: Traer a la persona correspondiente
		//TODO: Traer al rodado correspondiente
		//TODO: Fijarse que exista los lugares y en base a eso guardar o no
		
		System.out.println(permisoModel);
		
		
		
		if(bindingResult.hasErrors())
			redirectView.setUrl("/permiso/periodo/new");
		
		
		return redirectView;
	}
}
