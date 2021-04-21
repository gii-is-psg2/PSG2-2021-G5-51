package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Donacion;
import org.springframework.samples.petclinic.repository.DonacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DonacionService {

	
	private DonacionRepository donacionRepository;
	
	@Autowired
	public DonacionService(DonacionRepository donacionRepository) {
		this.donacionRepository = donacionRepository;
	}
	
	
	public List<Donacion> findAll() {
		return this.donacionRepository.findAll();
	}


	public void saveDonacion(Donacion donacion) {
		this.donacionRepository.save(donacion);
	}
	
	@Transactional(readOnly = true)
	public Donacion findDonacionById(final int id) throws DataAccessException {
		return this.donacionRepository.findById(id);
	}
	
	
}
