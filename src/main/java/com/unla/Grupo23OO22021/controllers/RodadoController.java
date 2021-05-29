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

import com.unla.Grupo23OO22021.helpers.ViewRouteHelper;
import com.unla.Grupo23OO22021.models.RodadoModel;
import com.unla.Grupo23OO22021.services.implementation.RodadoService;

@Controller
@RequestMapping("/rodado")
public class RodadoController {

	@Autowired
	@Qualifier("rodadoService")
	private RodadoService rodadoService;
	
	@GetMapping("/new")
	public ModelAndView formRodado() {
		ModelAndView modelAndView = new ModelAndView("rodado/form");
		modelAndView.addObject("rodado", new RodadoModel());
		return modelAndView;
	}
	
	@PostMapping("/save")
	public RedirectView save(@Valid @ModelAttribute("rodado") RodadoModel rodadoModel, BindingResult bindingResult)
	{
		RedirectView redirectView = new RedirectView("/");

		RodadoModel rodadoExistente = rodadoService.traerDominio(rodadoModel.getDominio());
		
		if (bindingResult.hasErrors()||rodadoExistente!=null)
			redirectView.setUrl("/rodado/new");
			else {
		    	rodadoService.insertOrUpdate(rodadoModel);
		    }

		return redirectView;
	}


}
