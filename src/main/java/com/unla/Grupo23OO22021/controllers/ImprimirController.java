package com.unla.Grupo23OO22021.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.Grupo23OO22021.entities.Perfil;
import com.unla.Grupo23OO22021.enums.TipoDocumento;
import com.unla.Grupo23OO22021.models.PerfilModel;
import com.unla.Grupo23OO22021.models.UsuarioModel;

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
	
	@GetMapping("/usuarios")
	public ModelAndView imprimirUsuarios() {
		ModelAndView modelAndView = new ModelAndView("prueba/imprimir-usuario");
		
		PerfilModel admin = new PerfilModel(1, "ADMIN");
		PerfilModel auditor=new PerfilModel(2, "AUDITOR");
		
		List<UsuarioModel> usuarios = new ArrayList<UsuarioModel>();
		usuarios.add(new UsuarioModel(1, 43200624, "Ezequiel", "de la Fuente", "pepe@pepe", "ezePepe", "22", TipoDocumento.DNI, auditor));
		usuarios.add(new UsuarioModel(2, 43200625, "Pepe", "de la Fuente", "pepe@pepe", "ezePepe3", "22", TipoDocumento.DNI, admin));
		
		modelAndView.addObject("usuarios", usuarios);
		
		return modelAndView;
	}
}
