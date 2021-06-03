package com.unla.Grupo23OO22021.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.Grupo23OO22021.entities.Lugar;
import com.unla.Grupo23OO22021.helpers.ViewRouteHelper;
import com.unla.Grupo23OO22021.models.FilterModel;
import com.unla.Grupo23OO22021.models.LugarModel;
import com.unla.Grupo23OO22021.models.PermisoDiarioModel;
import com.unla.Grupo23OO22021.models.PermisoModel;
import com.unla.Grupo23OO22021.models.PermisoPeriodoModel;
import com.unla.Grupo23OO22021.models.PersonaModel;
import com.unla.Grupo23OO22021.models.RodadoModel;
import com.unla.Grupo23OO22021.services.implementation.LugarService;
import com.unla.Grupo23OO22021.services.implementation.PermisoService;
import com.unla.Grupo23OO22021.services.implementation.PersonaService;
import com.unla.Grupo23OO22021.services.implementation.RodadoService;

@Controller
@RequestMapping("/permiso")
public class PermisoController {

	@Autowired
	@Qualifier("permisoService")
	private PermisoService permisoService;

	@Autowired
	private RodadoService rodadoService;

	@Autowired
	private LugarService lugarService;

	@Autowired
	private PersonaService personaService;
	
	@GetMapping("")
	public ModelAndView index() {
		return traer();
	}

	@Transactional
	@GetMapping("/periodo/new")
	public ModelAndView formPeriodo(@RequestParam(name = "error", required = false) String error) {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.PERMISO_FORM_PERIODO);
		modelAndView.addObject("permiso", new PermisoPeriodoModel());

		List<PersonaModel> personas = personaService.traerPersonas();
		modelAndView.addObject("personas", personas);

		List<RodadoModel> rodados = rodadoService.traerRodados();
		modelAndView.addObject("rodados", rodados);
		
		modelAndView.addObject("lugares", lugarService.getLugares());
		modelAndView.addObject("lugar", new LugarModel());
		
		modelAndView.addObject("error", error);

		return modelAndView;
	}

	@PostMapping("/periodo/save")
	public ModelAndView savePeriodo(@Valid @ModelAttribute("permiso") PermisoPeriodoModel permisoModel,
			BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.ROUTE_PERMISOS);

		
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("personas", personaService.traerPersonas());

			List<RodadoModel> rodados = rodadoService.traerRodados();
			modelAndView.addObject("rodados", rodados);
			
			modelAndView.addObject("lugares", lugarService.getLugares());
			modelAndView.addObject("lugar", new LugarModel());
			
			modelAndView.setViewName(ViewRouteHelper.PERMISO_FORM_PERIODO);
			
			return modelAndView;
		}
			
		else {
			
			permisoModel.setFecha(Date.valueOf(permisoModel.getFechaString()));

			permisoModel.setPersona(personaService.traerId(permisoModel.getPersona().getIdPersona()));

			permisoModel.setRodado(rodadoService.traerId(permisoModel.getRodado().getIdRodado()));

			
			permisoModel.setDesdeHasta(lugarService.getLugares());

			
			permisoService.insertOrUpdate(permisoModel);
			lugarService.clearLugares();
			
			return traer(); 
		}
		
		
		
		
	}
	
	@Transactional
	@GetMapping("/dia/new")
	public ModelAndView formDia(@RequestParam(name = "error", required = false) String error) {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.PERMISO_FORM_DIA);
		modelAndView.addObject("permiso", new PermisoDiarioModel());

		List<PersonaModel> personas = personaService.traerPersonas();
		modelAndView.addObject("personas", personas);
		
		modelAndView.addObject("lugares", lugarService.getLugares());
		modelAndView.addObject("lugar", new LugarModel());
		
		modelAndView.addObject("error", error);

		return modelAndView;
	}
	
	@Transactional
	@PostMapping("/dia/save")
	public ModelAndView saveDia(@Valid @ModelAttribute("permiso") PermisoDiarioModel permisoModel,
			BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.ROUTE_PERMISOS);

		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName(ViewRouteHelper.PERMISO_FORM_DIA);
			modelAndView.addObject("personas", personaService.traerPersonas());
			
			modelAndView.addObject("lugares", lugarService.getLugares());
			modelAndView.addObject("lugar", new LugarModel());
			return modelAndView;
		}
		else {
			permisoModel.setFecha(Date.valueOf(permisoModel.getFechaString()));
			permisoModel.setPersona(personaService.traerId(permisoModel.getPersona().getIdPersona()));
			permisoModel.setDesdeHasta(lugarService.getLugares());
			permisoService.insertOrUpdate(permisoModel);
			lugarService.clearLugares();
			return traer();
		}
		
		

		
	}

	@GetMapping("/listar")
	public ModelAndView traer() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.ROUTE_PERMISOS);
		List<PermisoDiarioModel> permisoDiarioModels = new ArrayList<PermisoDiarioModel>();
		List<PermisoPeriodoModel> permisoPeriodoModels = new ArrayList<PermisoPeriodoModel>();
		FilterModel filterModel = new FilterModel();
		RodadoModel rodadoModel = new RodadoModel();
		PersonaModel personaModel = new PersonaModel();

		for (PermisoModel permisoModel : permisoService.findAll()) {
			if (permisoModel instanceof PermisoPeriodoModel)
				permisoPeriodoModels.add((PermisoPeriodoModel) permisoModel);
			else if (permisoModel instanceof PermisoDiarioModel)
				permisoDiarioModels.add((PermisoDiarioModel) permisoModel);
		}

		modelAndView.addObject("permisosDiario", permisoDiarioModels);
		modelAndView.addObject("permisosPeriodo", permisoPeriodoModels);
		modelAndView.addObject("filtro", filterModel);
		modelAndView.addObject("rodado", rodadoModel);
		modelAndView.addObject("persona", personaModel);
		return modelAndView;
	}
	
	@PostMapping("/clear-lugar")
	public String clearLugar(Model model) {
		lugarService.clearLugares();
		return "redirect:/permiso/dia/new";
	}
	
	
	@Transactional
	@PostMapping("/search-lugar-d")
	public ModelAndView searchLugarD(@Valid @ModelAttribute("lugar") LugarModel lugarModel,BindingResult bindingResult) {
		LugarModel lugarModeEncontrado = lugarService.findByLugarAndCodigoPostal(lugarModel.getLugar(), lugarModel.getCodigoPostal());
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.PERMISO_FORM_DIA);
		System.out.println(bindingResult);
		if(!bindingResult.hasErrors()) {
			if(lugarModeEncontrado!=null) {
				lugarService.guardarLugarEncontrado(lugarModeEncontrado);
			}else {
//				error="El lugar que buscaste no existe, intente agregarlo: "+lugarModel.getLugar() + " CP: "+lugarModel.getCodigoPostal() ;
				lugarService.guardarLugar(lugarModel);
			}
			modelAndView.addObject("lugar", new LugarModel());
		}
		
		modelAndView.addObject("lugares", lugarService.getLugares());
		modelAndView.addObject("permiso", new PermisoDiarioModel());
		
		List<PersonaModel> personas = personaService.traerPersonas();
		modelAndView.addObject("personas", personas);
		return modelAndView;
	}
	
	@Transactional
	@PostMapping("/search-lugar-p")
	public ModelAndView searchLugarP(@Valid @ModelAttribute("lugar") LugarModel lugarModel,BindingResult bindingResult) {
		LugarModel lugarModeEncontrado = lugarService.findByLugarAndCodigoPostal(lugarModel.getLugar(), lugarModel.getCodigoPostal());
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.PERMISO_FORM_PERIODO);
		System.out.println(bindingResult);
		if(!bindingResult.hasErrors()) {
			if(lugarModeEncontrado!=null) {
				lugarService.guardarLugarEncontrado(lugarModeEncontrado);
			}else {
//				error="El lugar que buscaste no existe, intente agregarlo: "+lugarModel.getLugar() + " CP: "+lugarModel.getCodigoPostal() ;
				lugarService.guardarLugar(lugarModel);
			}
			modelAndView.addObject("lugar", new LugarModel());
		}
		
		modelAndView.addObject("lugares", lugarService.getLugares());
		modelAndView.addObject("permiso", new PermisoPeriodoModel());
		
		List<PersonaModel> personas = personaService.traerPersonas();
		modelAndView.addObject("personas", personas);
		modelAndView.addObject("rodados", rodadoService.traerRodados());
		return modelAndView;
	}

	@PostMapping("/rodados")
	public ModelAndView traerPorRodado(@ModelAttribute("rodado") RodadoModel rodadoModel,RedirectAttributes rA) {
		
		ModelAndView modelAndView= new ModelAndView(ViewRouteHelper.ROUTE_PERMISOS);
		boolean hayQueRedireccionar=false;
		
		String dominioInput=rodadoModel.getDominio();
		rodadoModel=rodadoService.traerDominio(rodadoModel.getDominio());
		
		if(rodadoModel!=null)
		{
			List<PermisoPeriodoModel> listaPermisos = permisoService.findByDominio(rodadoModel);
			modelAndView.addObject("permisosPeriodo", listaPermisos);		
			if(listaPermisos.size()==0)
			{
				rA.addFlashAttribute("error", "El dominio "+dominioInput+" no tiene permisos asociados");
				hayQueRedireccionar=true;
			}
		}
		else{
			rA.addFlashAttribute("error", "El dominio "+dominioInput+" no esta en la Base de datos");
			hayQueRedireccionar=true;
		}
		
		modelAndView.addObject("permisosDiario", new ArrayList<PermisoDiarioModel>());
		modelAndView.addObject("filtro", new FilterModel());
		modelAndView.addObject("rodado", new RodadoModel());
		modelAndView.addObject("persona", new PersonaModel());
		if(hayQueRedireccionar)
		{
			modelAndView =new ModelAndView("redirect:/"+ViewRouteHelper.ROUTE_PERMISOS);
		}
		return modelAndView;
	}
	
	@PreAuthorize("hasRole('ROLE_AUDITOR') or !isAuthenticated()")
	@PostMapping("/personas")
	public ModelAndView traerPorPersonas(@ModelAttribute("persona") PersonaModel personaModel) {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.ROUTE_PERMISOS);
		personaModel=personaService.traerDni(personaModel.getDni());
		
		if(personaModel!=null)
		{
			modelAndView.addObject("permisosPeriodo", permisoService.findByPersonaPeriodo(personaModel));
			modelAndView.addObject("permisosDiario", permisoService.findByPersonaDiario(personaModel));
			modelAndView.addObject("persona", personaModel);
								
		}else {
			modelAndView.addObject("permisosPeriodo", new ArrayList<PermisoPeriodoModel>());
			modelAndView.addObject("permisosDiario", new ArrayList<PermisoDiarioModel>());
			modelAndView.addObject("persona", new PersonaModel());
		}
		modelAndView.addObject("filtro", new FilterModel());
		modelAndView.addObject("listaPersonas", personaService.traerPersonas());
		modelAndView.addObject("listaRodados", new ArrayList<RodadoModel>());
		modelAndView.addObject("rodado", new RodadoModel());
		return modelAndView;
	}

	@PostMapping("/filtro")
	public ModelAndView traerEntreFechas(@Valid @ModelAttribute("filtro") FilterModel filterModel,
			BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.ROUTE_PERMISOS);

		if (bindingResult.hasErrors())
			return traer();
		try {
			if (!filterModel.getFechaInicio().isEmpty() && !filterModel.getFechaFin().isEmpty()) {

				if (!filterModel.getLugar().getLugar().isEmpty() && !filterModel.getLugar().getCodigoPostal().isEmpty()) {

					modelAndView.addObject("permisosDiario", permisoService.findByFechaBetweenAndLugar(
							LocalDate.parse(filterModel.getFechaInicio(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
							LocalDate.parse(filterModel.getFechaFin(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
							filterModel.getLugar()));
					modelAndView.addObject("permisosPeriodo", permisoService.findByFechaAndLugar(
							LocalDate.parse(filterModel.getFechaInicio(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
							LocalDate.parse(filterModel.getFechaFin(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
							filterModel.getLugar()));
				} else {
					modelAndView.addObject("permisosDiario", permisoService.findByFechaBetween(
							LocalDate.parse(filterModel.getFechaInicio(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
							LocalDate.parse(filterModel.getFechaFin(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
					modelAndView.addObject("permisosPeriodo", permisoService.findByFecha(
							LocalDate.parse(filterModel.getFechaInicio(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
							LocalDate.parse(filterModel.getFechaFin(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
				}

			}
		} catch (Exception e) {
			List<PermisoDiarioModel> permisoDiarioModels = new ArrayList<PermisoDiarioModel>();
			List<PermisoPeriodoModel> permisoPeriodoModels = new ArrayList<PermisoPeriodoModel>();
			for (PermisoModel permisoModel : permisoService.findAll()) {
				if (permisoModel instanceof PermisoPeriodoModel)
					permisoPeriodoModels.add((PermisoPeriodoModel) permisoModel);
				else if (permisoModel instanceof PermisoDiarioModel)
					permisoDiarioModels.add((PermisoDiarioModel) permisoModel);
			}

			modelAndView.addObject("permisosDiario", permisoDiarioModels);
			modelAndView.addObject("permisoPeriodo", permisoPeriodoModels);
		}

		modelAndView.addObject("filtro", filterModel);
		modelAndView.addObject("rodado", new RodadoModel());
		modelAndView.addObject("persona", new PersonaModel());
		return modelAndView;
	}
}
