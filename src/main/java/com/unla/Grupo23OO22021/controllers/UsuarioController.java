package com.unla.Grupo23OO22021.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.Grupo23OO22021.models.UsuarioModel;
import com.unla.Grupo23OO22021.services.IUsuarioService;
import com.unla.Grupo23OO22021.services.implementation.UsuarioService;
import com.unla.Grupo23OO22021.helpers.ViewRouteHelper;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USUARIO_INDEX);
		mAV.addObject("usuarios", usuarioService.traerUsuarios());
		return mAV;
	}
	
	@GetMapping("/new")
	public ModelAndView crear() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USUARIO_NEW);
		mAV.addObject("usuario", new UsuarioModel());
		return mAV;
	}
	
	@PostMapping("/crear")
	public RedirectView crear(@Valid @ModelAttribute("usuario") UsuarioModel UsuarioModel, BindingResult result) {
		
		RedirectView redirect= new RedirectView(ViewRouteHelper.USUARIO_ROOT);
		
		 if (result.hasErrors()) {			
			 redirect= new RedirectView("new");
		    }else {
		    	
		    	usuarioService.insertOrUpdate(UsuarioModel);
		    }
		return redirect;
	}
	
	@GetMapping("/{idUsuario}")
	public ModelAndView get(@PathVariable("idUsuario") long idUsuario) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USUARIO_UPDATE);
		mAV.addObject("usuario", usuarioService.traerId(idUsuario));
		return mAV;
	}
	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("usuario") UsuarioModel usuarioModel) {
		usuarioService.insertOrUpdate(usuarioModel);
		return new RedirectView(ViewRouteHelper.USUARIO_ROOT);
	}
	
	@PostMapping("/delete/{id}")
	public RedirectView delete(@PathVariable("id") long id) {
		usuarioService.remove(id);
		return new RedirectView(ViewRouteHelper.USUARIO_ROOT);
	}

}
