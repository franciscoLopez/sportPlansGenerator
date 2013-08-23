package com.project.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.project.util.Tabla;

@Entity
@Table(name=Tabla.GROUPS)
public class Group extends BaseEntity{
	
	
	private static final long serialVersionUID = 1L;

	private String group_name;
	
	@ManyToMany
	@JoinTable(name = "group_members",
			joinColumns=@JoinColumn(name="group_id"),
			inverseJoinColumns=@JoinColumn(name="username",table="users",referencedColumnName="username"))
	private List<User> members;	
	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "group_authorities", joinColumns=@JoinColumn(name="group_id"),
			inverseJoinColumns=@JoinColumn(name="authority"))
	private List<Authority> authorities;
	
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public List<User> getMembers() {
		if (members == null)
			members = new ArrayList<User>();
		return members;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public List<Authority> getAuthorities() {
		if (authorities == null)
			authorities = new ArrayList<Authority>();
		return authorities;
	}
	
	public String toString(){
		return group_name;
	}
}
