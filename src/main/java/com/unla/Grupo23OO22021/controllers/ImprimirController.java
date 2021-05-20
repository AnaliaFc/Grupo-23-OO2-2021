package com.unla.Grupo23OO22021.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.Grupo23OO22021.entities.Perfil;

@Controller
@RequestMapping("/imprimir")
public class ImprimirController {
	@GetMapping("/perfiles")
	public ModelAndView imprimirPerfiles() {
		ModelAndView modelAndView = new ModelAndView("prueba/imprimir-perfil");
		
		List<Perfil> perfils = new ArrayList<Perfil>();
		perfils.add(new Perfil(1, "ADMIN"));
		perfils.add(new Perfil(2, "AUDITOR"));
		
		modelAndView.addObject("perfiles", perfils);
		
		return modelAndView;
	}
}
