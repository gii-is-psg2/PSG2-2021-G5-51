package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Specialty;

public interface SpecialtyRepository extends CrudRepository<Specialty, Integer>{
	
	Collection<Specialty> findAll() throws DataAccessException;
	
	Specialty findById(int id);

}
