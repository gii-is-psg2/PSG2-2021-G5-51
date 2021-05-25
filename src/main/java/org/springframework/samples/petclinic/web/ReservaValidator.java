package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.service.ReservaService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ReservaValidator implements Validator {
	
	private ReservaService reservaService;
	
	public ReservaValidator(ReservaService reservaService) {
		this.reservaService = reservaService;
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Reserva reserva = (Reserva) obj;
		Integer petId = reserva.getPet().getId();
		List<Reserva> lsReservas = reservaService.findReservasByPetId(petId);

		if (reserva.getStartDate()==null) {
			errors.rejectValue("startDate", "Requerido", "Campo Requerido");	
		}
		
		if (reserva.getFinishDate()==null) {
			errors.rejectValue("finishDate", "Requerido", "Campo Requerido");	
		}
		
		if (reserva.getFinishDate()!=null && reserva.getStartDate()!=null && reserva.getStartDate().isAfter(reserva.getFinishDate())) {
			errors.rejectValue("finishDate", "Fecha fin anterior a fecha inicio", "La fecha de fin debe ser posterior a la fecha de fecha de inicio");
		}

		if(!lsReservas.isEmpty()) {
			for (Reserva r:lsReservas) {
				if (isThereOverlap(reserva.getStartDate(),reserva.getFinishDate(), r.getStartDate(), r.getFinishDate())) {
					errors.rejectValue("startDate", "Las fechas se superponen con las de una reserva existente", 
							"Las fechas se superponen con las de una reserva existente" );
				}
			}
		}
		
		if(reserva.getStartDate().isBefore(LocalDate.now())) {
			errors.rejectValue("startDate", "Fecha inicio anterior a la fecha actual", "La fecha de inicio de la reserva debe ser posterior a la actual");
		}
	}

	private static boolean isThereOverlap(LocalDate b1s, LocalDate b1f, LocalDate b2s, LocalDate b2f) {
		return b1s.isAfter(b2s) && b1s.isBefore(b2f) || 
				b1f.isAfter(b2s) && b1f.isBefore(b2f) ||
				b1s.isBefore(b2s) && b1f.isAfter(b2f) ||
				b1s.equals(b2s) && b1f.isAfter(b2f) ||
				b1s.isBefore(b2s) && b1f.equals(b2f) ||
				b1s.equals(b2s) && b1f.equals(b2f);
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Reserva.class.isAssignableFrom(clazz);
	}
	
}