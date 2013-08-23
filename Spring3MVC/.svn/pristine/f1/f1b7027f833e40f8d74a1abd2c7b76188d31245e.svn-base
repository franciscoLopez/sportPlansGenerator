
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

import com.project.util.Tabla;

@Entity
@Table(name = Tabla.PLAN)
@Inheritance(strategy = InheritanceType.JOINED)
public class Plan extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="periods_plan", joinColumns = @JoinColumn(name = "plan_ID"),
				inverseJoinColumns = @JoinColumn(name = "period_ID"))
	private Set<Period> periods;

	public Set<Period> getPeriods() {
		return periods;
	}

	public void setPeriods(Set<Period> periods) {
		this.periods = periods;
	}
}