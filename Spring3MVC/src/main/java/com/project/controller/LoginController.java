package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.util.Vista;
 
@Controller
public class LoginController extends GenericController {
 
	@RequestMapping(value="/login.do", method = RequestMethod.GET)
	public ModelAndView login(ModelMap model) {
 
		return new ModelAndView(Vista.LOGIN);
	}
	
	@RequestMapping(value="/loginfailed.do", method = RequestMethod.GET)
	public ModelAndView loginerror(ModelMap model) {
 
		model.addAttribute("error", "true");
		return new ModelAndView(Vista.LOGIN);
	}
	
	@RequestMapping(value="/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(ModelMap model) {
 
		return new ModelAndView(Vista.LOGIN);
	}
	
}