package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Solicitud;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.Adopcion;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.SolicitudService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.samples.petclinic.service.AdopcionService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adoption")
public class SolicitudController {
	
	@Autowired
	private SolicitudService solicitudService;
	@Autowired
	private AdopcionService adopcionService;
	@Autowired
	private OwnerService ownerService;
	
	@GetMapping("/{adoptionId}/requests")
	public String listSolicitudes(@PathVariable("adoptionId") final Integer adoptionId, ModelMap model) {
		List<Solicitud> requests = solicitudService.findAll();
		System.out.println(requests.toString());
		model.put("requests", requests);
		model.put("username", SecurityContextHolder.getContext().getAuthentication().getName());
		return "solicitud/requestsList";
	}

	@GetMapping("/{adoptionId}/requests/new")
    public String addNewSolicitud(@PathVariable("adoptionId") final Integer adoptionId,final ModelMap model) {
		Solicitud solicitud = new Solicitud();
		model.put("solicitud", solicitud);
        
        return "solicitud/createOrUpdateRequestForm";
    }
	
	
	@PostMapping("/{adoptionId}/requests/new")
    public String saveNewSolicitud(@PathVariable("adoptionId") final Integer adoptionId, @Valid final Solicitud solicitud, final BindingResult result, final ModelMap model) {

        if (result.hasErrors()) {
        	model.addAttribute("solicitud", solicitud);
			model.addAttribute("message", "There was an error in the form: " + result.getAllErrors().toString());
        	return "solicitud/createOrUpdateRequestForm";
        } else {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			solicitud.setRequestDate(LocalDate.now());
			solicitud.setNewOwner(ownerService.findOwnerByUsername(username));
			solicitud.setAdoption(this.adopcionService.findAdoptionById(adoptionId).get());
        	this.solicitudService.saveSolicitud(solicitud);
        	return listSolicitudes(adoptionId, model);
        }
    }

	@GetMapping("/{adoptionId}/requests/{requestId}/accept")
    public String acceptSolicitud(@PathVariable("adoptionId") final Integer adoptionId, @PathVariable("adoptionId") final Integer requestId, final ModelMap model) {
		Solicitud solicitud = solicitudService.findBySolicitudId(requestId).get();
		model.put("request", solicitud);
        
        return "solicitud/acceptRequest";
    }

	
	@PostMapping("/{adoptionId}/requests/{requestId}/accept")
    public String confirmAcceptSolicitud(@PathVariable("adoptionId") final Integer adoptionId, @PathVariable("adoptionId") final Integer requestId, final ModelMap model) {
		Solicitud solicitud = solicitudService.findBySolicitudId(requestId).get();
		model.put("request", solicitud);

		Owner originalOwner = solicitud.getAdoption().getPet().getOwner();
		Pet pet = solicitud.getAdoption().getPet();

		originalOwner.removePet(pet);
		pet.setOwner(solicitud.getNewOwner());

		ownerService.saveOwner(originalOwner);
		ownerService.saveOwner(solicitud.getNewOwner());

		adopcionService.deleteAdopcionByIdAndSolicitudes(adoptionId);

		model.addAttribute("message", "The adoption request was accepted succesfully.");
        
        return listSolicitudes(adoptionId, model);
    }

}
