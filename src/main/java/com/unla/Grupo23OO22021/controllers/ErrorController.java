package com.unla.Grupo23OO22021.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unla.Grupo23OO22021.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/error")
public class ErrorController {
	@GetMapping("/403")
	public String error403() {
		return ViewRouteHelper.ERROR_403;
	}
}
