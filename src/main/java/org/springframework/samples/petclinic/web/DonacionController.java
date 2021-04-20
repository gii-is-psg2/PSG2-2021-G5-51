package org.springframework.samples.petclinic.web;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Causa;
import org.springframework.samples.petclinic.model.Donacion;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.CausaService;
import org.springframework.samples.petclinic.service.DonacionService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/donaciones")
public class DonacionController {
	
	private CausaService causaService;
	private DonacionService donacionService;
	private UserService userService;

	@Autowired
	public DonacionController(CausaService causaService, DonacionService donacionService, UserService userService) {
		this.causaService = causaService;
		this.donacionService = donacionService;
		this.userService = userService;
	}
	
	
	
	@GetMapping("/{causaId}/donate")
	public String createDonation(ModelMap model, @PathVariable("causaId") int causaId) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User usuario = userService.findUser(username);
		model.addAttribute("donacion", new Donacion());
		model.addAttribute("causa", causaService.findCauseById(causaId));
		model.addAttribute("usuario", usuario);
		return "/donacion/createDonation";

	}

	@PostMapping("/{causaId}/donate")
	public String createDonation(@PathVariable("causaId") int causaId, @Valid Donacion donacion, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("donacion", donacion);
			model.addAttribute("causa", causaService.findCauseById(causaId));
			return "donacion/createDonation";
		} else {
			Causa causa = causaService.findCauseById(causaId);
			Set<Donacion> donaciones = causa.getDonaciones();
			donaciones.add(donacion);
			causa.setDonaciones(donaciones);
			causa.checkBudget(); 
        	causaService.saveCausa(causa);
        	donacionService.saveDonacion(donacion);
			return "redirect:/causas/list";
		}
	}

}
