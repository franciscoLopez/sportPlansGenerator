package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.dto.DTOSportPlan;
import com.project.model.Customer;
import com.project.model.CustomerLevel;
import com.project.model.DayOfWeek;
import com.project.model.DurationLimit;
import com.project.model.Objetive;
import com.project.model.PhysicalQuality;
import com.project.model.SportPlan;
import com.project.util.Vista;

@Controller
public class SportPlanController extends GenericController {

	private static final String SPORT_PLAN = "sportPlan";
	private static final String ERROR = "error";
	private static final String ROUTINE_TYPES = "routineTypes";
	private static final String PASO_1_GUARDADO_CORRECTAMENTE = "Paso 1 guardado correctamente";
	private static final String ROUTINE_ORDERS = "routineOrders";
	private static final String ROUTINE_TYPE = "routineType";
	private static final String SESSIONS_PER_WEEK = "sessionsPerWeek";
	private static final String RECUPERATION_TIME = "recuperationTime";
	private static final String JSON_VIEW = "jsonView";
	private static final String OBJETIVE2 = "objetive";
	private static final String LEVEL2 = "level";
	private static final String MANUAL = "manual";
	private static final String DTO_SPORT_PLAN = "dtoSportPlan";
	private static final String SPORT_PLAN_ID = "sportPlanId";
	private static final String CUSTOMER2 = "customer";
	private static final String PERFIL_MODIFICADO_CORRECTAMENTE = "Perfil modificado correctamente";
	private static final String CUSTOMER_ID = "customerId";
	private static final String MENSAJE_OK = "mensaje_ok";



	@RequestMapping(value = "/menuGenerarPlan.do", method = RequestMethod.GET)
	public ModelAndView menuGenerarGET(ModelMap model,HttpSession session,
										@RequestParam(CUSTOMER_ID) Long customerId){
		if(model.containsAttribute(MENSAJE_OK)){
			model.addAttribute(MENSAJE_OK,PERFIL_MODIFICADO_CORRECTAMENTE);
		}
		Customer customer = this.daoCustomer.getCustomer(customerId);
		model.addAttribute(CUSTOMER2,customer);
		model.addAttribute(CUSTOMER_ID, customer.getId());
		return new ModelAndView(Vista.MENU_GENERATOR,model);
	}
	
	@RequestMapping(value = "/menuGenerarPlanStep1.do", method = RequestMethod.GET)
	public ModelAndView menuGenerarPlanStep1GET(ModelMap model,HttpSession session,
										@RequestParam(CUSTOMER_ID) Long customerId,
										@RequestParam(value =SPORT_PLAN_ID, required = false) Long sportPlanId,
										@ModelAttribute(DTO_SPORT_PLAN) DTOSportPlan dtoSportPlan) {
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		DTOSportPlan dto = new DTOSportPlan();
		dto.setSportTrainerId(this.daoUser.getIdByUsername(user.getUsername()));
		dto.setCustomerId(customerId);
		CustomerLevel cl = this.daoMuscle.getCustomerLevel(customerId);
		if(cl != null && !cl.getLevel().equals("")){
			dto.setLevel(cl.getLevel());
			DurationLimit dl = this.daoMuscle.getDurationLimit(cl.getId());
			model.addAttribute("weeks",dl.getWeeks());
			model.addAttribute("periodsNumber",dl.getPeriods());
			model.addAttribute("offset",dl.getOffset());
			List<Objetive> objetives = this.daoMuscle.getObjetives(cl.getId());
			model.addAttribute("objetives",objetives);
			List<PhysicalQuality> physicalQualities = this.daoMuscle.getPhysicalQualities();
			model.addAttribute("physicalQualities",physicalQualities);
			List<DayOfWeek> daysOfWeek = this.daoMuscle.getDaysOfWeek();
			model.addAttribute("daysOfWeek",daysOfWeek);
		}else{
			//TODO reenviar modifyProfile
		}
		model.addAttribute(MANUAL, new Integer(0));
		model.addAttribute(DTO_SPORT_PLAN,dto);		
		return new ModelAndView(Vista.MENU_GENERATOR_STEP1,model);
	}
	
	@RequestMapping(value = "/menuGenerarPlanStep1Manual.do", method = RequestMethod.GET)
	public ModelAndView menuGenerarPlanStep1ManualGET(ModelMap model,HttpSession session,
										@RequestParam(CUSTOMER_ID) Long customerId,
										@RequestParam(value =SPORT_PLAN_ID, required = false) Long sportPlanId,
										@ModelAttribute(DTO_SPORT_PLAN) DTOSportPlan dtoSportPlan) {
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		DTOSportPlan dto = new DTOSportPlan();
		dto.setSportTrainerId(this.daoUser.getIdByUsername(user.getUsername()));
		dto.setCustomerId(customerId);
		CustomerLevel cl = this.daoMuscle.getCustomerLevel(customerId);
		if(cl != null && !cl.getLevel().equals("")){
			dto.setLevel(cl.getLevel());
			DurationLimit dl = this.daoMuscle.getDurationLimit(cl.getId());
			model.addAttribute("weeks",dl.getWeeks());
			model.addAttribute("periodsNumber",dl.getPeriods());
			model.addAttribute("offset",dl.getOffset());
			List<Objetive> objetives = this.daoMuscle.getObjetives(cl.getId());
			model.addAttribute("objetives",objetives);
			List<PhysicalQuality> physicalQualities = this.daoMuscle.getPhysicalQualities();
			model.addAttribute("physicalQualities",physicalQualities);
			List<DayOfWeek> daysOfWeek = this.daoMuscle.getDaysOfWeek();
			model.addAttribute("daysOfWeek",daysOfWeek);
		}else{
			//TODO reenviar modifyProfile
		}
		model.addAttribute(DTO_SPORT_PLAN,dto);		
		model.addAttribute(MANUAL, new Integer(1));
		return new ModelAndView(Vista.MENU_GENERATOR_STEP1,model);
	}
	
	@RequestMapping(value = "/getSessionsPerWeek.do", method = RequestMethod.GET)
	public String getExercisesFilterGET(ModelMap model, HttpServletRequest request,
			@RequestParam(LEVEL2) String level,@RequestParam(OBJETIVE2) String objetive) {
		model.addAttribute(SESSIONS_PER_WEEK, this.daoSportPlan.getSessionsPerWeek(level,objetive));
		return JSON_VIEW;
	}
	
	@RequestMapping(value = "/getRecuperationTime.do", method = RequestMethod.GET)
	public String getRecuperationTimeGET(ModelMap model, HttpServletRequest request,
			@RequestParam(LEVEL2) String level,@RequestParam(OBJETIVE2) String objetive) {
		model.addAttribute(RECUPERATION_TIME, this.daoSportPlan.getRecuperationTime(level,objetive));
		return JSON_VIEW;
	}
	
	@RequestMapping(value = "/getExercisesPerSession.do", method = RequestMethod.GET)
	public String getExercisesPerSessionGET(ModelMap model, HttpServletRequest request,
			@RequestParam(ROUTINE_TYPE) String routineType,
			@RequestParam(LEVEL2) String level,
			@RequestParam(OBJETIVE2) String objetive) {
		model.addAttribute("exercisesPerSession", this.daoSportPlan.getExercisesPerSession(level, objetive, routineType));
		return JSON_VIEW;
	}
	
	@RequestMapping(value = "/getEquipments.do", method = RequestMethod.GET)
	public String getEquipmentsGET(ModelMap model, HttpServletRequest request,
			@RequestParam(LEVEL2) String level) {
		model.addAttribute("equipments", this.daoSportPlan.getEquipments(level));
		return JSON_VIEW;
	}
	
	@RequestMapping(value = "/generateSportPlanStep1.do", method = RequestMethod.POST)
	public ModelAndView menuGenerarPlanStep1POST(ModelMap model,HttpSession session,
										@ModelAttribute(DTO_SPORT_PLAN) DTOSportPlan dtoSportPlan,
										BindingResult result) {
		ModelAndView res = null;
		if(dtoSportPlan != null){
			validarDTOSportPlan.validate(dtoSportPlan,result);
			if(result.hasErrors()){
				model.addAttribute(ERROR, result);
				res = new ModelAndView("redirect:menuGenerarPlan.do?customerId="+dtoSportPlan.getCustomerId(),model);
			}else{
				Long sportPlanId = this.daoSportPlan.createSportPlanStepOne(dtoSportPlan);
				model.addAttribute(MENSAJE_OK,PASO_1_GUARDADO_CORRECTAMENTE);
				model.addAttribute(ROUTINE_ORDERS, this.daoSportPlan.getRoutineOrders());
				model.addAttribute(ROUTINE_TYPES,this.daoSportPlan.getRoutineTypes());
				model.addAttribute(SPORT_PLAN_ID,sportPlanId);
				model.addAttribute(MANUAL, new Integer(0));
				res = new ModelAndView(Vista.MENU_GENERATOR_STEP2,model);
			}
		}else{
			result.rejectValue(OBJETIVE2,"etiqueta.error.customerLevel");
			model.addAttribute(ERROR,result);
			res = new ModelAndView("redirect:menuGenerarPlan.do?customerId="+dtoSportPlan.getCustomerId(),model);
		}
		return res;
	}
	
	@RequestMapping(value = "/generateSportPlanStep1Manual.do", method = RequestMethod.POST)
	public ModelAndView menuGenerarPlanStep1ManualPOST(ModelMap model,HttpSession session,
										@ModelAttribute(DTO_SPORT_PLAN) DTOSportPlan dtoSportPlan,
										BindingResult result) {
		ModelAndView res = null;
		if(dtoSportPlan != null){
			validarDTOSportPlan.validate(dtoSportPlan,result);
			if(result.hasErrors()){
				model.addAttribute(ERROR, result);
				res = new ModelAndView("redirect:menuGenerarPlan.do?customerId="+dtoSportPlan.getCustomerId(),model);
			}else{
				Long sportPlanId = this.daoSportPlan.createSportPlanStepOne(dtoSportPlan);
				model.addAttribute(MENSAJE_OK,PASO_1_GUARDADO_CORRECTAMENTE);
				model.addAttribute(ROUTINE_ORDERS, this.daoSportPlan.getRoutineOrders());
				model.addAttribute(ROUTINE_TYPES,this.daoSportPlan.getRoutineTypes());
				model.addAttribute(SPORT_PLAN_ID,sportPlanId);
				model.addAttribute(MANUAL, new Integer(1));
				res = new ModelAndView(Vista.MENU_GENERATOR_STEP2,model);
			}
		}else{
			result.rejectValue(OBJETIVE2,"etiqueta.error.customerLevel");
			model.addAttribute(ERROR,result);
			res = new ModelAndView("redirect:menuGenerarPlan.do?customerId="+dtoSportPlan.getCustomerId(),model);
		}
		return res;
	}
	
	@RequestMapping(value = "/generateSportPlanStep2.do", method = RequestMethod.POST)
	public ModelAndView menuGenerarPlanStep2POST(ModelMap model,HttpSession session,
										@ModelAttribute(DTO_SPORT_PLAN) DTOSportPlan dtoSportPlan,
										BindingResult result) {
		ModelAndView res = null;
		if(dtoSportPlan != null){
			if(dtoSportPlan.getRoutineOrder() != null && !dtoSportPlan.getRoutineOrder().isEmpty()){
				if(dtoSportPlan.getRoutineType() != null && !dtoSportPlan.getRoutineType().isEmpty()){
					if(dtoSportPlan.getExercisesPerSession() != null && dtoSportPlan.getExercisesPerSession() > 0){
						if(dtoSportPlan.getSportPlanId() != null){
							model.addAttribute(SPORT_PLAN,this.daoSportPlan.createSportPlanStepTwo(dtoSportPlan));
							model.addAttribute(MENSAJE_OK,"¡Plan deportivo generado con éxito!");
							res = new ModelAndView(Vista.MENU_GENERATOR_STEP3,model);
						}
					}else{
						result.rejectValue("exercisesPerSession","etiqueta.error.routine.exercises.per.session");
						model.addAttribute(ERROR,result);
					}
				}else{
					result.rejectValue(ROUTINE_TYPE,"etiqueta.error.routine.type");
					model.addAttribute(ERROR,result);
				}
			}else{
				result.rejectValue("routineOrder","etiqueta.error.routine.order");
				model.addAttribute(ERROR,result);
			}
			if(model.containsAttribute(ERROR)){
				//ARREGLAR
				res = new ModelAndView("redirect:menuGenerarPlan.do?customerId="+dtoSportPlan.getCustomerId(),model);
			}
		}else{
			result.rejectValue(ROUTINE_TYPE,"etiqueta.error.routine.type");
			model.addAttribute(ERROR,result);
			//ARREGLAR
			res = new ModelAndView("redirect:menuGenerarPlan.do?customerId="+dtoSportPlan.getCustomerId(),model);
		}
		return res;
	}
	
	@RequestMapping(value = "/generateSportPlanStep2Manual.do", method = RequestMethod.POST)
	public ModelAndView menuGenerarPlanStep2ManualPOST(ModelMap model,HttpSession session,
										@ModelAttribute(DTO_SPORT_PLAN) DTOSportPlan dtoSportPlan,
										BindingResult result) {
		ModelAndView res = null;
		if(dtoSportPlan != null){
			if(dtoSportPlan.getRoutineOrder() != null && !dtoSportPlan.getRoutineOrder().isEmpty()){
				if(dtoSportPlan.getRoutineType() != null && !dtoSportPlan.getRoutineType().isEmpty()){
					if(dtoSportPlan.getExercisesPerSession() != null && dtoSportPlan.getExercisesPerSession() > 0){
						if(dtoSportPlan.getSportPlanId() != null){
							SportPlan sp = this.daoSportPlan.createSportPlanStepTwoManually(dtoSportPlan);
							model.addAttribute(SPORT_PLAN,sp);
							model.addAttribute("exercises", this.daoSportPlan.getExercises(sp));
							res = new ModelAndView(Vista.MENU_GENERATOR_SELECT_EXERCISES,model);
						}
					}else{
						result.rejectValue("exercisesPerSession","etiqueta.error.routine.exercises.per.session");
						model.addAttribute(ERROR,result);
					}
				}else{
					result.rejectValue(ROUTINE_TYPE,"etiqueta.error.routine.type");
					model.addAttribute(ERROR,result);
				}
			}else{
				result.rejectValue("routineOrder","etiqueta.error.routine.order");
				model.addAttribute(ERROR,result);
			}
			if(model.containsAttribute(ERROR)){
				//TODO ARREGLAR
				res = new ModelAndView("redirect:menuGenerarPlan.do?customerId="+dtoSportPlan.getCustomerId(),model);
			}
		}else{
			result.rejectValue(ROUTINE_TYPE,"etiqueta.error.routine.type");
			model.addAttribute(ERROR,result);
			//TODO ARREGLAR
			res = new ModelAndView("redirect:menuGenerarPlan.do?customerId="+dtoSportPlan.getCustomerId(),model);
		}
		return res;
	}
	
	@RequestMapping(value = "/generateSportPlanStep3Manual.do", method = RequestMethod.POST)
	public ModelAndView menuGenerarPlanStep3ManualPOST(ModelMap model,HttpSession session,
										@RequestParam(SPORT_PLAN_ID) Long sportPlanId,
										@RequestParam("exercises") String exercises) {
		model.addAttribute(SPORT_PLAN,this.daoSportPlan.createSportPlanStepThreeManually(sportPlanId,exercises));
		model.addAttribute(MENSAJE_OK,"¡Plan deportivo generado con éxito!");		
		return new ModelAndView(Vista.MENU_GENERATOR_STEP3,model);
	}
	
	
	
	@RequestMapping(value = "/showSportPlan.do", method = RequestMethod.GET)
	public ModelAndView showSportPlanGET(ModelMap model, HttpServletRequest request,
			@RequestParam(SPORT_PLAN_ID) Long sportPlanId) {	
		SportPlan sp = this.daoCustomer.getSportPlan(sportPlanId);		
		model.addAttribute(SPORT_PLAN, sp);
		return new ModelAndView(Vista.MENU_GENERATOR_STEP3,model);
	}
	
}
