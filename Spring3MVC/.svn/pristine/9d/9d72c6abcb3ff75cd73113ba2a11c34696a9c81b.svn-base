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
@Table(name = "period")
@Inheritance(strategy = InheritanceType.JOINED)
public class Period extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="weeks_period", joinColumns = @JoinColumn(name = "period_ID"),
				inverseJoinColumns = @JoinColumn(name = "week_ID"))
	private Set<Week> weeks;

	public Set<Week> getWeeks() {
		return weeks;
	}

	public void setWeeks(Set<Week> weeks) {
		this.weeks = weeks;
	}
}