package com.bolsadeideas.springboot.app.controllers;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bolsadeideas.springboot.app.models.entity.Factura;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/dashboard")
@SessionAttributes("dashboard")
public class DashboardController {
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/panel")
	public String crearsc(Model model, Locale locale) {
		
		model.addAttribute("titulo", messageSource.getMessage("text.dashboard.titulo", null, locale));
		return "dashboard/panel";
	}

}
