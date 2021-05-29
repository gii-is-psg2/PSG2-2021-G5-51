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

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Adopcion;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.AdopcionService;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class OwnerController {

	private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

	private final OwnerService ownerService;
	private final AdopcionService adopcionService;

	@Autowired
	public OwnerController(final OwnerService ownerService, final UserService userService,final AdopcionService adopcionService, final AuthoritiesService authoritiesService) {
		this.ownerService = ownerService;
		this.adopcionService = adopcionService;
	}

	@InitBinder
	public void setAllowedFields(final WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	
	public Boolean isAdmin() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<Owner> owner = Optional.ofNullable((this.ownerService.findOwnerByUsername(username)));
		if (!owner.isPresent()) { //es admin
			return true;
		} else { //no es admin, es owner
			return false;
		}
	}
	
	
	public Boolean accedeASuOwner(Integer ownerId) {
		Optional<Owner> ownerBuscado = Optional.ofNullable(this.ownerService.findOwnerById(ownerId));
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<Owner> ownerActual = Optional.ofNullable(this.ownerService.findOwnerByUsername(username));
		
		if(ownerBuscado.isPresent() && ownerActual.isPresent() && ownerBuscado.equals(ownerActual)) {
			return true;
		} else {
			return false;
		}
	}
	

	@GetMapping(value = "/owners/new")
	public String initCreationForm(final Map<String, Object> model) {
		final Owner owner = new Owner();
		model.put("owner", owner);
		return OwnerController.VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/owners/new")
	public String processCreationForm(@Valid final Owner owner, final BindingResult result) {
		if (result.hasErrors()) {
			return OwnerController.VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating owner, user and authorities
			this.ownerService.saveOwner(owner);
			
			return "redirect:/owners/" + owner.getId();
		}
	}

	@GetMapping(value = "/owners/find")
	public String initFindForm(final Map<String, Object> model) {
		if (this.isAdmin()) {
			model.put("owner", new Owner());
			return "owners/findOwners";
		} else {
			return "exception";
		}
		
	}

	@GetMapping(value = "/owners")
	public String processFindForm(Owner owner, final BindingResult result, final Map<String, Object> model) {
		if(this.isAdmin()) {
			
			// allow parameterless GET request for /owners to return all records
			if (owner.getLastName() == null) {
				owner.setLastName(""); // empty string signifies broadest possible search
			}

			// find owners by last name
			final Collection<Owner> results = this.ownerService.findOwnerByLastName(owner.getLastName());
			if (results.isEmpty()) {
				// no owners found
				result.rejectValue("lastName", "notFound", "not found");
				return "owners/findOwners";
			}
			else if (results.size() == 1) {
				// 1 owner found
				owner = results.iterator().next();
				return "redirect:/owners/" + owner.getId();
			}
			else {
				// multiple owners found
				model.put("selections", results);
				return "owners/ownersList";
			}
			
		} else {
			return "exception";
		}
		
	}

	@GetMapping(value = "/owners/{ownerId}/edit")
	public String initUpdateOwnerForm(@PathVariable("ownerId") final int ownerId, final Model model) {
		if(this.isAdmin() || this.accedeASuOwner(ownerId)) {
			final Owner owner = this.ownerService.findOwnerById(ownerId);
			model.addAttribute(owner);
			return OwnerController.VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		} else {
			return "exception";
		}
		
	}

	@PostMapping(value = "/owners/{ownerId}/edit")
	public String processUpdateOwnerForm(@Valid final Owner owner, final BindingResult result,
			@PathVariable("ownerId") final int ownerId) {
		if(this.isAdmin() || this.accedeASuOwner(ownerId)) {
			if (result.hasErrors()) {
				return OwnerController.VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
			}
			else {
				owner.setId(ownerId);
				this.ownerService.saveOwner(owner);
				return "redirect:/owners/{ownerId}";
			}
		} else {
			return "exception";
		}
	}

	/**
	 * Custom handler for displaying an owner.
	 * @param ownerId the ID of the owner to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@GetMapping("/owners/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") final int ownerId) {
		if (this.isAdmin() || this.accedeASuOwner(ownerId)) {
			final ModelAndView mav = new ModelAndView("owners/ownerDetails");
			mav.addObject(this.ownerService.findOwnerById(ownerId));
			final List<Adopcion> la = this.adopcionService.findAll();
			mav.addObject("la", la);
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			mav.addObject("username", username);
			return mav;
		} else {
			return new ModelAndView("exception");
		}
		
	}
	
	
	@GetMapping("/owners/myowner")
	public ModelAndView showMyOwner() {
		final ModelAndView mav = new ModelAndView("owners/ownerDetails");
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		mav.addObject(this.ownerService.findOwnerByUsername(username));
		final List<Adopcion> la = this.adopcionService.findAll();
		mav.addObject("la", la);
		mav.addObject("username", username);
		return mav;
	}
	
	
	
	
	@GetMapping(value = "/owners/{ownerId}/delete")
	public String delete(@PathVariable("ownerId") final int ownerId, final ModelMap model) {
		final Owner owner = this.ownerService.findOwnerById(ownerId);
 		
		if(this.isAdmin()) {
			try {
	 			System.out.println(owner);
	 			this.ownerService.delete(owner);
	 			model.addAttribute("message", "Owner deleted successfully!");
	 		}catch(final DataAccessException e) {
				model.addAttribute("message", "The owner could not be removed");
	 		}
	 		return this.initFindForm(model);
		} else {
			return "exception";
		}
		
	}

}
