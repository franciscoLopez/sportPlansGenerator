package com.project.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.dao.DAOCustomer;
import com.project.dao.DAOSecurity;
import com.project.dto.DTOProfile;
import com.project.model.Authority;
import com.project.model.Customer;
import com.project.model.CustomerLevel;
import com.project.model.Group;
import com.project.model.Profile;
import com.project.model.SportCentre;
import com.project.model.SportPlan;
import com.project.model.SportTrainer;
import com.project.util.Util;

public class DAOCustomerImpl extends DAOComunImpl implements DAOCustomer{

	@Autowired
	private DAOSecurity	daoSecurity;
	
	public void addCustomer(Customer customer, Long idUser) {
		Calendar hoy = Calendar.getInstance();
		customer.setInsertDate(hoy.getTime());
		customer.setModifyDate(hoy.getTime());
		List<Group> grupos = daoSecurity.getGrupos();
		Group grupo = null;
		for(Group g : grupos){
			List<Authority> authorities = g.getAuthorities();
			for(Authority a : authorities){
				if(a.getAuthority().equals("ROLE_CUSTOMER")){
					grupo = g;
				}
			}
		}
		customer.addGrupo(grupo);
		SportCentre sportCentre = this.obtenerObjetoId(SportCentre.class, idUser);
		customer.setSportCenter(sportCentre);
		customer.setPassword(Util.encriptarPassword(customer.getPassword()));
		this.guardarObjeto(customer);
		
	}
	
	public void addCustomerBySportTrainer(Customer customer, Long id) {
		Calendar hoy = Calendar.getInstance();
		customer.setInsertDate(hoy.getTime());
		customer.setModifyDate(hoy.getTime());
		List<Group> grupos = daoSecurity.getGrupos();
		Group grupo = null;
		for(Group g : grupos){
			List<Authority> authorities = g.getAuthorities();
			for(Authority a : authorities){
				if(a.getAuthority().equals("ROLE_CUSTOMER")){
					grupo = g;
				}
			}
		}
		customer.addGrupo(grupo);
		SportTrainer sportTrainer = this.obtenerObjetoId(SportTrainer.class, id);
		customer.setSportCenter(sportTrainer.getSportCenter());
		customer.setPassword(Util.encriptarPassword(customer.getPassword()));
		this.guardarObjeto(customer);		
	}

	public Customer getCustomer(Long userId) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		crit.add(Restrictions.idEq(userId));
		return (Customer) crit.uniqueResult();
	}

	public void modifyCustomer(Customer infoCustomer) {
		Customer c = this.getCustomer(infoCustomer.getId());
		if(infoCustomer.getName() != null && !infoCustomer.getName().equals("")){
			c.setName(infoCustomer.getName());
		}
		if(infoCustomer.getSurName() != null && !infoCustomer.getSurName().equals("")){
			c.setSurName(infoCustomer.getSurName());
		}
		if(!infoCustomer.getContactInfo().isEmpty()){
			c.modifyCustomer(infoCustomer.getContactInfo());
		}
		Calendar hoy = Calendar.getInstance();		
		c.setModifyDate(hoy.getTime());	
		sessionFactory.getCurrentSession().saveOrUpdate(c);		
	}
	
	public boolean deleteCustomer(Long userId) {
		boolean res = false;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		crit.add(Restrictions.idEq(userId));
		Customer customer = (Customer) crit.uniqueResult();
		customer.setEnabled((short) 0);
		if(this.guardarObjeto(customer)){
			res = true;
		}
		return res;
	}

	public void modifyProfile(DTOProfile dtoprofile) {		
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select profile from Profile as profile "
						+ " join profile.customer as customer "
						+ " where customer.id = :id ";
		Query query = session.createQuery(queryString);
		query.setParameter("id",dtoprofile.getIdCustomer());		
		Profile p = (Profile) query.uniqueResult();
		if(p == null){
			p = new Profile();
		}
		if(dtoprofile.getDateBirth() != null){
			p.setDateBirth(dtoprofile.getDateBirth());
		}
		if(dtoprofile.getHeight() != null){
			p.setHeight(dtoprofile.getHeight());
		}
		if(dtoprofile.getWeight() != null){
			p.setWeight(dtoprofile.getWeight());
		}
		if(dtoprofile.getLaboralActivity() != null && !dtoprofile.getLaboralActivity().isEmpty()){
			p.setLaboralActivity(dtoprofile.getLaboralActivity());
		}
		if(dtoprofile.getCustomerLevel() != null && !dtoprofile.getCustomerLevel().isEmpty()){
			CustomerLevel cus = this.getCustomerLevel(dtoprofile.getCustomerLevel());
			p.setLevel(cus);
		}
		Customer c = this.getCustomer(dtoprofile.getIdCustomer());
		c.setProfile(p);
		sessionFactory.getCurrentSession().saveOrUpdate(p);
		
	}

	private CustomerLevel getCustomerLevel(String customerLevel) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(CustomerLevel.class);
		crit.add(Restrictions.eq(CustomerLevel.A_LEVEL, customerLevel));
		return (CustomerLevel) crit.uniqueResult();
	}

	public DTOProfile getDTOProfile(Long userId) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select profile from Profile as profile "
						+ " join profile.customer as customer "
						+ " where customer.id = :id ";
		Query query = session.createQuery(queryString);
		query.setParameter("id",userId);		
		Profile p = (Profile) query.uniqueResult();
		DTOProfile dto = null;
		if(p != null){
			dto = new DTOProfile();
			dto.setIdCustomer(p.getCustomer().getId());
			if(p.getDateBirth() != null){
				dto.setDateBirth(p.getDateBirth());
			}
			if(p.getHeight() != null){
				dto.setHeight(p.getHeight());
			}
			if(p.getWeight() != null){
				dto.setWeight(p.getWeight());
			}
			if(p.getLaboralActivity() != null && !p.getLaboralActivity().isEmpty()){
				dto.setLaboralActivity(p.getLaboralActivity());
			}
			if(p.getLevel() != null && !p.getLevel().getLevel().isEmpty()){
				dto.setCustomerLevel(p.getLevel().getLevel());
			}
		}
		return dto;
	}

	public boolean hasUndefinedSportPlans(Long userId) {
		Boolean res = false;
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select sportPlan from SportPlan as sportPlan "
						+ " join sportPlan.customer as customer "
						+ " where customer.id = :id "
						+ " and sportPlan.defined = false";
		Query query = session.createQuery(queryString);
		query.setParameter("id",userId);
		List<SportPlan> lst = query.list();
		if(lst.size() > 0){
			res = true;
		}
		return res;
	}

	public Integer getNumSportPlansGenerated(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select sportPlan from SportPlan as sportPlan "
						+ " join sportPlan.customer as customer "
						+ " where customer.id = :id ";
		Query query = session.createQuery(queryString);
		query.setParameter("id",id);
		return query.list().size();
	}

	public CustomerLevel getCustomerLevel(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select level from Customer as customer "
						+ " join customer.profile as profile "
						+ " join profile.customerLevel as level "
						+ " where customer.id = :id ";
		Query query = session.createQuery(queryString);
		query.setParameter("id",id);
		return (CustomerLevel) query.uniqueResult();
	}

	public SportCentre getOwner(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select sportCentre from Customer as customer "
				+ " join customer.customerSportCenter as sportCentre "
				+ " where customer.id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("id",id);
		return (SportCentre) query.uniqueResult();
	}
	
	public SportPlan getSportPlan(Long sportPlanId){		
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select sportPlan from SportPlan as sportPlan "
				+ " left join fetch sportPlan.plan as plan "
				+ " left join fetch plan.periods as period "
				+ " left join fetch period.weeks as week "
				+ " left join fetch week.sessions as session "
				+ " left join fetch session.exercises as exercise "
				+ " where sportPlan.id = :id ";
		Query query = session.createQuery(queryString);
		query.setParameter("id",sportPlanId);
		SportPlan sp = (SportPlan) query.uniqueResult();
		session.flush();
		session.refresh(sp);
		return sp;
	}
}
