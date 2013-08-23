package com.project.dao.impl;

import java.util.Calendar;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.DAOUser;
import com.project.model.User;
import com.project.util.Util;

@Transactional
public class DAOUserImpl extends DAOComunImpl implements DAOUser{
	
	
	public List<User> listContact() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(User.class);
		return crit.list();
	}

	public SortedSet<String> getUsernames() {
		SortedSet<String> res = new TreeSet<String>();
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(User.class);
		crit.setProjection(Property.forName(User.A_USERNAME));
		res.addAll(crit.list());		
		return res;
	}

	public User getUserByUserName(String userName) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(User.class);
		crit.add(Restrictions.eq(User.A_USERNAME, userName));
		return (User)crit.uniqueResult();
	}
	
	public Long getIdByUsername(String userName){
		User user = getUserByUserName(userName);
		return user.getId();
	}

	public void modifyPassWord(User user) {
		User usuario = getUserByUserName(user.getUsername());
		usuario.setPassword(Util.encriptarPassword(user.getPassword()));
		usuario.setModifyDate(Calendar.getInstance().getTime());
		this.guardarObjeto(usuario);		
	}

	public void setUsername(org.springframework.security.core.userdetails.User user, String username) {
		User usuario = getUserByUserName(user.getUsername());
		usuario.setUsername(username);
		this.guardarObjeto(usuario);
	}
}
