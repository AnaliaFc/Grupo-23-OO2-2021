package com.unla.Grupo23OO22021.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unla.Grupo23OO22021.helpers.ViewRouteHelper;
import com.unla.Grupo23OO22021.models.PerfilModel;
import com.unla.Grupo23OO22021.services.IPerfilService;


@Controller
@RequestMapping("/perfil")
public class PerfilController {

	@Autowired
	private IPerfilService service;
	
	@GetMapping("/")
	public String index(Model model) {
		return ViewRouteHelper.ROUTE_PERFILES;
	}
	
	@GetMapping("/listar")
	public String listar(Model model) {
		List<PerfilModel> perfiles = service.traerPerfiles();
		model.addAttribute("perfiles",perfiles);
		return ViewRouteHelper.PERFILES;
	}
	
	@GetMapping("/editar/{idPerfil}")
	public String editar(@PathVariable long idPerfil,Model model) {
		PerfilModel perfil = service.traerId(idPerfil);
		model.addAttribute("perfil", perfil);
		return ViewRouteHelper.FORM;
	}
	
	@GetMapping("/new")
	public String agregar(Model model) {
		model.addAttribute("perfil", new PerfilModel());
		return ViewRouteHelper.FORM;
	}
	
	@PostMapping("/save")
	public String save(@Valid PerfilModel perfil, Model model, BindingResult result) {
		
		if(result.hasErrors()) {
			return ViewRouteHelper.FORM;
		}
			service.insertOrUpdate(perfil);
			return ViewRouteHelper.ROUTE_PERFILES;
	}
	
	@GetMapping("/eliminar/{idPerfil}")
	public String delete(Model model, @PathVariable long idPerfil) {
		service.remove(idPerfil);
		return ViewRouteHelper.ROUTE_PERFILES;
	}
	
}