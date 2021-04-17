package org.springframework.samples.petclinic.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.ReservaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;


@Controller
@RequestMapping("/owners")
public class ReservaController {
    public static final String RESERVAS_FORM = "reservas/createOrUpdateReservaForm";
	
	@Autowired
	private ReservaService reservaService;
	
	@Autowired
	private PetService petService;
	
	@ModelAttribute("reserva")
	public Reserva loadPetWithVisit(@PathVariable("petId") int petId) {
		Reserva reserva = new Reserva();
		Pet pet = petService.findPetById(petId);		
		reserva.setPet(pet);
		return reserva;
	}
	
	@InitBinder("reserva")
	public void initReservasBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new ReservaValidator());
	}
	
	
	@GetMapping(value = "/reservas")
	public String listReservas(int ownerId, int petId, ModelMap model) {
		List<Reserva> reservas = reservaService.findReservasByPetId(petId);
		model.addAttribute("reservas", reservas);
		model.addAttribute("petId", petId);
		model.addAttribute("ownerId", ownerId);
		return "reservas/listadoReservas";
		
	}
	
	
	@GetMapping(value = "/{ownerId}/pets/{petId}/reservas")
	public String listadoReservas(@PathVariable("ownerId") int ownerId, @PathVariable("petId") int petId, ModelMap model) {
		String vista = null;
		Optional<Pet> op = Optional.of(petService.findPetById(petId));
		if(op.isPresent()) {
			List<Reserva> reservas = reservaService.findReservasByPetId(petId);
			model.addAttribute("reservas", reservas);
			model.addAttribute("petId", petId);
			model.addAttribute("ownerId", ownerId);
			vista = "reservas/listadoReservas";
		} else {
			model.addAttribute("message", "There was an error, the pet does not exist.");
			vista = "/error";
		}
		return vista;
		
	}

	@GetMapping("/{ownerId}/pets/{petId}/reservas/new")
    public String addNewReserva(@PathVariable("ownerId") int ownerId, @PathVariable("petId") int petId, ModelMap model) {
        model.addAttribute("reserva", new Reserva());
		model.addAttribute("pet", petService.findPetById(petId));
        return RESERVAS_FORM;
    }

	@PostMapping("/{ownerId}/pets/{petId}/reservas/new")
    public String saveNewReserva(@PathVariable("ownerId") int ownerId, @PathVariable("petId") int petId, @Valid Reserva reserva, BindingResult binding, ModelMap model) {
		if(binding.hasErrors()) {
			model.addAttribute("pet", petService.findPetById(petId));
            return RESERVAS_FORM;
        }else {
			reserva.setPet(petService.findPetById(petId));
            reservaService.save(reserva);
            model.addAttribute("message", "The reserve was created succesfully.");
            return listReservas(ownerId, petId, model);
        }
    }

	@GetMapping("/{ownerId}/pets/{petId}/reservas/{reservaId}/edit")
    public String editReserva(@PathVariable("ownerId") int ownerId, @PathVariable("petId") int petId, @PathVariable("reservaId") int id, ModelMap model){
        Optional<Reserva> reserva = reservaService.findReservaById(id);
		model.addAttribute("pet", petService.findPetById(petId));
        if(reserva.isPresent()) {
            model.addAttribute("reserva", reserva.get());
            return RESERVAS_FORM;
        }
		else
            model.addAttribute("message","There was an error, the reserve does not exist.");
        return listReservas(ownerId, petId, model);
    }

    @PostMapping("/{ownerId}/pets/{petId}/reservas/{reservaId}/edit")
    public String editReserva(@PathVariable("ownerId") int ownerId, @PathVariable("petId") int petId, @PathVariable("reservaId") int id, ModelMap model, @Valid Reserva modifiedReserva, BindingResult binding){
        Optional<Reserva> reserva = reservaService.findReservaById(id);
		model.addAttribute("pet", petService.findPetById(petId));
        if(!reserva.isPresent()){
            model.addAttribute("message","There was an error, the reserve does not exist.");
        }
        if(binding.hasErrors()){
			model.put("reserva", reserva.get());
            return RESERVAS_FORM;
        }
        else{
			modifiedReserva.setPet(petService.findPetById(petId));
            BeanUtils.copyProperties(modifiedReserva, reserva.get(), "id");
            reservaService.save(reserva.get());
            model.addAttribute("message", "The reserve was updated succesfully.");
        }
		return listReservas(ownerId, petId, model);
    }
	
	
	@GetMapping(value = "/{ownerId}/pets/{petId}/reservas/{reservaId}/delete")
	public String borrarReserva(@PathVariable("ownerId") int ownerId, @PathVariable("petId") int petId, @PathVariable("reservaId") int id, ModelMap model) {
		Optional<Reserva> reserva = reservaService.findReservaById(id);
		if(reserva.isPresent()) {
			reservaService.delete(reserva.get());
			model.addAttribute("message", "The reserve was deleted succesfully.");
		} else {
			model.addAttribute("message", "There was an error, the reserve does not exist.");
		}
		return listReservas(ownerId, petId, model);
	}
	
}
