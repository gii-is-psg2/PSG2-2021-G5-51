package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Causa;

public interface CausaRepository extends CrudRepository<Causa, Integer> {

	List<Causa> findAll();
}
