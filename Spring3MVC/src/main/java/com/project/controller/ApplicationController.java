package com.project.controller;


import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.Customer;
import com.project.model.SportTrainer;
import com.project.util.Vista;

@Controller
public class ApplicationController extends GenericController {

	@RequestMapping(value="/welcome.do", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		String res = null;
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
		Collection<GrantedAuthority> authorities = user.getAuthorities();
		for(GrantedAuthority g : authorities){
			if(g.getAuthority().equals("ROLE_ADMIN")){
				res = "redirect:/menuAdmin.do";
			}
			else if(g.getAuthority().equals("ROLE_SPORT_CENTRE")){
				res = "redirect:/menuSportCentre.do";
			}
			else if(g.getAuthority().equals("ROLE_SPORT_TRAINER")){
				res = "redirect:/menuSportTrainer.do";
			}
			else if(g.getAuthority().equals("ROLE_CUSTOMER")){
				res = "redirect:/menuCustomer.do";
			}
		}
			
		return res;
	}
	@RequestMapping(value="/menuSportCentre.do", method = RequestMethod.GET)
	public ModelAndView menuSportCentre(ModelMap model,@ModelAttribute("sportTrainer") SportTrainer sportTrainer,@ModelAttribute("customer") Customer customer,BindingResult result) {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long id = this.daoUser.getIdByUsername(user.getUsername());
		model.addAttribute("user",user);
		model.addAttribute("sportCentreName", this.daoSportCentre.getSportCentre(user.getUsername()).getName());
		model.addAttribute("numSportTrainers",this.daoSportCentre.getNumberSportTrainers(id));
		model.addAttribute("numCustomers",this.daoSportCentre.getNumberCustomers(id));
		model.addAttribute("numSportPlans",this.daoSportCentre.getNumberSportPlans(id));
		return new ModelAndView(Vista.MENU_SPORT_CENTRE,model);
	}
	
	@RequestMapping(value= "/menuSportTrainer.do", method = RequestMethod.GET)
	public ModelAndView menuSportTrainer(ModelMap model,@ModelAttribute("customer") Customer customer,BindingResult result) {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("user",user);
		Long id = this.daoUser.getIdByUsername(user.getUsername());
		model.addAttribute("id",id);
		model.addAttribute("nombreSportTrainer", this.daoSportTrainer.getSportTrainer(id).getName() + " " + this.daoSportTrainer.getSportTrainer(id).getSurName());
		model.addAttribute("sportCentre",this.daoSportTrainer.getOwner(id));
		model.addAttribute("numCustomers",this.daoSportTrainer.getNumberCustomers(id));
		model.addAttribute("numSportPlans",this.daoSportTrainer.getNumberSportPlans(id));
		return new ModelAndView(Vista.MENU_SPORT_TRAINER);
	}
	
	@RequestMapping(value= "/menuAdmin.do", method = RequestMethod.GET)
	public ModelAndView menuAdmin(ModelMap model) {
		return new ModelAndView(Vista.MENU_ADMIN,model);
	}
	
	@RequestMapping(value= "/menuCustomer.do", method = RequestMethod.GET)
	public ModelAndView menuCustomer(ModelMap model) {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long id = this.daoUser.getIdByUsername(user.getUsername());
		model.addAttribute("customer",this.daoCustomer.getCustomer(id));
		model.addAttribute("sportCentre",this.daoCustomer.getOwner(id).getName());
		model.addAttribute("customerLevel",this.daoCustomer.getCustomerLevel(id).getLevel());
		model.addAttribute("numPlanes", this.daoCustomer.getNumSportPlansGenerated(id));		
		model.addAttribute("listSportPlans", this.daoSportPlan.getSportPlansByUserId(id));
		return new ModelAndView(Vista.MENU_CUSTOMER,model);
	}
	
	@RequestMapping(value = "/getNotifications.do",method = RequestMethod.GET)
	public String test(ModelMap modelo,HttpServletRequest request){
		return "jsonView";
	}
}
