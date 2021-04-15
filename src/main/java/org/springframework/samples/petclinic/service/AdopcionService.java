package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Adopcion;
import org.springframework.samples.petclinic.repository.AdopcionRepository;
import org.springframework.stereotype.Service;

@Service
public class AdopcionService {

	private final AdopcionRepository AdopcionRepository;
	
	@Autowired
	public AdopcionService(final AdopcionRepository AdopcionRepository) {
		this.AdopcionRepository = AdopcionRepository;
	}
	
	
	public List<Adopcion> findAll() {
		return this.AdopcionRepository.findAll();
	}


	public void saveAdopcion(final Adopcion Adopcion) {
		this.AdopcionRepository.save(Adopcion);
	}
	
	public void deleteByAdopcionId(final Integer id) {
		this.AdopcionRepository.deleteById(id);
	}

}
