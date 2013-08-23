package com.project.dao.impl;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.dao.DAOMuscle;
import com.project.dao.DAOSportTrainer;
import com.project.dto.DTOExercise;
import com.project.dto.DTOExerciseModify;
import com.project.dto.DTOMuscle;
import com.project.dto.DTOMuscleAndType;
import com.project.model.CustomerLevel;
import com.project.model.DayOfWeek;
import com.project.model.DurationLimit;
import com.project.model.Equipment;
import com.project.model.Exercise;
import com.project.model.ExerciseType;
import com.project.model.Image;
import com.project.model.Muscle;
import com.project.model.MuscleType;
import com.project.model.Objetive;
import com.project.model.ObjetiveByLevel;
import com.project.model.PhysicalQuality;
import com.project.model.SportTrainer;

public class DAOMuscleImpl extends DAOComunImpl implements DAOMuscle {

	@Autowired
	protected DAOSportTrainer daoSportTrainer;
	
	public void addMuscle(String muscle) {
		Muscle m = new Muscle();
		m.setMuscle(muscle);
		sessionFactory.getCurrentSession().saveOrUpdate(m);
	}

	public void addExercise(String name, String muscle) {
		Muscle m = this.getMuscle(muscle);
		Exercise e = new Exercise();
		e.setName(name);
		sessionFactory.getCurrentSession().saveOrUpdate(e);
	}

	public Muscle getMuscle(String muscle) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Muscle.class);
		crit.add(Restrictions.eq("muscle",muscle));
		return (Muscle) crit.uniqueResult();
	}

	public List<DTOExercise> getDTOExercises() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Exercise.class);
		List<Exercise> exercises = crit.list();
		List<DTOExercise> lstDTO = new ArrayList<DTOExercise>();
		for(Exercise e : exercises){
			DTOExercise dto = new DTOExercise();
			dto.setId(e.getId());
			dto.setName(e.getName());
			if(e.getPrincipalMuscleType() != null){
				DTOMuscleAndType dtoMAT = new DTOMuscleAndType();
				dtoMAT.setMuscle(e.getPrincipalMuscleType().getMuscle().getMuscle());
				dtoMAT.setType(e.getPrincipalMuscleType().getType());
				dto.setPrincipalMuscle(dtoMAT);
				dto.setSrc(Exercise.RUTA+e.getPrincipalMuscleType().getMuscle().getMuscle()+"/"+e.getImage().getSrc());
			}			
			lstDTO.add(dto);
		}
		return lstDTO;
	}
	
	public List<Exercise> getExercises() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Exercise.class);
		return crit.list();

	}

	public void addImage(String codified,Exercise e) {		
		Image img = new Image();
		Calendar hoy = Calendar.getInstance();
		img.setF_uploaded(hoy);
		img.setF_modified(hoy);
		img.setSrc(codified);
		e.setImage(img);
		sessionFactory.getCurrentSession().saveOrUpdate(e);
		sessionFactory.getCurrentSession().saveOrUpdate(img);				
	}

	public void updateExercise(Exercise e) {
		sessionFactory.getCurrentSession().saveOrUpdate(e);
	}

	public Exercise getExercise(Long exerciseId) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Exercise.class);
		crit.add(Restrictions.idEq(exerciseId));
		return (Exercise) crit.uniqueResult();
	}

	public List<DTOMuscle> getDTOMuscles() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Muscle.class);
		List<Muscle> lstMuscles = crit.list();
		List<DTOMuscle> lstDTO = new ArrayList<DTOMuscle>();
		for(Muscle m : lstMuscles){
			DTOMuscle dto = new DTOMuscle();
			dto.setName(m.getMuscle());
			dto.setId(m.getId());
			lstDTO.add(dto);
		}
		return lstDTO;
	}
	
	private MuscleType getMuscleType(Long id){
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(MuscleType.class);
		crt.add(Restrictions.idEq(id));
		return (MuscleType) crt.uniqueResult();
	}

	public void updateDB() {
		List<Exercise> exercises = this.getExercises();
		MuscleType mt = getMuscleType((long)37);
		for(Exercise e : exercises){
			// 39-52
			if(e.getId() >= 98 && e.getId() <= 103){
				e.setPrincipalMuscleType(mt);
				sessionFactory.getCurrentSession().saveOrUpdate(e);
			}
		}
		
	}

	public List<DTOExercise> getDTOExercisesFilter(List<String> musculos) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Exercise.class);
		int[] borrar = new int[musculos.size()];
		if(musculos.size() == 1){
			if(musculos.get(0).equals("Todos") || musculos.get(0).equals("Ninguno")){
				
			}
		}else{			
			int i = 0;
			for(String s: musculos){
				if(s.equals("Todos") || s.equals("Ninguno")){
					borrar[i] = 1;
					i++;
				}
			}
			for(int j = 0; j < borrar.length; j++){
				if(borrar[j] == 1){
					musculos.remove(j);
				}
			}
			crit.createAlias(Exercise.A_PRINCIPAL_MUSCLE, "ppalMuscle");
			crit.createAlias("ppalMuscle.muscle", "muscle");
			crit.add(Restrictions.in("muscle.muscle", musculos));
		}		
		List<Exercise> exercises = crit.list();
		List<DTOExercise> lstDTO = new ArrayList<DTOExercise>();
		for(Exercise e : exercises){
			DTOExercise dto = new DTOExercise();
			dto.setId(e.getId());
			dto.setName(e.getName());
			if(e.getPrincipalMuscleType() != null){
				DTOMuscleAndType dtoMAT = new DTOMuscleAndType();
				dtoMAT.setMuscle(e.getPrincipalMuscleType().getMuscle().getMuscle());
				dtoMAT.setType(e.getPrincipalMuscleType().getType());
				dto.setPrincipalMuscle(dtoMAT);				
			}
			if(e.getImage() != null && !e.getImage().getSrc().equals("")){
				dto.setSrc(Exercise.RUTA+e.getPrincipalMuscleType().getMuscle().getMuscle()+"/"+e.getImage().getSrc());
			}
			if(e.getSecundaryMuscleType() != null && !e.getSecundaryMuscleType().isEmpty()){
				List<DTOMuscleAndType> sm = new ArrayList<DTOMuscleAndType>();
				for(MuscleType m : e.getSecundaryMuscleType()){
					DTOMuscleAndType dtoMAT = new DTOMuscleAndType();
					dtoMAT.setMuscle(m.getMuscle().getMuscle());
					dtoMAT.setType(m.getType());
					sm.add(dtoMAT);
				}
				dto.setSecundaryMuscle(sm);
			}
			lstDTO.add(dto);
		}
		return lstDTO;
	}

	public List<Muscle> getMuscles() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Muscle.class);
		return  crit.list();
	}

	public DTOExerciseModify getDTOExerciseModify(Long exerciseId) {
		Exercise e = this.getExercise(exerciseId);
		DTOExerciseModify dto = new DTOExerciseModify();
		return dto.getDTO(e);
		
	}

	public void modifyExercise(DTOExerciseModify dtoExerciseModify) {
		Exercise e = this.getExercise(dtoExerciseModify.getId());
		// Name
		if(dtoExerciseModify.getName() != null && !dtoExerciseModify.getName().isEmpty()){
			e.setName(dtoExerciseModify.getName());
		}
		// Comment
		if(dtoExerciseModify.getComment() != null ){
			e.setComment(dtoExerciseModify.getComment());
		}
		// Equipment
		if(dtoExerciseModify.getEquipment() != null && !dtoExerciseModify.getEquipment().equals("-")){
			Equipment equipment = this.getEquipment(dtoExerciseModify.getEquipment());
			e.setEquipment(equipment);
		}
		// Exercise Type
		if(dtoExerciseModify.getExerciseType() != null && !dtoExerciseModify.getExerciseType().equals("-")){
			ExerciseType eType = this.getExerciseType(dtoExerciseModify.getExerciseType());
			e.setExerciseType(eType);
		}
		// Principal Muscle
		if(dtoExerciseModify.getPrincipalMuscle() != null && !dtoExerciseModify.getPrincipalMuscle().getMuscle().isEmpty() && !dtoExerciseModify.getPrincipalMuscle().getType().isEmpty()){
			MuscleType mType = this.getMuscleType(dtoExerciseModify.getPrincipalMuscle().getType(),dtoExerciseModify.getPrincipalMuscle().getMuscle());
			e.setPrincipalMuscleType(mType);
		}
		// Secundary Muscles
		if(dtoExerciseModify.getSecundaryMuscles() != null && !dtoExerciseModify.getSecundaryMuscles().isEmpty()){
			List<DTOMuscleAndType> lst = dtoExerciseModify.getSecundaryMuscles();
			for(DTOMuscleAndType dto : lst){
				if(dto.getMuscle() != null && !dto.getMuscle().isEmpty() && dto.getType() != null && !dto.getType().isEmpty()){
					MuscleType mType = this.getMuscleType(dto.getType(),dto.getMuscle());
					e.getSecundaryMuscleType().add(mType);
				}
			}
		}
		Calendar hoy = Calendar.getInstance();
		e.setF_modified(hoy);
		sessionFactory.getCurrentSession().saveOrUpdate(e);		
	}

	public MuscleType getMuscleType(String type, String muscle) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(MuscleType.class);
		crit.add(Restrictions.eq(MuscleType.A_TYPE, type));
		crit.createAlias(MuscleType.A_MUSCLE, "muscle");
		crit.add(Restrictions.eq("muscle.muscle", muscle));
		
		return  (MuscleType) crit.uniqueResult();
	}

	private ExerciseType getExerciseType(String exerciseType) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(ExerciseType.class);
		crit.add(Restrictions.eq(ExerciseType.A_EXERCISE_TYPE, exerciseType));
		ExerciseType e = new ExerciseType();
		e = (ExerciseType) crit.uniqueResult();
		return e;
	}

	private Equipment getEquipment(String equipment) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Equipment.class);
		crit.add(Restrictions.eq(Equipment.A_EQUIPMENT, equipment));
		Equipment e = new Equipment();
		e = (Equipment) crit.uniqueResult();
		return e;
	}

	public List<DTOMuscleAndType> getTypeMuscles() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(MuscleType.class);
		List<MuscleType> mt = crit.list();
		List<DTOMuscleAndType> res = new ArrayList<DTOMuscleAndType>();
		for(MuscleType m : mt){
			DTOMuscleAndType dto = new DTOMuscleAndType();
			dto.setMuscle(m.getMuscle().getMuscle());
			dto.setType(m.getType());
			dto.setIdMuscle(m.getMuscle().getId());
			res.add(dto);
		}
		return res;
	}

	public Image createImagen(String name) {
		Image img = new Image();
		Calendar hoy = Calendar.getInstance();
		img.setF_uploaded(hoy);
		img.setF_modified(hoy);
		img.setSrc(name);
		sessionFactory.getCurrentSession().saveOrUpdate(img);
		return img;
	}

	public void createExercise(String name,Long imagenId,Long muscleTypeId) {
		Exercise e = new Exercise();
		Image img = this.getImage(imagenId);
		e.setImage(img);
		Calendar hoy = Calendar.getInstance();
		e.setF_created(hoy);
		e.setF_modified(hoy);
		e.setName(name);
		SportTrainer sp = this.daoSportTrainer.getSportTrainer((long) 39);
		e.setOwner(sp);
		MuscleType mt = this.getMuscleType(muscleTypeId);
		e.setPrincipalMuscleType(mt);
		sessionFactory.getCurrentSession().saveOrUpdate(e);
		
	}

	private Image getImage(Long imagenId) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Image.class);
		crit.add(Restrictions.idEq(imagenId));
		Image img = (Image) crit.uniqueResult();
		return img;
		
	}
	
	public List<Image> getAllImages(){
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Image.class);
		return crit.list();		
	}


	public CustomerLevel getCustomerLevel(String level) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(CustomerLevel.class);
		crit.add(Restrictions.eq(CustomerLevel.A_LEVEL, level));
		return (CustomerLevel) crit.uniqueResult();
	}
	
	public void createObjetives() {
		Objetive obj1 = new Objetive();
		obj1.setObjetive(Objetive.O_ADAPTACION);
		sessionFactory.getCurrentSession().saveOrUpdate(obj1);
		
		Objetive obj2 = new Objetive();
		obj2.setObjetive(Objetive.O_AUMENTO_DE_LA_FUERZA);
		sessionFactory.getCurrentSession().saveOrUpdate(obj2);
		
		Objetive obj3 = new Objetive();
		obj3.setObjetive(Objetive.O_HIPERTROFIA);
		sessionFactory.getCurrentSession().saveOrUpdate(obj3);
		
		Objetive obj4 = new Objetive();
		obj4.setObjetive(Objetive.O_MANTENIMIENTO);
		sessionFactory.getCurrentSession().saveOrUpdate(obj4);
		
		Objetive obj5 = new Objetive();
		obj5.setObjetive(Objetive.O_PERDIDA_PESO);
		sessionFactory.getCurrentSession().saveOrUpdate(obj5);
		
		Objetive obj6 = new Objetive();
		obj6.setObjetive(Objetive.O_SALUD);
		sessionFactory.getCurrentSession().saveOrUpdate(obj6);
		
	}

	public void createObjetivesByLevel() {		
		Objetive adaptacion = this.getObjetive(Objetive.O_ADAPTACION);
		Objetive manteni = this.getObjetive(Objetive.O_MANTENIMIENTO);
		Objetive ppeso = this.getObjetive(Objetive.O_PERDIDA_PESO);
		Objetive hipertrofia = this.getObjetive(Objetive.O_HIPERTROFIA);
		Objetive salud = this.getObjetive(Objetive.O_SALUD);
		Objetive aumentoF = this.getObjetive(Objetive.O_AUMENTO_DE_LA_FUERZA);
		
		// ACLIMATACION
		ObjetiveByLevel objBL1 = new ObjetiveByLevel();
		CustomerLevel cl1 = this.getCustomerLevel(CustomerLevel.C_L_ACLIMATACION);
		objBL1.setLevel(cl1);
		List<Objetive> lst1 = new ArrayList<Objetive>();
		lst1.add(adaptacion);
		lst1.add(ppeso);
		lst1.add(manteni);
		objBL1.setObjetives(lst1);
		sessionFactory.getCurrentSession().save(objBL1);
		
		// PRINCIPIANTE
		ObjetiveByLevel objBL2 = new ObjetiveByLevel();
		CustomerLevel cl2 = this.getCustomerLevel(CustomerLevel.C_L_PRINCIPIANTE);
		objBL2.setLevel(cl2);
		List<Objetive> lst2 = new ArrayList<Objetive>();		
		lst2.add(ppeso);
		lst2.add(manteni);
		lst2.add(hipertrofia);
		lst2.add(salud);
		lst2.add(aumentoF);
		objBL2.setObjetives(lst2);
		sessionFactory.getCurrentSession().save(objBL2);
		
		// PRINCIPIANTE
		ObjetiveByLevel objBL3 = new ObjetiveByLevel();
		CustomerLevel cl3 = this.getCustomerLevel(CustomerLevel.C_L_INTERMEDIO);
		objBL3.setLevel(cl3);
		List<Objetive> lst3 = new ArrayList<Objetive>();		
		lst3.add(ppeso);
		lst3.add(manteni);
		lst3.add(hipertrofia);
		lst3.add(salud);
		lst3.add(aumentoF);
		objBL3.setObjetives(lst3);
		sessionFactory.getCurrentSession().save(objBL3);
		
		// PRINCIPIANTE
		ObjetiveByLevel objBL4 = new ObjetiveByLevel();
		CustomerLevel cl4 = this.getCustomerLevel(CustomerLevel.C_L_AVANZADO);
		objBL4.setLevel(cl4);
		List<Objetive> lst4 = new ArrayList<Objetive>();		
		lst4.add(ppeso);
		lst4.add(manteni);
		lst4.add(hipertrofia);
		lst4.add(salud);
		lst4.add(aumentoF);
		objBL4.setObjetives(lst4);
		sessionFactory.getCurrentSession().save(objBL4);		
	}

	public Objetive getObjetive(String objetive) {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(Objetive.class);
		crit.add(Restrictions.eq(Objetive.O_OBJETIVE, objetive));
		return (Objetive) crit.uniqueResult();
	}

	public void createPhysicalQualities() {
		// FUERZA
		PhysicalQuality ph1 = new PhysicalQuality();
		ph1.setQuality(PhysicalQuality.PQ_FUERZA);
		sessionFactory.getCurrentSession().saveOrUpdate(ph1);
		
		// RESISTENCIA
		PhysicalQuality ph2 = new PhysicalQuality();
		ph2.setQuality(PhysicalQuality.PQ_RESISTENCIA);
		sessionFactory.getCurrentSession().saveOrUpdate(ph2);
		
		// FLEXIBILIDAD
		PhysicalQuality ph3 = new PhysicalQuality();
		ph3.setQuality(PhysicalQuality.PQ_FLEXIBILIDAD);
		sessionFactory.getCurrentSession().saveOrUpdate(ph3);
	}

	public CustomerLevel getCustomerLevel(Long customerId) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select level from Customer as customer "
						+ " join customer.profile as profile "
						+ " join profile.customerLevel as level "
						+ " where customer.enabled = 1 "
						+ " and customer.id = :id ";
		Query query = session.createQuery(queryString);
		query.setParameter("id",customerId);
		CustomerLevel res  = (CustomerLevel) query.uniqueResult();		
		return res;
	}

	public List<Objetive> getObjetives(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select objetive.objetives from ObjetiveByLevel as objetive "
						+ " join objetive.level as level "
						+ " where level.id = :id ";
		Query query = session.createQuery(queryString);
		query.setParameter("id",id);
		List<Objetive> res  = query.list();		
		return res;
	}

	public List<PhysicalQuality> getPhysicalQualities() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(PhysicalQuality.class);
		List<PhysicalQuality> res = crit.list();
		return res;
	}

	public DurationLimit getDurationLimit(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select duration from DurationLimit as duration "
						+ " join duration.level as level "
						+ " where level.id = :id ";
		Query query = session.createQuery(queryString);
		query.setParameter("id",id);
		return (DurationLimit) query.uniqueResult();
	}

	public PhysicalQuality getPhysicalQuality(String physicalQuality) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(PhysicalQuality.class);
		crit.add(Restrictions.eq(PhysicalQuality.PQ_QUALITY, physicalQuality));
		return (PhysicalQuality)crit.uniqueResult();
	}

	public List<DayOfWeek> getDaysOfWeek() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(DayOfWeek.class);
		return crit.list();
	}

	public List<DayOfWeek> getDaysOfWeek(List<String> daysOfWeek) {
		List<DayOfWeek> res = new ArrayList<DayOfWeek>();
		for(String s : daysOfWeek){
			if(s != null && !s.isEmpty()){
				Criteria crit = sessionFactory.getCurrentSession().createCriteria(DayOfWeek.class);
				crit.add(Restrictions.eq(DayOfWeek.DAY, s));
				DayOfWeek day = (DayOfWeek) crit.uniqueResult();
				res.add(day);
			}
		}
		return res;
	}

	public void updateImage(Image image) {
		sessionFactory.getCurrentSession().saveOrUpdate(image);
	}
}