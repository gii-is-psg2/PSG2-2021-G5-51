package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.User;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends CrudRepository<User, String>{
	
	Collection<User> findAll() throws DataAccessException;
	
	@Query("SELECT user FROM User user WHERE user.username =:username")
	User findUser(@Param ("username")String username);
	
//	@Transactional
//    @Modifying
//    @Query("DELETE FROM User user where user.username=:username")
//    void delete(@Param(value = "username") String username);
	
}
