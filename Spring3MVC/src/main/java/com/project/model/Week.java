package com.project.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "week")
@Inheritance(strategy = InheritanceType.JOINED)
public class Week extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name= "sessions_week", joinColumns = @JoinColumn (name ="week_ID"),
			inverseJoinColumns = @JoinColumn(name = "session_ID"))
	private Set<Session> sessions;

	public Set<Session> getSessions() {
		return sessions;
	}

	public void setSessions(Set<Session> sessions) {
		this.sessions = sessions;
	}	
}