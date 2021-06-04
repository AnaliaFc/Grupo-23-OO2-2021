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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.unla.Grupo23OO22021.models.UsuarioModel;
import com.unla.Grupo23OO22021.services.IPerfilService;
import com.unla.Grupo23OO22021.services.implementation.PersonaService;
import com.unla.Grupo23OO22021.services.implementation.UsuarioService;
import com.unla.Grupo23OO22021.util.Encriptar;
import com.unla.Grupo23OO22021.entities.Usuario;
import com.unla.Grupo23OO22021.helpers.ViewRouteHelper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

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
	
	@GetMapping("/neworupdate")
	public ModelAndView getById(@RequestParam(value= "idPersona", required=false)Usuario usuario) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USUARIO_FORM);
		UsuarioModel usuarioModel= null;
		if(usuario ==null)
		{
			usuarioModel= new UsuarioModel();
			 mAV.addObject("add", true);
		}else 
		{
			usuarioModel= usuarioService.traerId(usuario.getIdPersona());	
			 mAV.addObject("add", false);
		}
		mAV.addObject("perfiles", perfilService.traerPerfiles());
		mAV.addObject("usuario", usuarioModel);
		return mAV;
	}
	
	@PostMapping("/newUser")
	public ModelAndView saveUsuario(@Valid @ModelAttribute("usuario") UsuarioModel usuarioModel, BindingResult result,RedirectAttributes redirAttrs) {
		
		ModelAndView mAV=null;
		usuarioModel.setPassword(encriptar.encode(usuarioModel.getPassword()));
		
		UsuarioModel existente = usuarioService.traerDocumento(usuarioModel.getDni());
		UsuarioModel userNameRepetido = usuarioService.traerUsername(usuarioModel.getUsername());
		UsuarioModel emailRepetido=usuarioService.traerEmail(usuarioModel.getEmail());
		boolean addIncorrecto=false;
				
		if(existente!=null)
		{
			FieldError error = new FieldError("usuario", "dni", "Ya existe un usuario con el dni ingresado");
			result.addError(error);
			addIncorrecto=true;
		}
		if (result.hasErrors())	
				addIncorrecto=true;
		if(userNameRepetido!=null){
			 
		    	 FieldError error = new FieldError("usuario", "username", "Ya existe un usuario con el username ingresado");
				 result.addError(error);
				 addIncorrecto=true;
		    }
		if(emailRepetido!=null)
		    {FieldError otroError = new FieldError("usuario", "email", "El email ya tiene una cuenta asociada");
			 result.addError(otroError);
			 addIncorrecto=true;
		    }
		if(!addIncorrecto){
		    	usuarioService.insertOrUpdate(usuarioModel);
		    	redirAttrs.addFlashAttribute("success", usuarioModel.toString()+" agregado exitosamente.");
		       	mAV = new ModelAndView(ViewRouteHelper.USUARIO_ROOT);
		    }else {
		    		mAV = new ModelAndView(ViewRouteHelper.USUARIO_FORM);
		    		mAV.addObject("add", true);
		    		mAV.addObject("perfiles", perfilService.traerPerfiles());}
		 return mAV;
	}
	
	@PostMapping("/editUser")
	public  ModelAndView update(@Valid @ModelAttribute("usuario") UsuarioModel usuarioModel, BindingResult result,RedirectAttributes redirAttrs) {
		
		boolean editIncorrecto=false;
		
		ModelAndView mAV=null;
		
		String usernameActual=usuarioService.traerId(usuarioModel.getIdPersona()).getUsername();
		String emailActual=usuarioService.traerId(usuarioModel.getIdPersona()).getEmail();
		UsuarioModel otroUsuario;
		if (result.hasErrors())			
			editIncorrecto=true;
		
		if (!usernameActual.equals(usuarioModel.getUsername()))//si quiere cambiar el nombre
		{
			otroUsuario=usuarioService.traerUsername(usuarioModel.getUsername());
			if(otroUsuario!=null)
			{if(otroUsuario.getIdPersona()!=usuarioModel.getIdPersona())
			{
				 FieldError error = new FieldError("usuario", "username", "Ya existe un usuario con el username ingresado");
				 result.addError(error);
				 editIncorrecto=true;
			}}
		}
		if(!emailActual.equals(usuarioModel.getEmail()))//si quiere cambiar el email
		{
			otroUsuario=usuarioService.traerEmail(usuarioModel.getEmail());
			if(otroUsuario!=null)
			{if(otroUsuario.getIdPersona()!=usuarioModel.getIdPersona())
			{
				 FieldError otroError = new FieldError("usuario", "email", "El email ya tiene una cuenta asociada");
				 result.addError(otroError);
				 editIncorrecto=true;
			}}
		}
		if(editIncorrecto)
		{
			 mAV = new ModelAndView(ViewRouteHelper.USUARIO_FORM);
			 mAV.addObject("add", false);
			 mAV.addObject("perfiles", perfilService.traerPerfiles());
			
		}else {
			usuarioService.insertOrUpdate(usuarioModel);
			redirAttrs.addFlashAttribute("success", usuarioModel.toString()+" editado exitosamente.");
			mAV =new ModelAndView(ViewRouteHelper.USUARIO_ROOT);
		}
		return mAV;
	}
	

	@PostMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") long id,RedirectAttributes redirAttrs) {
		String userName = usuarioService.traerId(id).getUsername();
		
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    UserDetails userDetail = (UserDetails) auth.getPrincipal();
		    UsuarioModel usuarioLog = usuarioService.traerUsername(userDetail.getUsername());
		
		 if(usuarioLog.equals(usuarioService.traerId(id))) 
			 {
			 redirAttrs.addFlashAttribute("error", "Usted no puede borrar su propia cuenta");
			 }
		 else if(!usuarioService.remove(id)) {
			redirAttrs.addFlashAttribute("error", "El usuario "+userName+" no ha podido ser dado de baja debido a registros activos");
		}else
		{
			redirAttrs.addFlashAttribute("success","El usuario "+userName+ " fue dado de baja exitosamente.");
		}
		return new ModelAndView(ViewRouteHelper.USUARIO_ROOT);
	}

}
