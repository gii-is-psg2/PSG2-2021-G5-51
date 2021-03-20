package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.ReservaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/reservas")
public class ReservaController {

	
	@Autowired
	private ReservaService reservaService;
	
	@Autowired
	private PetService petService;
	
	
	@GetMapping(value = "/listadoReservas")
	public String listadoReservas(ModelMap model) {
		List<Reserva> reservas = reservaService.findAll();
		model.addAttribute("reservas", reservas);
		return "reservas/listadoReservas";
		
	}
	
	
	@GetMapping(value = "/listadoReservas/{petId}")
	public String listadoReservas(@PathVariable("petId") int petId, ModelMap model) {
		String vista = null;
		Optional<Pet> op = Optional.of(petService.findPetById(petId));
		if(op.isPresent()) {
			List<Reserva> reservas = reservaService.findReservasByPetId(petId);
			model.addAttribute("reservas", reservas);
			vista = "reservas/listadoReservas";
		} else {
			model.addAttribute("message", "The pet doesn't exist");
			vista = "/error";
		}
		return vista;
		
	}
	
	
	@GetMapping(value = "/delete/{reservaId}")
	public String borrarReserva(@PathVariable("reservaId") int id, ModelMap model) {
		String vista;
		Optional<Reserva> op = reservaService.findReservaById(id);
		if(op.isPresent()) {
			reservaService.delete(op.get());
			model.addAttribute("message", "Reserve deleted sucessfully");
		} else {
			model.addAttribute("message", "Reserve not found");
		}
		vista = listadoReservas(model);
		return vista;
	}
	
	
	
	
}
