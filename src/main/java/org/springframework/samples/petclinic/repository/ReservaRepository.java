package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Reserva;

public interface ReservaRepository extends CrudRepository<Reserva, Integer> {

	@Query("SELECT r FROM Reserva r WHERE r.pet.id = ?1")
	List<Reserva> findReservasByPetId(Integer petId);

	Collection<Reserva> findAll();

    Optional<Reserva> findById(int id);
    
}
