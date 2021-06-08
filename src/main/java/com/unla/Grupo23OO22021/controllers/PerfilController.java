package com.unla.Grupo23OO22021.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
	public String listar(Model model, @RequestParam(name = "error", required = false) String error) {
		List<PerfilModel> perfiles = service.traerPerfiles();
		model.addAttribute("perfiles",perfiles);
		model.addAttribute("error", error);
		return ViewRouteHelper.PERFILES;
	}
	
	@GetMapping("/editar/{idPerfil}")
	public String editar(@PathVariable long idPerfil,Model model) {
		PerfilModel perfil = service.traerId(idPerfil);
		model.addAttribute("perfil", perfil);
		return ViewRouteHelper.PERFIL_FORM;
	}
	
	@GetMapping("/new")
	public String agregar(Model model) {
		model.addAttribute("perfil", new PerfilModel());
		return ViewRouteHelper.PERFIL_FORM;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid @ModelAttribute("perfil") PerfilModel perfilModel, BindingResult result, RedirectAttributes redirAttrs) {

		ModelAndView mAV;
		PerfilModel exis = service.traerTipo(perfilModel.getTipo());
		
		if(exis!=null) {
			FieldError error = new FieldError("persona", "dni", "Ya existe una persona con el dni ingresado");
			result.addError(error);
			mAV = new ModelAndView(ViewRouteHelper.PERFIL_FORM);
		} else if (result.hasErrors()) {
			mAV = new ModelAndView(ViewRouteHelper.PERFIL_FORM);
		} else {
			service.insertOrUpdate(perfilModel);
			redirAttrs.addFlashAttribute("success", perfilModel.toString()+" agregado exitosamente.");
	       	mAV = new ModelAndView(ViewRouteHelper.HOME_ROUTE);
		}
		return mAV;
	}
	
	@PostMapping("/eliminar/{idPerfil}")
	public String delete(@PathVariable long idPerfil, Model model) {
		
		if(!service.remove(idPerfil)) {
			return ViewRouteHelper.ROUTE_PERFILES+"?error=Error al intertar borrar el perfil: "+service.traerId(idPerfil).getTipo()+""
					+ ", ya que tiene usuarios asignados.";
		}else {
			return ViewRouteHelper.ROUTE_PERFILES;
		}
	}
	
}