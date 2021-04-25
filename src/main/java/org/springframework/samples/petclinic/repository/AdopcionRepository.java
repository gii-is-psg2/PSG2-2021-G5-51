package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Adopcion;

public interface AdopcionRepository extends CrudRepository<Adopcion, Integer> {

	@Override
	List<Adopcion> findAll();
}
