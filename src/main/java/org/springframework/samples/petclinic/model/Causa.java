package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.DoubleStream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Entity
@Table(name="causas")
public class Causa extends NamedEntity {
	
	@NotEmpty
	private String description;
	
	@NotNull
	@Positive
	@Column(name="budget_target")
	private Double budgetTarget;
	
	@NotEmpty
	private String organization;
	
	@OneToMany(mappedBy= "causa")
	private Set<Donacion> donaciones;
	
	private Boolean isClosed;
	
	
	public Boolean getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(Boolean isClosed) {
		this.isClosed = isClosed;
	}

	public Set<Donacion> getDonaciones() {
		return donaciones;
	}

	public void setDonaciones(Set<Donacion> donaciones) {
		this.donaciones = donaciones;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getBudgetTarget() {
		return budgetTarget;
	}

	public void setBudgetTarget(Double budgetTarget) {
		this.budgetTarget = budgetTarget;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}
	
	public void checkBudget() {
		List<Double> money = new ArrayList<>();
		for(Donacion d : this.donaciones) {
			money.add(d.getMoney());
		}
		Double sum = 0.0;
		for (Double e : money) sum += e;
		
		if(sum >= this.budgetTarget) {
			this.isClosed = true;
		}
		
	}
	
	/* Para comprobar si la causa está cerrada, debo crear un atributo
	 * derivado que compruebe si la suma de las cantidades de las donaciones
	 * es igual al budgetTarget
	 */

	
}
