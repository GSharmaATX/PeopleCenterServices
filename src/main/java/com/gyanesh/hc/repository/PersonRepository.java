package com.gyanesh.hc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gyanesh.hc.model.Person;
import com.gyanesh.hc.model.PersonName;

/**
 * Methods in this interface return Person object based on the input criteria. 
 * @author gyanesh.sharma
 *
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	@Query("SELECT p FROM PersonName pn JOIN pn.person p WHERE pn.firstName LIKE %?1%")
	Page<Person> findPersonByFirstNameLike(String firstName, Pageable pageRequest);

	@Query("SELECT p FROM PersonName pn JOIN pn.person p WHERE pn.lastName LIKE %?1%")
	Page<Person> findPersonByLastNameLike(String lastName, Pageable pageRequest);

	@Query("SELECT p FROM PersonName pn JOIN pn.person p WHERE pn.firstName LIKE %?1% AND pn.lastName LIKE %?2%")
	Page<Person> findPersonByFirstNameLikeAndLastNameLike(String firstName, String lastName, Pageable pageRequest);

	@Query("SELECT p FROM PersonName pn JOIN pn.person p")
	Page<Person> findAll(Pageable pageRequest);
	
	@Query("SELECT p FROM PersonName pn JOIN pn.person p WHERE pn.id = ?1")
	Person findPersonByPersonNameId(Long personNameId);

}
