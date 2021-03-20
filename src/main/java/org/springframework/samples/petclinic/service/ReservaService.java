package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservaService {
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	
	@Transactional(readOnly = true)
	public List<Reserva> findAll() {
		return (List<Reserva>) reservaRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Reserva> findReservasByPetId(Integer id) {
		return reservaRepository.findReservasByPetId(id);
	}
	
	
	@Transactional(readOnly = true)
	public Optional<Reserva> findReservaById(Integer id) {
		return reservaRepository.findById(id);
	}
	
	@Transactional
	public void delete(Reserva r) {
		reservaRepository.delete(r);
	}

}
