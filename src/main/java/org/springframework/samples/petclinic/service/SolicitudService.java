package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Solicitud;
import org.springframework.samples.petclinic.repository.SolicitudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SolicitudService {

	private final SolicitudRepository SolicitudRepository;
	
	@Autowired
	public SolicitudService(final SolicitudRepository SolicitudRepository) {
		this.SolicitudRepository = SolicitudRepository;
	}
	
	
	public List<Solicitud> findAll() {
		return this.SolicitudRepository.findAll();
	}

	@Transactional(readOnly = true)
	public List<Solicitud> findByAdoptionId(Integer id) {
		return this.SolicitudRepository.findByAdoptionId(id);
	}

	@Transactional(readOnly = true)
	public Optional<Solicitud> findBySolicitudId(Integer id) {
		return this.SolicitudRepository.findById(id);
	}

	public void saveSolicitud(final Solicitud Solicitud) {
		this.SolicitudRepository.save(Solicitud);
	}
	
	public void deleteBySolicitudId(final Integer id) {
		this.SolicitudRepository.deleteById(id);
	}
}
