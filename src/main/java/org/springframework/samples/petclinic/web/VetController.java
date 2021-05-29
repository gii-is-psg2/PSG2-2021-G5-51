/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.SpecialtyService;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class VetController {

	private final VetService vetService;
	private final SpecialtyService specialtyService;
	public static final String VETs_FORM = "vets/createOrUpdateVetsForm";

	@Autowired
	public VetController(final VetService clinicService, final SpecialtyService specialtyService) {
		this.vetService = clinicService;
		this.specialtyService = specialtyService;
	}

	@ModelAttribute("specialties")
	public Collection<Specialty> showSpecialities() {
		return this.specialtyService.findSpecialties();
	}

	@GetMapping(value = { "/vets" })
	public String showVetList(final Map<String, Object> model) {
		// Here we are returning an object of type 'Vets' rather than a collection of
		// Vet objects
		// so it is simpler for Object-Xml mapping
		final Vets vets = new Vets();
		vets.getVetList().addAll(this.vetService.findVets());
		model.put("vets", vets);
		final Collection<GrantedAuthority> c = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		final boolean b = c.stream().anyMatch(x-> x.getAuthority().equals("admin"));
		model.put("isAdmin", b);
		return "vets/vetList";
	}
	
	@GetMapping(value = "vets/{vetId}/delete")
	public String deleteVet(@PathVariable final int vetId, final Map<String, Object> model) {
		this.vetService.deleteVet(vetId);
		return "redirect:/vets";
	}

	@GetMapping(value = { "/vets.xml" })
	public @ResponseBody Vets showResourcesVetList() {
		// Here we are returning an object of type 'Vets' rather than a collection of
		// Vet
		// objects
		// so it is simpler for JSon/Object mapping
		final Vets vets = new Vets();
		vets.getVetList().addAll(this.vetService.findVets());
		return vets;
	}

	@GetMapping("/vets/new")
	public String saveNewVet(final ModelMap model) {

		model.addAttribute("vet", new Vet());
		

		return VetController.VETs_FORM;
	}

	@PostMapping("/vets/new")
	public String saveNewVet(@Valid final Vet vet, final BindingResult binding,@RequestParam(value="specialties", required= false) final Collection<Specialty> specialties,  final ModelMap model) {
		try {
		if (binding.hasErrors()) {
			model.addAttribute("message", "Datos del veterinario inválidos.");
			return VetController.VETs_FORM;
		} else {
			  if(!(specialties==null)) {
				  
				  for(final Specialty s:specialties) {
						vet.addSpecialty(s);
					} 
			  }
				
			
			this.vetService.save(vet);
			model.addAttribute("message", "Nuevo veterinario creado");
			return "redirect:/" + "vets";
		
		}
		}catch(final IllegalArgumentException ex){
            this.vetService.save(vet);
            return  "vets/createOrUpdateVetForm";
		}
	}

	@GetMapping("/vets/{id}/edit")
	public String editVet(@PathVariable("id") final int id, final ModelMap model) {
		final Vet vet = this.vetService.findById(id);
		final Collection<Specialty> specialtiesOfVet = vet.getSpecialties();
		final Collection<Specialty> specialties = this.specialtyService.findSpecialties();
		final List<Specialty> specialtiesRemaining = new ArrayList<>();
		for(final Specialty s: specialties) {
			if(!specialtiesOfVet.contains(s)) {
				specialtiesRemaining.add(s);
			}
		}
		
		
		model.addAttribute("vet", vet);
		model.addAttribute("specialtiesRemaining", specialtiesRemaining);
		return VetController.VETs_FORM;
	}

	@PostMapping("/vets/{id}/edit")
	public String editVet(@PathVariable("id") final int id,@RequestParam(value="specialties", required= false) final Collection<Specialty> specialties, @Valid final Vet modifiedVet, final BindingResult binding, final ModelMap model) {
		final Vet vet = this.vetService.findById(id);
		try {

		if (binding.hasErrors()) {
			final Collection<Specialty> specialtiesOfVet = vet.getSpecialties();
			final Collection<Specialty> specialties2 = this.specialtyService.findSpecialties();
			final List<Specialty> specialtiesRemaining = new ArrayList<>();
			for(final Specialty s: specialties2) {
				if(!specialtiesOfVet.contains(s)) {
					specialtiesRemaining.add(s);
				}
			}
			model.addAttribute("specialtiesRemaining", specialtiesRemaining);
			model.addAttribute("message", "Datos del veterinario inválidos.");
			return VetController.VETs_FORM;
		} else {
			BeanUtils.copyProperties(modifiedVet, vet, "id"); 
			if(!(specialties==null)) {
			for(final Specialty s:specialties) {
				vet.addSpecialty(s);
			}
			}
			
			this.vetService.save(vet);
			model.addAttribute("message", "Datos del veterinario actualizados");
			return "redirect:/" + "vets";
		}
		
	} catch(final IllegalArgumentException ex){
        BeanUtils.copyProperties(modifiedVet, vet, "id");
        this.vetService.save(vet);
        model.addAttribute("message", "vet updated succesfully!");
        return "redirect:/" +"vets";
        }
	}

}
