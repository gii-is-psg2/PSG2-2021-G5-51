package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Adopcion;
import org.springframework.samples.petclinic.model.Solicitud;
import org.springframework.samples.petclinic.repository.AdopcionRepository;
import org.springframework.samples.petclinic.repository.SolicitudRepository;
import org.springframework.stereotype.Service;

@Service
public class AdopcionService {

	private final AdopcionRepository AdopcionRepository;
	private final SolicitudRepository SolicitudRepository;
	
	@Autowired
	public AdopcionService(final AdopcionRepository AdopcionRepository, final SolicitudRepository SolicitudRepository) {
		this.AdopcionRepository = AdopcionRepository;
		this.SolicitudRepository = SolicitudRepository;
	}

	public Optional<Adopcion> findAdoptionById(final Integer id) {
		return this.AdopcionRepository.findById(id);
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

	public void deleteAdopcionByIdAndSolicitudes(final Integer id) {
		final List<Solicitud> solicitudes = this.SolicitudRepository.findByAdoptionId(id);
		if (!solicitudes.isEmpty()) {
			for (final Solicitud solicitud: solicitudes) {
				this.SolicitudRepository.deleteById(solicitud.getId());
			}
		}
		this.deleteByAdopcionId(id);
	}

}
