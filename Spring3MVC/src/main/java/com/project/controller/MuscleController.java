package com.project.controller;


import java.io.File;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.dto.DTOExercise;
import com.project.dto.DTOExerciseModify;
import com.project.dto.DTOMuscle;
import com.project.dto.DTOMuscleAndType;
import com.project.model.Exercise;
import com.project.model.Muscle;
import com.project.util.Util;
import com.project.util.Vista;
import com.project.model.Image;

@Controller
public class MuscleController extends GenericController{

	@RequestMapping(value = "/addMuscles.do", method = RequestMethod.GET)
	public String addMuscles(ModelMap model) {
		this.daoMuscle.addMuscle(Muscle.M_ABDOMINALES);
		this.daoMuscle.addMuscle(Muscle.M_ANTEBRAZOS);
		this.daoMuscle.addMuscle(Muscle.M_BICEPS);
		this.daoMuscle.addMuscle(Muscle.M_ESPALDA);
		this.daoMuscle.addMuscle(Muscle.M_EXPLOSIVOS);
		this.daoMuscle.addMuscle(Muscle.M_DELTOIDES);
		this.daoMuscle.addMuscle(Muscle.M_LUMBARES);
		this.daoMuscle.addMuscle(Muscle.M_PECHO);
		this.daoMuscle.addMuscle(Muscle.M_PIERNAS);
		this.daoMuscle.addMuscle(Muscle.M_TRICEPS);
		return "";
	}
	
	@RequestMapping(value = "/createCSV.do", method = RequestMethod.GET)
	public ModelAndView createCSV(ModelMap model, HttpServletRequest request) {
		List<Exercise> lst = this.daoMuscle.getExercises();
		model.addAttribute("lst",lst);
		return new ModelAndView(Vista.PRUEBA);
	}
	
	
	
	@RequestMapping(value = "/addImages-deprecated.do", method = RequestMethod.GET)
	public String addAdbominales(ModelMap model) {
		File dir = new File("C:\\Users\\Francisco\\Desktop\\PFC\\Spring3MVC\\src\\main\\webapp\\images\\storage\\exercises\\Abdominales");
		File[] imgs = dir.listFiles();
		if(imgs == null){
			 System.out.println("No hay ficheros en el directorio especificado");
		}else{
			for(int i = 0;i<imgs.length;i++){
				File img = imgs[i];
				String name = img.getName().substring(0,img.getName().length() - 4);
				String codified = Util.encriptarPassword(name);
				codified = codified + ".png";
				String path = img.getPath();
				File f2 = new File(path.substring(0, path.length() - name.length()) + codified);
				System.out.println(f2.getName());
			}
		}
		return "";
	}
	
	@RequestMapping(value = "/modifyImagesName.do", method = RequestMethod.GET)
	public String modifyImagesName(ModelMap model) {
		List<Image> lstImages = this.daoMuscle.getAllImages();
		for(Image i : lstImages){
			Calendar hoy = Calendar.getInstance();
			i.setF_modified(hoy);
			String src = i.getSrc();
			src = src.substring(0, src.length() - 3) + "jpg";
			i.setSrc(src);
			this.daoMuscle.updateImage(i);
			System.out.println(src);
		}
		return "";
	}
	
	@RequestMapping(value = "/addImages.do", method = RequestMethod.GET)
	public String updateExercises(ModelMap model) {
		List<Exercise> lstExercises = this.daoMuscle.getExercises();
		for(Exercise e : lstExercises){
			String name = e.getName().substring(0,e.getName().length() - 4);
			name = name.replace("_", " ");
			name = name.toLowerCase();
			String first = name.substring(0,1);
			first = first.toUpperCase();
			name = name.substring(1,name.length());
			name = first + name;
			System.out.println(name);	
			e.setName(name);
			this.daoMuscle.updateExercise(e);
		}
		return "";
	}
	
	@RequestMapping(value = "/createDurationLimits.do", method = RequestMethod.GET)
	public String createDurationLimits(ModelMap model) {
		return "";
	}
	
	@RequestMapping(value = "/createObjetives.do", method = RequestMethod.GET)
	public String createObjetives(ModelMap model) {
		this.daoMuscle.createObjetives();
		return "";
	}
	
	@RequestMapping(value = "/createObjetivesByLevel.do", method = RequestMethod.GET)
	public String createObjetivesByLevel(ModelMap model) {
		this.daoMuscle.createObjetivesByLevel();
		return "";
	}
	
	@RequestMapping(value = "/createPhysicalQualities.do", method = RequestMethod.GET)
	public String createPhysicalQualities(ModelMap model) {
		this.daoMuscle.createPhysicalQualities();
		return "";
	}
	
	@RequestMapping(value = "/getMuscles.do", method = RequestMethod.GET)
	public String getMusclesGET(ModelMap model, HttpServletRequest request) {
		List<DTOMuscle> lstMuscles = this.daoMuscle.getDTOMuscles();
		model.addAttribute("listaMusculos", lstMuscles);
		return "jsonView";
	}	
	@RequestMapping(value = "/getExercisesFilter.do", method = RequestMethod.GET)
	public String getExercisesFilterGET(ModelMap model, HttpServletRequest request,
			@RequestParam("musculos") List<String> musculos) {
		List<DTOExercise> lstExercises = this.daoMuscle.getDTOExercisesFilter(musculos);
		model.addAttribute("listaEjercicios", lstExercises);
		return "jsonView";
	}	
	
	@RequestMapping(value = "/getExercises.do", method = RequestMethod.GET)
	public String getExercisesGET(ModelMap model, HttpServletRequest request) {
		List<DTOExercise> lstExercises = this.daoMuscle.getDTOExercises();
		model.addAttribute("listaEjercicios", lstExercises);
		return "jsonView";
	}
	
	@RequestMapping(value = "/modifyExercise.do", method = RequestMethod.GET)
	public ModelAndView modifyExerciseGET(ModelMap model, HttpServletRequest request,
			@RequestParam("exercise") Long exerciseId,
			@ModelAttribute("dtoExerciseModify") DTOExerciseModify dtoExerciseModify,
			BindingResult result) {
		DTOExerciseModify e = this.daoMuscle.getDTOExerciseModify(exerciseId);
		model.addAttribute("dtoExerciseModify",e);
		List<Muscle> musclesTypes = this.daoMuscle.getMuscles();
		model.addAttribute("muscles",musclesTypes);
		List<DTOMuscleAndType> mAT = this.daoMuscle.getTypeMuscles();
		model.addAttribute("musclesTypes",mAT);
		return new ModelAndView(Vista.FORM_MODIFY_EXERCISE);
	}
	
	@RequestMapping(value = "/modifyExercise.do", method = RequestMethod.POST)
	public ModelAndView modifyExercisePOST(ModelMap model, HttpServletRequest request,
			@ModelAttribute("dtoExerciseModify") DTOExerciseModify dtoExerciseModify,
			BindingResult result) {	
		ModelAndView res = null;
		if(dtoExerciseModify != null){
			validarDTOExercise.validate(dtoExerciseModify,result);
			if(result.hasErrors()){
				model.addAttribute("error", result);
				res = new ModelAndView(Vista.FORM_MODIFY_EXERCISE,model);
			}else{
				this.daoMuscle.modifyExercise(dtoExerciseModify);
				model.addAttribute("mensaje_ok","Ejercicio modificado correctamente");
				res = new ModelAndView(Vista.MENU_ADMIN,model);
			}
		}else{
			result.rejectValue("name", "etiqueta.error.username.is.required");
			model.addAttribute("error",result);
			res = new ModelAndView(Vista.FORM_MODIFY_EXERCISE,model);
		}
		return res;
	}
	
}