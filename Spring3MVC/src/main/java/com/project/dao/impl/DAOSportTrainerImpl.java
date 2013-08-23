package com.project.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.dao.DAOSecurity;
import com.project.dao.DAOSportTrainer;
import com.project.dto.DTOCustomer;
import com.project.dto.DTOProfile;
import com.project.model.Authority;
import com.project.model.Customer;
import com.project.model.Group;
import com.project.model.Profile;
import com.project.model.SportCentre;
import com.project.model.SportTrainer;
import com.project.servicios.SportTrainerManager;
import com.project.util.Util;

public class DAOSportTrainerImpl extends DAOComunImpl implements DAOSportTrainer{

	@Autowired
	private DAOSecurity	daoSecurity;
	
	@Autowired
	private SportTrainerManager sportTrainerManager;
	
	public void addSportTrainer(SportTrainer sportTrainer,Long userId) {
		Calendar hoy = Calendar.getInstance();
		sportTrainer.setInsertDate(hoy.getTime());
		sportTrainer.setModifyDate(hoy.getTime());
		List<Group> grupos = daoSecurity.getGrupos();
		Group grupo = null;
		for(Group g : grupos){
			List<Authority> authorities = g.getAuthorities();
			for(Authority a : authorities){
				if(a.getAuthority().equals("ROLE_SPORT_TRAINER")){
					grupo = g;
				}
			}
		}
		sportTrainer.addGrupo(grupo);
		SportCentre sportCentre = this.obtenerObjetoId(SportCentre.class, userId);
		sportTrainer.setSportCenter(sportCentre);
		sportTrainer.setPassword(Util.encriptarPassword(sportTrainer.getPassword()));
		this.guardarObjeto(sportTrainer);
	}

	public boolean deleteSportTrainer(Long userId) {
		boolean res = false;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(SportTrainer.class);
		crit.add(Restrictions.idEq(userId));
		SportTrainer sportTrainer = (SportTrainer) crit.uniqueResult();
		sportTrainer.setEnabled((short) 0);
		if(this.guardarObjeto(sportTrainer)){
			res = true;
		}
		return res;
	}
	
	public SportTrainer getSportTrainer(Long userId) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(SportTrainer.class);
		crit.add(Restrictions.idEq(userId));
		return (SportTrainer) crit.uniqueResult();
	}

	public void modifySportTrainer(SportTrainer sp) {
		SportTrainer s = this.getSportTrainer(sp.getId());
		if(sp.getName()!=null && !sp.getName().equals("")){
			s.setName(sp.getName());
		}
		if(sp.getSurName()!=null && !sp.getSurName().equals("")){
			s.setSurName(sp.getSurName());
		}
		if(!sp.getContactInfo().isEmpty()){
			s.modifySportTrainer(sp.getContactInfo());
		}
		Calendar hoy = Calendar.getInstance();		
		s.setModifyDate(hoy.getTime());	
		sessionFactory.getCurrentSession().saveOrUpdate(s);
	}

	
	public Integer getNumberCustomers(Long idByUsername) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select customer from SportTrainer as sportTrainer "
						+ " join sportTrainer.sportCenter as sportCentre "
						+ " join sportCentre.customers as customer"
						+ " where sportTrainer.enabled = 1 "
						+ " and customer.enabled = 1 "
						+ " and sportTrainer.sportCenter.id = customer.customerSportCenter.id "
						+ " and sportTrainer.id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("id",idByUsername);
		return query.list().size();
	}

	
	public Integer getNumberSportPlans(Long idByUsername) {		
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select sportTrainer from SportPlan as sportPlan "
						+ " join sportPlan.sportTrainer as sportTrainer "
						+ " where sportTrainer.enabled = 1 "
						+ " and sportTrainer.id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("id",idByUsername);
		return query.list().size();
	}

	public List<DTOCustomer> getCustomers(Long idByUsername) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select customer from SportTrainer as sportTrainer "
				+ " join sportTrainer.sportCenter as sportCentre "
				+ " join sportCentre.customers as customer"
				+ " where sportTrainer.enabled = 1 "
				+ " and customer.enabled = 1 "
				+ " and sportTrainer.sportCenter.id = customer.customerSportCenter.id "
				+ " and sportTrainer.id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("id",idByUsername);
		List<Customer> res  = query.list();
		List<DTOCustomer> lstRes = new ArrayList<DTOCustomer>();
		for(Customer c: res){
			DTOCustomer dto = new DTOCustomer();
			dto.setName(c.getName());
			dto.setSurName(c.getSurName());
			dto.setId(c.getId());
			lstRes.add(dto);			
		}
		return lstRes;
	}

	public String getOwner(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select sportCentre.name from SportTrainer as sportTrainer "
				+ " join sportTrainer.sportCenter as sportCentre "
				+ " where sportTrainer.id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("id",id);
		return (String) query.uniqueResult();		
	}

	public List<DTOProfile> getInfoCustomers(Long idByUsername) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select profile from SportTrainer as sportTrainer "
				+ " join sportTrainer.sportCenter as sportCentre "
				+ " join sportCentre.customers as customer "
				+ " join customer.profile as profile"
				+ " where sportTrainer.enabled = 1 "
				+ " and customer.enabled = 1 "
				+ " and sportTrainer.sportCenter.id = customer.customerSportCenter.id "
				+ " and sportTrainer.id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("id",idByUsername);
		List<Profile> res  = query.list();
		List<DTOProfile> lstRes = new ArrayList<DTOProfile>();
		for(Profile p : res){
			DTOProfile dto = null;
			if(p != null){
				dto = new DTOProfile();
				dto.setIdCustomer(p.getCustomer().getId());
				dto.setDateBirth(p.getDateBirth());
				dto.setHeight(p.getHeight());
				dto.setWeight(p.getWeight());
				dto.setCustomerLevel(p.getLevel().getLevel());
				dto.setLaboralActivity(p.getLaboralActivity());
			}
			lstRes.add(dto);
		}
		return lstRes;
	}
}
