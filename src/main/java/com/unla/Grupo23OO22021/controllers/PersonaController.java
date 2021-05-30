package com.unla.Grupo23OO22021.controllers;

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

import com.unla.Grupo23OO22021.models.PersonaModel;
import com.unla.Grupo23OO22021.services.implementation.PersonaService;

@Controller
@RequestMapping("/persona")
public class PersonaController {
	
	@Autowired
	@Qualifier("personaService")
	private PersonaService service;
	
	@GetMapping("/")
	public RedirectView index() {
		return new RedirectView("/persona/new");
	}
	
	@GetMapping("/new")
	public ModelAndView newPersona() {
		ModelAndView modelAndView = new ModelAndView("persona/form");
		modelAndView.addObject("persona", new PersonaModel());
		return modelAndView;
	}
	
	@PostMapping("/save")
	public RedirectView save(@Valid @ModelAttribute("persona") PersonaModel personaModel, BindingResult bindingResult) {
		RedirectView redirectView = new RedirectView("/");
		PersonaModel aux = service.traerDni(personaModel.getDni());
		if (personaModel.equals(aux) || bindingResult.hasErrors()) {
			redirectView.setUrl("/persona/new");
		} else {
			service.insertOrUpdate(personaModel);
		}
		return redirectView;
	}

}
