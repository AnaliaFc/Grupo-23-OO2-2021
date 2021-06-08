package com.unla.Grupo23OO22021.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.Grupo23OO22021.helpers.ViewRouteHelper;
import com.unla.Grupo23OO22021.models.PerfilModel;
import com.unla.Grupo23OO22021.models.PersonaModel;
import com.unla.Grupo23OO22021.services.implementation.PersonaService;

@Controller
@RequestMapping("/persona")
public class PersonaController {
	
	@Autowired
	@Qualifier("personaService")
	private PersonaService personaService;
	
	@GetMapping("/new")
	public ModelAndView newPersona() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.PERSONA_FORM);
		modelAndView.addObject("persona", new PersonaModel());
		return modelAndView;
	}
	
	@PostMapping("/save")
	public ModelAndView savePersona(@Valid @ModelAttribute("persona") PersonaModel personaModel, BindingResult bindingResult, RedirectAttributes redirAttrs)
	{
		ModelAndView mAV;
		PersonaModel personaExistente = personaService.traerDni(personaModel.getDni());
		if(personaModel.equals(personaExistente))
		{
			FieldError error = new FieldError("persona", "dni", "Ya existe una persona con el dni ingresado");
			bindingResult.addError(error);
			mAV = new ModelAndView(ViewRouteHelper.PERSONA_FORM);
			
		}else if(bindingResult.hasErrors())
		{
			mAV = new ModelAndView(ViewRouteHelper.PERSONA_FORM);
		} else{
		    	personaService.insertOrUpdate(personaModel);
		    	redirAttrs.addFlashAttribute("success", personaModel.toString()+" agregado exitosamente.");
		       	mAV = new ModelAndView(ViewRouteHelper.HOME_ROUTE);
		    }
		return mAV;
	}

}
