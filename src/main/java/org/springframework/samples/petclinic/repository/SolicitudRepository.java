package org.springframework.samples.petclinic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Solicitud;

public interface SolicitudRepository extends CrudRepository<Solicitud, Integer> {

	@Override
	List<Solicitud> findAll();

	@Override
	Optional<Solicitud> findById(Integer id);

	@Query("SELECT s FROM Solicitud s WHERE s.adoption.id = ?1")
	List<Solicitud> findByAdoptionId(Integer id);
}
