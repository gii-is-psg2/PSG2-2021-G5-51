package org.springframework.samples.petclinic.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Causa;
import org.springframework.samples.petclinic.service.CausaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/causas")
public class CausaController {
	
	
	private CausaService causaService;
	
	
	@Autowired
	public CausaController(CausaService causaService) {
		this.causaService = causaService;
	}
	
	

	@GetMapping("/list")
	public String listadoCausas(ModelMap model) {
		List<Causa> causas = causaService.findAll();
		model.addAttribute("causas", causas);
		return "causas/causasList";
	}
	
	
	@GetMapping("/new")
    public String addNewCausa(ModelMap model) {
        model.addAttribute("causa",new Causa());
        return "causas/createCausaForm";
    }
	
	
	@PostMapping("/new")
    public String saveNewCausa(@Valid Causa causa, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
        	model.addAttribute("causa", causa);
        	return "causas/createCausaForm";
        } else {
        	causaService.saveCausa(causa);
        	return listadoCausas(model);
        }
    }

}
