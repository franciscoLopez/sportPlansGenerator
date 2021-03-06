package com.project.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.DAOSecurity;
import com.project.model.Group;
import com.project.model.User;

public class DAOSecurityImpl extends DAOComunImpl implements DAOSecurity{

	@Transactional(readOnly = true)
	public User loadUsuarioNombre(String username) {
		
		User result = null;
		Criteria crtUsuario = null;
		
		crtUsuario = sessionFactory.getCurrentSession().createCriteria(User.class);
		crtUsuario.add(Restrictions.eq(User.A_USERNAME, username));
		return (User) crtUsuario.uniqueResult();
		
	}

	public List<Group> getGrupos() {
		Criteria crtUsuario = sessionFactory.getCurrentSession().createCriteria(Group.class);
		List<Group> lstGrupos = crtUsuario.list();
		return lstGrupos;
	}
}
