package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.repository.SpecialtyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SpecialtyService {
	
	private SpecialtyRepository specialtyRepository;


	@Autowired
	public SpecialtyService(SpecialtyRepository specialtyRepository) {
		this.specialtyRepository = specialtyRepository;
	}		

	
	
	@Transactional(readOnly = true)	
	public Collection<Specialty> findSpecialties() throws DataAccessException {
		return specialtyRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Specialty findById(int id) throws DataAccessException {
		return specialtyRepository.findById(id);
	}
}
