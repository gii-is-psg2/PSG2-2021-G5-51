package org.springframework.samples.petclinic.model;

import java.util.Set;

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
	
	@OneToMany
	private Set<Donacion> donaciones;
	
	
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
	
	/* Para comprobar si la causa está cerrada, debo crear un atributo
	 * derivado que compruebe si la suma de las cantidades de las donaciones
	 * es igual al budgetTarget
	 */

	
}
