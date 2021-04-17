package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.service.ReservaService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ReservaValidator implements Validator {
	
	@Autowired
	private ReservaService reservaService;
	
	@Override
	public void validate(Object obj, Errors errors) {
		Reserva reserva = (Reserva) obj;

		if (reserva.getStartDate()==null) {
			errors.rejectValue("startDate", "Requerido", "Campo Requerido");	
		}
		
		if (reserva.getFinishDate()==null) {
			errors.rejectValue("finishDate", "Requerido", "Campo Requerido");	
		}
		
		if (reserva.getFinishDate()!=null && reserva.getStartDate()!=null && reserva.getStartDate().isAfter(reserva.getFinishDate())) {
			errors.rejectValue("finishDate", "Fecha fin anterior a fecha inicio", "La fecha de fin debe ser posterior a la fecha de fecha de inicio");
		}
		
		Pet pet = reserva.getPet();
		List<Reserva> lsReservas = reservaService.findReservasByPetId(pet.getId());
		for (Reserva r:lsReservas) {
			if (isThereOverlap(reserva.getStartDate(),reserva.getFinishDate(), r.getStartDate(), r.getFinishDate())) {
				errors.reject("Date Period Overlap", "Las fechas se superponen con las de una reserva existente");
			}
		}
		
	}

	private static boolean isThereOverlap(LocalDate b1Start, LocalDate b1Finish, LocalDate b2Start, LocalDate b2Finish) {
		return b1Start.isAfter(b2Start) && b1Start.isBefore(b2Finish) ||
				b1Finish.isAfter(b2Start) && b1Finish.isBefore(b2Finish) ||
                b1Start.isBefore(b2Start) && b1Finish.isAfter(b2Finish);
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Reserva.class.isAssignableFrom(clazz);
	}
	
}