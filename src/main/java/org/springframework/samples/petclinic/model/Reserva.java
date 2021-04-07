package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="reservas")
public class Reserva extends BaseEntity {
	
	@ManyToOne
	@JoinColumn(name="pet_id")
	private Pet pet;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@JoinColumn(name="start_date")
	private LocalDate startDate;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@JoinColumn(name="finish_date")
	private LocalDate finishDate;

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(LocalDate finishDate) {
		this.finishDate = finishDate;
	}
	
	
	
	
}
