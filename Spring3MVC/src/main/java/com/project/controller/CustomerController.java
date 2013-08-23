package com.project.controller;

import java.util.Collection;
import java.util.List;
import java.util.SortedSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.dto.DTOCustomer;
import com.project.dto.DTOProfile;
import com.project.model.Customer;
import com.project.model.User;
import com.project.util.Vista;

@Controller
public class CustomerController extends GenericController{

	private static final String REDIRECT_MENU_SPORT_CENTRE_DO = "redirect:/menuSportCentre.do";
	private static final String CUSTOMER2 = "customer";

	@RequestMapping(value = "/addCustomer.do", method = RequestMethod.POST)
	public String addSportTrainer(ModelMap model,@ModelAttribute(CUSTOMER2) Customer customer,	BindingResult result, HttpSession session) {
		String res = null;
		if (customer != null) {
			validarCustomer.validate(customer, result);
			if (result.hasErrors()) {
				res = REDIRECT_MENU_SPORT_CENTRE_DO;
			} else {
				if (customer.getPassword().equals(
						customer.getRepetirPassword())) {
					SortedSet<String> users = this.daoUser.getUsernames();
					if (!users.contains(customer.getUsername())) {
						SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
						User usuario = null;
						if (securityContext != null) {
							usuario = this.daoUser.getUserByUserName(securityContext.getAuthentication().getName());
						}						
						Collection<GrantedAuthority> a = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
						GrantedAuthority g = a.iterator().next();
						if(g.getAuthority().equals("ROLE_SPORT_CENTRE")){
							this.daoCustomer.addCustomer(customer,usuario.getId());
							res = REDIRECT_MENU_SPORT_CENTRE_DO;
						}
						else if(g.getAuthority().equals("ROLE_SPORT_TRAINER")){
							this.daoCustomer.addCustomerBySportTrainer(customer,usuario.getId());
							res = "redirect:/menuSportTrainer.do";
						}
					} else {
						result.rejectValue("username",
								"etiqueta.error.username.repetido");
						res = REDIRECT_MENU_SPORT_CENTRE_DO;
					}
				} else {
					result.rejectValue("repetirPassword",
							"etiqueta.error.password.incorrecto");
					res = REDIRECT_MENU_SPORT_CENTRE_DO;
				}
			}
		} else {
			result.rejectValue("username",
					"etiqueta.error.username.is.required");
			res = REDIRECT_MENU_SPORT_CENTRE_DO;
		}
		return res;
	}
	
	@RequestMapping(value = "/getCustomers.do", method = RequestMethod.GET)
	public String getCustomersGET(ModelMap modelo, HttpServletRequest request) {
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		List<DTOCustomer> listC = this.daoSportCentre.getCustomers(this.daoUser.getIdByUsername(user.getUsername()));
		modelo.addAttribute("listaCustomers", listC);
		return "jsonView";
	}
	
	@RequestMapping(value = "/getCustomersFromSportTrainer.do", method = RequestMethod.GET)
	public String getCustomersFromSportTrainerGET(ModelMap modelo, HttpServletRequest request){
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		List<DTOCustomer> listC = this.daoSportTrainer.getCustomers(this.daoUser.getIdByUsername(user.getUsername()));
		modelo.addAttribute("listaCustomers", listC);
		return "jsonView";
	}
	
	@RequestMapping(value = "/getInfoCustomersFromSportTrainer.do", method = RequestMethod.GET)
	public String getInfoCustomersFromSportTrainerGET(ModelMap modelo, HttpServletRequest request){
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		List<DTOProfile> listC = this.daoSportTrainer.getInfoCustomers(this.daoUser.getIdByUsername(user.getUsername()));
		modelo.addAttribute("listaInfoCustomers", listC);
		return "jsonView";
	}
	
	@RequestMapping(value = "/deleteCustomer.do", method = RequestMethod.GET)
	public String deleteCustomerGET(ModelMap model, HttpSession session,
			Long userId) {
		this.daoCustomer.deleteCustomer(userId);
		return "redirect:/menuSportTrainer.do";
	}
	
	@RequestMapping(value = "/modifyInfoCustomer.do", method = RequestMethod.GET)
	public ModelAndView modifyInfoSportTrainerGET(ModelMap model,
			HttpSession session, Long userId,
			@ModelAttribute(CUSTOMER2) Customer infocustomer) {
		Customer customer = this.daoCustomer.getCustomer(userId);
		model.addAttribute(CUSTOMER2, customer);
		return new ModelAndView(Vista.FORM_MODIFY_CREDENTIALS_CUSTOMER);
	}
	
	@RequestMapping(value = "/modifyCustomer.do", method = RequestMethod.POST)
	public String modifySportTrainerPOST(ModelMap model, HttpSession session,
			@ModelAttribute(CUSTOMER2) Customer infoCustomer,
			BindingResult result) {
		String res = null;
		if (infoCustomer != null) {
			validarInfoCustomer.validate(infoCustomer, result);
			if (result.hasErrors()) {
				res = Vista.FORM_MODIFY_CREDENTIALS_CUSTOMER;
			} else {
				this.daoCustomer.modifyCustomer(infoCustomer);
				Collection<GrantedAuthority> a = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
				GrantedAuthority g = a.iterator().next();
				if(g.getAuthority().equals("ROLE_SPORT_CENTRE")){
					res = REDIRECT_MENU_SPORT_CENTRE_DO;
				}
				else if(g.getAuthority().equals("ROLE_SPORT_TRAINER")){
					res = "redirect:/menuSportTrainer.do";
				}
			}
		} else {
			result.rejectValue("name", "etiqueta.error.username.is.required");
			res = Vista.FORM_MODIFY_CREDENTIALS_CUSTOMER;
		}
		return res;
	}
	
	
}
