package com.project.controller;

import java.util.Collection;
import java.util.List;
import java.util.SortedSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.dto.DTOProfile;
import com.project.dto.DTOSportTrainer;
import com.project.model.Customer;
import com.project.model.SportTrainer;
import com.project.model.User;
import com.project.servicios.SportTrainerManager;
import com.project.util.Vista;

@Controller
public class SportTrainerController extends GenericController {

	private static final String ERROR = "error";
	private static final String REDIRECT_MENU_SPORT_CENTRE_DO = "redirect:/menuSportCentre.do";
	private static final String SPORT_TRAINER = "sportTrainer";
	@Autowired
	protected SportTrainerManager sportTrainerManager;

	@RequestMapping(value = "/addSportTrainer.do", method = RequestMethod.POST)
	public ModelAndView addSportTrainer(ModelMap model,
			@ModelAttribute(SPORT_TRAINER) SportTrainer sportTrainer,
			BindingResult result, HttpSession session) {
		ModelAndView res = null;
		if (sportTrainer != null) {
			validarSportTrainer.validate(sportTrainer, result);
			if (result.hasErrors()) {
				res = new ModelAndView(REDIRECT_MENU_SPORT_CENTRE_DO);
			} else {
				if (sportTrainer.getPassword().equals(
						sportTrainer.getRepetirPassword())) {
					SortedSet<String> users = this.daoUser.getUsernames();
					if (!users.contains(sportTrainer.getUsername())) {
						SecurityContext securityContext = (SecurityContext) session
								.getAttribute("SPRING_SECURITY_CONTEXT");
						User usuario = null;
						if (securityContext != null) {
							usuario = this.daoUser.getUserByUserName(securityContext.getAuthentication().getName());
						}
						this.daoSportTrainer.addSportTrainer(sportTrainer,usuario.getId());
						model.addAttribute("mensaje_ok","Monitor creado correctamente");
						org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
						model.addAttribute("user",user);
						Long id = this.daoUser.getIdByUsername(user.getUsername());
						model.addAttribute("numSportTrainers",this.daoSportCentre.getNumberSportTrainers(id));
						model.addAttribute("numCustomers",this.daoSportCentre.getNumberCustomers(id));
						model.addAttribute("numSportPlans",this.daoSportCentre.getNumberSportPlans(id));
						res = new ModelAndView(Vista.MENU_SPORT_CENTRE,model);
					} else {
						result.rejectValue("username",
								"etiqueta.error.username.repetido");
						res = new ModelAndView(REDIRECT_MENU_SPORT_CENTRE_DO);
					}
				} else {
					result.rejectValue("repetirPassword",
							"etiqueta.error.password.incorrecto");
					res = new ModelAndView(REDIRECT_MENU_SPORT_CENTRE_DO);
				}
			}
		} else {
			result.rejectValue("username",
					"etiqueta.error.username.is.required");
			res = new ModelAndView(REDIRECT_MENU_SPORT_CENTRE_DO);
		}
		return res;
	}

	@RequestMapping(value = "/getSportTrainers.do", method = RequestMethod.GET)
	public String test(ModelMap modelo, HttpServletRequest request) {
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<DTOSportTrainer> listSP = this.daoSportCentre.getSportTrainers(this.daoUser.getIdByUsername(user.getUsername()));
		modelo.addAttribute("listaSportTrainers", listSP);
		return "jsonView";
	}

	@RequestMapping(value = "/deleteSportTrainer.do", method = RequestMethod.GET)
	public String deleteSportTrainerGET(ModelMap model, HttpSession session,
			Long userId) {
		this.daoSportTrainer.deleteSportTrainer(userId);
		return REDIRECT_MENU_SPORT_CENTRE_DO;
	}

	@RequestMapping(value = "/modifyInfoSportTrainer.do", method = RequestMethod.GET)
	public ModelAndView modifyInfoSportTrainerGET(ModelMap model,
			HttpSession session, Long userId,
			@ModelAttribute(SPORT_TRAINER) SportTrainer infoSportTrainer) {
		SportTrainer sportTrainer = this.daoSportTrainer.getSportTrainer(userId);
		model.addAttribute(SPORT_TRAINER, sportTrainer);
		return new ModelAndView(Vista.FORM_MODIFY_CREDENTIALS_SPORT_TRAINER);
	}

	@RequestMapping(value = "/modifySportTrainer.do", method = RequestMethod.POST)
	public String modifySportTrainerPOST(ModelMap model, HttpSession session,
			@ModelAttribute(SPORT_TRAINER) SportTrainer infoSportTrainer,
			BindingResult result) {
		String res = null;
		if (infoSportTrainer != null) {
			validarInfoSportTrainer.validate(infoSportTrainer, result);
			if (result.hasErrors()) {
				model.addAttribute(ERROR,result);
				res = Vista.FORM_MODIFY_CREDENTIALS_SPORT_TRAINER;
			} else {
				this.daoSportTrainer.modifySportTrainer(infoSportTrainer);
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
			model.addAttribute(ERROR,result);
			res = Vista.FORM_MODIFY_CREDENTIALS_SPORT_TRAINER;
		}
		return res;
	}
	
	@RequestMapping(value = "/generarPlan.do", method = RequestMethod.GET)
	public String generarPlanGET(ModelMap model,HttpSession session, @RequestParam("userId")Long userId) {
		String res = null;
		Customer customer = this.daoCustomer.getCustomer(userId);
		model.addAttribute("customer",customer);
		if(customer.getProfile() == null){	
			res = "redirect:modifyProfile.do?customerId="+userId; 
		}else{
				res = "redirect:menuGenerarPlan.do?customerId="+userId;	
		}
		return res;
	}
	
	@RequestMapping(value = "/modifyProfile.do", method = RequestMethod.GET)
	public ModelAndView modifyProfileGET(ModelMap model, HttpSession session, 
										@RequestParam("customerId") Long customerId,
										@ModelAttribute("dtoProfile") DTOProfile dtoProfile,
										BindingResult result) {
		DTOProfile dto = this.daoCustomer.getDTOProfile(customerId);
		if(dto == null){
			dto = new DTOProfile();
			dto.setIdCustomer(customerId);			
		}
		model.addAttribute("dtoProfile",dto);
		return new ModelAndView(Vista.FORM_MODIFY_PROFILE,model);
	}
	
	@RequestMapping(value = "/modifyProfile.do", method = RequestMethod.POST)
	public ModelAndView modifyProfilePOST(ModelMap model, HttpSession session, 
											@ModelAttribute("dtoProfile") DTOProfile dtoprofile, 
											BindingResult result) {
		ModelAndView res = null;
		if(dtoprofile != null){
			validarDTOProfile.validate(dtoprofile,result);
			if(result.hasErrors()){
				model.addAttribute(ERROR,result);
				res = new ModelAndView(Vista.FORM_MODIFY_PROFILE,model);
			}else{
				this.daoCustomer.modifyProfile(dtoprofile);
				model.addAttribute("mensaje_ok","Perfil modificado correctamente");
				res = new ModelAndView("redirect:menuGenerarPlan.do?customerId="+dtoprofile.getIdCustomer(),model); 
			}
		}else{
			result.rejectValue("dateBirth", "etiqueta.error.datebirth");
			model.addAttribute(ERROR,result);
			res = new ModelAndView(Vista.FORM_MODIFY_PROFILE,model);
		}		
		return res;
	}
	
	@RequestMapping(value = "/showSportPlans.do", method = RequestMethod.GET)
	public ModelAndView showSportPlansGET(ModelMap model,
			HttpSession session, @RequestParam("customerId") Long customerId) {
		model.addAttribute("numPlanes", this.daoCustomer.getNumSportPlansGenerated(customerId));
		model.addAttribute("listSportPlans", this.daoSportPlan.getSportPlansByUserId(customerId));
		return new ModelAndView(Vista.SHOW_SPORT_PLANS);
	}
}