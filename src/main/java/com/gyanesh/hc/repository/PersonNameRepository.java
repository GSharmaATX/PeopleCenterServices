package com.gyanesh.hc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gyanesh.hc.model.PersonName;

/**
 * The methods in this repository returns PersonName based on the input criteria. 
 * @author gyanesh.sharma
 *
 */
@Repository
public interface PersonNameRepository extends JpaRepository<PersonName, Long> {

	// Note: JPQL is used because automatic queries didn't work with LIKE expressions. 
	@Query("SELECT pn FROM PersonName pn WHERE pn.firstName LIKE %?1% AND pn.lastName LIKE %?2%")
	public Page<PersonName> findByFirstNameLikeAndLastNameLike(String firstName, String lastName, Pageable page);
	@Query("SELECT pn FROM PersonName pn WHERE pn.firstName LIKE %?1%")
	public Page<PersonName> findByFirstNameLike(String firstName, Pageable page);
	@Query("SELECT pn FROM PersonName pn WHERE pn.lastName LIKE %?1%")
	public Page<PersonName> findByLastNameLike(String lastName, Pageable page);
	
	// Get Person name joined with Person by last name using like expression 
	@Query("SELECT pn FROM PersonName pn JOIN pn.person p WHERE pn.lastName LIKE %?1%")
	public Page<PersonName> findPersonNameAndPersonByLastNameLike(String lastName, Pageable page);
	
	// Get Person name joined with Person by first name using like expression 
	@Query("SELECT pn FROM PersonName pn JOIN pn.person p WHERE pn.firstName LIKE %?1%")
	public Page<PersonName> findPersonNameAndPersonByFirstNameLike(String firstName, Pageable page);

	// Get Person name joined with Person by first and last name using like expression
	@Query("SELECT pn FROM PersonName pn JOIN pn.person p WHERE pn.firstName LIKE %?1% AND pn.lastName LIKE %?2%")
	public Page<PersonName> findPersonNameAndPersonByFirstNameLikeAndLastNameLike(String firstName, String lastName, Pageable page);

	// Get Person name joined with Person
	@Query("SELECT pn FROM PersonName pn JOIN pn.person p")
	public Page<PersonName> findAllPersonNameAndPerson(Pageable page);

}
