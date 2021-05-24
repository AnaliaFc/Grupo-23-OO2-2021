package com.unla.Grupo23OO22021.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/")
public class HomeController {
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("home/index");
		return modelAndView;
	}
	
	@GetMapping("/login")
	public String login(Model model,
						@RequestParam(name="error",required=false) String error,
						@RequestParam(name="logout", required=false) String logout) {
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return "home/login";
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		return "home/logout";
	}
	
	@GetMapping("/loginsuccess")
	public String loginCheck() {
		return "redirect:/";
	}
}
