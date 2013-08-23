package com.project.controller;

import java.util.SortedSet;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.SportCentre;
import com.project.util.Vista;

@Controller
public class SportCentreController extends GenericController {

	private static final String SPORT_CENTRE = "sportCentre";

	@RequestMapping(value = "/formSportCentre.do", method = RequestMethod.GET)
	public ModelAndView newUserGET(ModelMap model,
			@ModelAttribute(SPORT_CENTRE) SportCentre sportCentre,
			BindingResult result) {
		return new ModelAndView(Vista.FORM_SPORT_CENTRE);
	}

	@RequestMapping(value = "/addSportCentre.do", method = RequestMethod.POST)
	public String addSportCentre(ModelMap model,
			@ModelAttribute(SPORT_CENTRE) SportCentre sportCentre,
			BindingResult result) {
		String res = null;
		validarSportCentre.validate(sportCentre, result);
		if (result.hasErrors()) {
			res = "redirect:/formSportCentre.do";
		} else {
			if (sportCentre.getPassword().equals(
					sportCentre.getRepetirPassword())) {
				SortedSet<String> users = this.daoUser.getUsernames();
				if (!users.contains(sportCentre.getUsername())) {
					this.daoSportCentre.addSportCentre(sportCentre);
					res = Vista.LOGIN;
				} else {
					result.rejectValue("username",
							"etiqueta.error.username.repetido");
					res = "redirect:/formSportCentre.do";
				}
			} else {
				result.rejectValue("repetirPassword",
						"etiqueta.error.password.incorrecto");
				res = "redirect:/formSportCentre.do";
			}
		}
		return res;
	}

	@RequestMapping(value = "/modifyCredentialsSportCentre.do", method = RequestMethod.GET)
	public ModelAndView modifyCredentialsSportCentre(ModelMap model,
			@ModelAttribute(SPORT_CENTRE) SportCentre infoSportCentre) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		SportCentre sportCentre = this.daoSportCentre.getSportCentre(user
				.getUsername());
		model.addAttribute(SPORT_CENTRE, sportCentre);
		return new ModelAndView(Vista.FORM_MODIFY_CREDENTIALS_SPORT_CENTRE);
	}

	@RequestMapping(value = "/modifySportCentre.do", method = RequestMethod.POST)
	public String modifySportCentrePOST(ModelMap model,
			@ModelAttribute(SPORT_CENTRE) SportCentre infoSportCentre,
			BindingResult result) {
		String res = null;
		if (infoSportCentre != null) {
			validarInfoSportCentre.validate(infoSportCentre, result);
			if (result.hasErrors()) {
				res = Vista.FORM_MODIFY_CREDENTIALS_SPORT_CENTRE;
			} else {
				this.daoSportCentre.modifySportCentre(infoSportCentre);
				res = "redirect:/menuSportCentre.do";
			}
		} else {
			result.rejectValue("name", "etiqueta.error.username.is.required");
			res = Vista.FORM_MODIFY_CREDENTIALS_SPORT_CENTRE;
		}
		return res;
	}
	
	@RequestMapping(value = "/modifyPassWord.do", method = RequestMethod.GET)
	public ModelAndView modifyPassWordGET(ModelMap model,
			@ModelAttribute("user") com.project.model.User user) {		
		return new ModelAndView(Vista.FORM_MODIFY_PASSWORD);
	}
	
	@RequestMapping(value = "/modifyPassWord.do", method = RequestMethod.POST)
	public ModelAndView modifyPassWordPOST(ModelMap model,
			@ModelAttribute("user") com.project.model.User user,BindingResult result) {		
		ModelAndView m = null;
		if(user != null){
			User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			user.setUsername(usuario.getUsername());
			validarPassword.validate(user,result);
			if(result.hasErrors()){
				model.addAttribute("error",result);
				m = new ModelAndView(Vista.FORM_MODIFY_PASSWORD,model);
			}else{
				this.daoUser.modifyPassWord(user);
				model.addAttribute("mensaje_ok","Contrase&ntilde; modificada correctamente");
				m = new ModelAndView(Vista.LOGIN,model);
			}
		}else{
			result.rejectValue("password","etiqueta.error.password.is.required");
			model.addAttribute("error",result);
			m = new ModelAndView(Vista.FORM_MODIFY_PASSWORD,model);
		}		
		return m;		
	}
}
