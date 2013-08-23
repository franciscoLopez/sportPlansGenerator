package com.project.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.DAOComun;
import com.project.model.CustomerLevel;

@Transactional
public class DAOComunImpl implements DAOComun{

	@Autowired
	protected SessionFactory	sessionFactory;
	
	public <T> boolean guardarObjeto(T obj) throws DataAccessException {

		try {
			sessionFactory.getCurrentSession().saveOrUpdate(obj);
			return true;
		} catch (NonUniqueObjectException e) {
			try {
				sessionFactory.getCurrentSession().clear();
				sessionFactory.getCurrentSession().saveOrUpdate(obj);
				return true;
			} catch (HibernateException e1) {
				return false;
			}
		}
	}

	public <T> T obtenerObjetoId(Class<T> clase, Long id) {

		return obtenerObjetoId(clase, id, null);
	}
	
	public <T> T obtenerObjetoId(Class<T> clase, Long id, String lista) {

		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(clase).add(Restrictions.eq("id", id));
			if (lista != null && !lista.isEmpty())
				crit.setFetchMode(lista, FetchMode.JOIN).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return clase.cast(crit.uniqueResult());
		} catch (HibernateException e) {
			return null;
		}
	}

	public List<String> getCustomerLevels() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(CustomerLevel.class);
		List<CustomerLevel> levels = crit.list();
		List<String> customerLevels = new ArrayList<String>();
		for(CustomerLevel c : levels){
			customerLevels.add(c.getLevel());
		}
		return customerLevels;
	}
}
