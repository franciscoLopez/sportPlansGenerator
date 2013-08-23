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
import com.project.dao.DAOSportCentre;
import com.project.dto.DTOCustomer;
import com.project.dto.DTOSportTrainer;
import com.project.model.Authority;
import com.project.model.Customer;
import com.project.model.Group;
import com.project.model.SportCentre;
import com.project.model.SportTrainer;
import com.project.util.Util;

public class DAOSportCentreImpl extends DAOComunImpl implements DAOSportCentre{
	
	@Autowired
	private DAOSecurity	daoSecurity;
	
	public void addSportCentre(SportCentre sportCentre) {
		Calendar hoy = Calendar.getInstance();
		sportCentre.setInsertDate(hoy.getTime());
		sportCentre.setModifyDate(hoy.getTime());
		List<Group> grupos = daoSecurity.getGrupos();
		Group grupo = null;
		for(Group g : grupos){
			List<Authority> authorities = g.getAuthorities();
			for(Authority a : authorities){
				if(a.getAuthority().equals("ROLE_SPORT_CENTRE")){
					grupo = g;
				}
			}
		}
		sportCentre.addGrupo(grupo);
		sportCentre.setPassword(Util.encriptarPassword(sportCentre.getPassword()));
		this.guardarObjeto(sportCentre);		
	}

	
	public Integer getNumberSportTrainers(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select sportTrainer from SportCentre as sportCentre "
						+ " join sportCentre.sportTrainers as sportTrainer "
						+ " where sportTrainer.enabled = 1 "
						+ " and sportCentre.id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("id",id);
		Integer res  = query.list().size();
		
		return res;
	}

	public List<DTOSportTrainer> getSportTrainers(Long sportCentreID) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select sportTrainer from SportCentre as sportCentre "
						+ " join sportCentre.sportTrainers as sportTrainer "
						+ " where sportTrainer.enabled = 1 "
						+ " and sportCentre.id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("id",sportCentreID);
		List<SportTrainer> res  = query.list();
		List<DTOSportTrainer> lstRes = new ArrayList<DTOSportTrainer>();
		for(SportTrainer s: res){
			DTOSportTrainer dto = new DTOSportTrainer();
			dto.setName(s.getName());
			dto.setSurName(s.getSurName());
			dto.setId(s.getId());
			lstRes.add(dto);			
		}
		return lstRes;
	}


	public Integer getNumberCustomers(Long sportCentreID) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select customer from SportCentre as sportCentre "
						+ " join sportCentre.customers as customer "
						+ " where customer.enabled = 1 "
						+ " and sportCentre.id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("id",sportCentreID);
		Integer res  = query.list().size();
		
		return res;
	}


	public List<DTOCustomer> getCustomers(Long sportCentreID) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select customer from SportCentre as sportCentre "
						+ " join sportCentre.customers as customer "
						+ " where customer.enabled = 1 "
						+ " and sportCentre.id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("id",sportCentreID);
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

	public SportCentre getSportCentre(String username) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(SportCentre.class);
		crit.add(Restrictions.eq(SportCentre.A_USERNAME, username));
		return (SportCentre)crit.uniqueResult();
	}
	
	public SportCentre getSportCentreById(Long sportCentreID){
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(SportCentre.class);
		crit.add(Restrictions.idEq(sportCentreID));
		return (SportCentre) crit.uniqueResult();
	}

	public void modifySportCentre(SportCentre infoSportCentre) {
		SportCentre s = this.getSportCentreById(infoSportCentre.getId());
		if(infoSportCentre.getName() != null && !infoSportCentre.getName().equals("")){
			s.setName(infoSportCentre.getName());
		}
		if(infoSportCentre.getName() != null && !infoSportCentre.getName().equals("")){
			s.setName(infoSportCentre.getName());
		}
		if(!infoSportCentre.getContactInfo().isEmpty()){
			s.modifySportTrainer(infoSportCentre.getContactInfo());
		}
		Calendar hoy = Calendar.getInstance();		
		s.setModifyDate(hoy.getTime());	
		sessionFactory.getCurrentSession().saveOrUpdate(s);		
	}

	public Integer getNumberSportPlans(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select sportCenter from SportPlan as sportPlan "
						+ " join sportPlan.sportTrainer as sportTrainer "
						+ " join sportTrainer.sportCenter as sportCenter "
						+ " where sportTrainer.enabled = 1 "
						+ " and sportCenter.id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("id",id);
		return query.list().size();		
	}

}
