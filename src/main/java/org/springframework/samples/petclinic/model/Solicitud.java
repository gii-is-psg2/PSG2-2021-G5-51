package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="solicitudes")
public class Solicitud extends BaseEntity {
	@NotEmpty
	private String info;
	
	@OneToOne
	@JoinColumn(name = "adopcion_id")
	private Adopcion adoption;

	@OneToOne
	@JoinColumn(name = "owner_id")
	private Owner newOwner;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@JoinColumn(name="request_date")
	private LocalDate requestDate;
	

	public String getInfo() {
		return this.info;
	}

	public void setInfo(final String info) {
		this.info = info;
	}

	public Adopcion getAdoption() {
		return this.adoption;
	}

	public void setAdoption(final Adopcion adoption) {
		this.adoption = adoption;
	}

	public LocalDate getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}

	public Owner getNewOwner() {
		return this.newOwner;
	}

	public void setNewOwner(Owner newOwner) {
		this.newOwner = newOwner;
	}
	
}