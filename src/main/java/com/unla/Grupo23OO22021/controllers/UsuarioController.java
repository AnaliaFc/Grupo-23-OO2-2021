package com.unla.Grupo23OO22021.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.Grupo23OO22021.models.PersonaModel;
import com.unla.Grupo23OO22021.models.UsuarioModel;
import com.unla.Grupo23OO22021.services.IUsuarioService;
import com.unla.Grupo23OO22021.services.IPerfilService;
import com.unla.Grupo23OO22021.services.implementation.PersonaService;
import com.unla.Grupo23OO22021.services.implementation.UsuarioService;
import com.unla.Grupo23OO22021.util.Encriptar;
import com.unla.Grupo23OO22021.helpers.ViewRouteHelper;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;
	
	@Autowired
	@Qualifier("personaService")
	private PersonaService personaService;
	
	@Autowired
	@Qualifier("perfilService")
	private IPerfilService perfilService;
	
	@Autowired
	private Encriptar encriptar;
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USUARIO_INDEX);
		mAV.addObject("usuarios", usuarioService.traerUsuarios());
		return mAV;
	}
	
	@GetMapping("/new")
	public ModelAndView crear() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USUARIO_NEW);
		mAV.addObject("perfiles", perfilService.traerPerfiles());
		mAV.addObject("usuario", new UsuarioModel());
		return mAV;
	}
	
	@PostMapping("/crear")
	public ModelAndView saveUsuario(@Valid @ModelAttribute("usuario") UsuarioModel usuarioModel, BindingResult result,RedirectAttributes redirAttrs) {
		
		ModelAndView mAV=null;
		usuarioModel.setPassword(encriptar.encode(usuarioModel.getPassword()));
		
		UsuarioModel existente = usuarioService.traerDocumento(usuarioModel.getDni());
		UsuarioModel userNameRepetido = usuarioService.traerUsername(usuarioModel.getUsername());
				
		if(existente!=null)
		{
			FieldError error = new FieldError("usuario", "dni", "Ya existe un usuario con el dni ingresado");
			result.addError(error);
			mAV = new ModelAndView(ViewRouteHelper.USUARIO_NEW);
			 mAV.addObject("perfiles", perfilService.traerPerfiles());
			}else if (result.hasErrors()) {			
			 mAV = new ModelAndView(ViewRouteHelper.USUARIO_NEW);
			 mAV.addObject("perfiles", perfilService.traerPerfiles());
		    }else if(userNameRepetido!=null){
		    	 
		    	FieldError error = new FieldError("usuario", "username", "Ya existe un usuario con el username ingresado");
				 result.addError(error);
		    	 mAV = new ModelAndView(ViewRouteHelper.USUARIO_NEW);
				 mAV.addObject("perfiles", perfilService.traerPerfiles());
		    
		    }
		    else {
		    	usuarioService.insertOrUpdate(usuarioModel);
		    	redirAttrs.addFlashAttribute("success", usuarioModel.toString()+" agregado exitosamente.");
		       	mAV = new ModelAndView(ViewRouteHelper.HOME_ROUTE);
		    }
		 return mAV;
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
