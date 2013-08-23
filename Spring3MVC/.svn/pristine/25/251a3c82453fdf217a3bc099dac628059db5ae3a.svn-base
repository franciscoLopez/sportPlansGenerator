package com.project.dao;

import java.util.List;
import java.util.SortedSet;

import com.project.model.User;

public interface DAOUser {
	
	public List<User> listContact();
	
	public SortedSet<String> getUsernames();
	
	public User getUserByUserName(String userName);
	
	public Long getIdByUsername(String userName);

	public void modifyPassWord(User user);

	public void setUsername(org.springframework.security.core.userdetails.User user, String username);
}
