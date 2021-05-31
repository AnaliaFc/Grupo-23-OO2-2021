package com.unla.Grupo23OO22021.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

	@GetMapping("/periodo/new")
	public ModelAndView formPeriodo() {
		ModelAndView modelAndView = new ModelAndView("permiso/form-periodo");
		modelAndView.addObject("permiso", new PermisoPeriodoModel());

		List<PersonaModel> personas = personaService.traerPersonas();
		modelAndView.addObject("personas", personas);

		List<RodadoModel> rodados = rodadoService.traerRodados();
		modelAndView.addObject("rodados", rodados);

		return modelAndView;
	}

	@PostMapping("/periodo/save")
	public RedirectView savePeriodo(@Valid @ModelAttribute("permiso") PermisoPeriodoModel permisoModel,
			BindingResult bindingResult) {
		RedirectView redirectView = new RedirectView("/permiso/listar");

		permisoModel.setFecha(Date.valueOf(permisoModel.getFechaString()));

		permisoModel.setPersona(personaService.traerId(permisoModel.getPersona().getIdPersona()));

		permisoModel.setRodado(rodadoService.traerId(permisoModel.getRodado().getIdRodado()));

		System.out.println(permisoModel);

		// TODO: Guardarlo cuando se tengan disponible los servicios
		if (bindingResult.hasErrors())
			redirectView.setUrl("/permiso/periodo/new");
		else {
			permisoService.insertOrUpdate(permisoModel);
		}

		return redirectView;
	}

	@GetMapping("/dia/new")
	public ModelAndView formDia() {
		ModelAndView modelAndView = new ModelAndView("permiso/form-dia");
		modelAndView.addObject("permiso", new PermisoDiarioModel());

		List<PersonaModel> personas = personaService.traerPersonas();
		modelAndView.addObject("personas", personas);

		return modelAndView;
	}

	@PostMapping("/dia/save")
	public RedirectView saveDia(@Valid @ModelAttribute("permiso") PermisoDiarioModel permisoModel,
			BindingResult bindingResult) {
		RedirectView redirectView = new RedirectView("/permiso/listar");

		permisoModel.setFecha(Date.valueOf(permisoModel.getFechaString()));

		permisoModel.setPersona(personaService.traerId(permisoModel.getPersona().getIdPersona()));

		System.out.println(permisoModel);
		if (bindingResult.hasErrors())
			redirectView.setUrl("/permiso/periodo/new");
		else {
			permisoService.insertOrUpdate(permisoModel);
		}

		return redirectView;
	}

	@GetMapping("/listar")
	public ModelAndView traer() {
		ModelAndView modelAndView = new ModelAndView("permiso/listar");
		List<PermisoDiarioModel> permisoDiarioModels = new ArrayList<PermisoDiarioModel>();
		List<PermisoPeriodoModel> permisoPeriodoModels = new ArrayList<PermisoPeriodoModel>();
		FilterModel filterModel = new FilterModel();
		List<RodadoModel> listaRodados = rodadoService.traerRodados();
		RodadoModel rodadoModel = new RodadoModel();

		for (PermisoModel permisoModel : permisoService.findAll()) {
			if (permisoModel instanceof PermisoPeriodoModel)
				permisoPeriodoModels.add((PermisoPeriodoModel) permisoModel);
			else if (permisoModel instanceof PermisoDiarioModel)
				permisoDiarioModels.add((PermisoDiarioModel) permisoModel);
		}

		modelAndView.addObject("permisosDiario", permisoDiarioModels);
		modelAndView.addObject("permisoPeriodo", permisoPeriodoModels);
		modelAndView.addObject("filtro", filterModel);
		modelAndView.addObject("listaRodados", listaRodados);// TRAER RODADOS POR PERMISO
		modelAndView.addObject("rodado", rodadoModel);
		return modelAndView;
	}

	@PostMapping("/rodados")
	public ModelAndView traerPorRodado(@ModelAttribute("rodado") RodadoModel rodadoModel) {
		ModelAndView modelAndView = new ModelAndView("permiso/listar");

		modelAndView.addObject("permisosPeriodo", permisoService.findByDominio(rodadoModel));
		modelAndView.addObject("permisosDiario", new ArrayList<PermisoDiarioModel>());
		modelAndView.addObject("filtro", new FilterModel());
		modelAndView.addObject("listaRodados", rodadoService.traerRodados());// TRAER RODADOS POR PERMISO
		modelAndView.addObject("rodado", new RodadoModel());
		return modelAndView;
	}

	@PostMapping("/filtro")
	public ModelAndView traerEntreFechas(@Valid @ModelAttribute("filtro") FilterModel filterModel,
			BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView("permiso/listar");

		if (bindingResult.hasErrors())
			return traer();
		try {
			if (!filterModel.getFechaInicio().isEmpty() && !filterModel.getFechaFin().isEmpty()) {

				if (!filterModel.getLugar().getLugar().isEmpty() && !filterModel.getLugar().getCodigoPostal().isEmpty()) {

					modelAndView.addObject("permisosDiario", permisoService.findByFechaBetweenAndLugar(
							LocalDate.parse(filterModel.getFechaInicio(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
							LocalDate.parse(filterModel.getFechaFin(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
							filterModel.getLugar()));
					modelAndView.addObject("permisoPeriodo", permisoService.findByFechaAndLugar(
							LocalDate.parse(filterModel.getFechaInicio(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
							LocalDate.parse(filterModel.getFechaFin(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
							filterModel.getLugar()));
				} else {
					modelAndView.addObject("permisosDiario", permisoService.findByFechaBetween(
							LocalDate.parse(filterModel.getFechaInicio(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
							LocalDate.parse(filterModel.getFechaFin(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
					modelAndView.addObject("permisoPeriodo", permisoService.findByFecha(
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
		return modelAndView;
	}

}
