package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "donaciones")
public class Donacion extends NamedEntity {

	@NotNull
	@Positive
	private Double money;

	@ManyToOne(optional = false)
	private Causa causa;
	
	@ManyToOne(optional = false)
	private User usuario;

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Causa getCausa() {
		return causa;
	}

	public void setCausa(Causa causa) {
		this.causa = causa;
	}

}
