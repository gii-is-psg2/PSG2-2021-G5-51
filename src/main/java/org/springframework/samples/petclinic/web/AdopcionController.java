package org.springframework.samples.petclinic.web;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Adopcion;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.AdopcionService;
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
public class AdopcionController {
	
	@Autowired
	private AdopcionService adopcionService;
	@Autowired
	private PetService petService;
	
	@GetMapping("")
	public String listAdopciones(ModelMap model) {
		List<Adopcion> adoptions = adopcionService.findAll();
		model.put("adoptions", adoptions);
		return "adopcion/adoptionsList";
	}
	
	
	@GetMapping("/pet/{petId}/new")
    public String addNewAdopcion(@PathVariable("petId") final Integer petId,final ModelMap model) {
       
		final Pet pet = this.petService.findPetById(petId);
		final Adopcion adop = new Adopcion();
		adop.setPet(pet);
		model.addAttribute("adopcion",adop);
        
        return "adopcion/createEditAdoptionForm";
    }
	
	
	@PostMapping("/pet/{petId}/new")
    public String saveNewAdopcion(@Valid final Adopcion adopcion, final BindingResult result, final ModelMap model) {
        if (result.hasErrors()) {
        	model.addAttribute("adopcion", adopcion);
        	return "adopcion/createEditAdoptionForm";
        } else {
        	this.adopcionService.saveAdopcion(adopcion);
        	final Integer num = adopcion.getPet().getOwner().getId();
        	return "redirect:/owners/"+Integer.toString(num);
        }
    }

}
