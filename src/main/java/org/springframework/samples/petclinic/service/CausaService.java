package org.springframework.samples.petclinic.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Causa;
import org.springframework.samples.petclinic.repository.CausaRepository;
import org.springframework.stereotype.Service;

@Service
public class CausaService {

	private CausaRepository causaRepository;
	
	@Autowired
	public CausaService(CausaRepository causaRepository) {
		this.causaRepository = causaRepository;
	}
	
	
	public List<Causa> findAll() {
		return this.causaRepository.findAll();
	}


	public void saveCausa(Causa causa) {
		this.causaRepository.save(causa);
	}

}
