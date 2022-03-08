package com.gyanesh.hc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gyanesh.hc.dto.PersonDTO;
import com.gyanesh.hc.dto.PersonNameDTO;
import com.gyanesh.hc.service.PersonService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * This REST API controller class exposes Rest end points for CRUD operation on
 * Person and PersonName.
 * 
 * @author gyanesh.sharma
 *
 */
@RestController
@RequestMapping("/")
@CrossOrigin("http://localhost:3000")
public class PeopleCenterController {

	@Autowired
	PersonService personService;

	@GetMapping(value = "person/person-names", produces = "application/json")
	@Operation(summary = "get person names", description = "Get person names by search criteria")
	public ResponseEntity<Page<PersonNameDTO>> getPersonClaimedPersonNameByName(
			@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName,
			@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
			@RequestParam(required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(required = false) String sortBy, @RequestParam(required = false) String sortOrder) {

		Page<PersonNameDTO> response;

		if ((firstName != null) && (lastName != null)) {
			response = personService.getPersonNameAndPersonByFirstNameAndLastName(firstName, lastName, pageNumber,
					pageSize, sortBy, sortOrder);

		} else if (firstName != null) {
			response = personService.getPersonNameAndPersonByFirstName(firstName, pageNumber, pageSize, sortBy,
					sortOrder);

		} else if (lastName != null) {
			response = personService.getPersonNameAndPersonByLastName(lastName, pageNumber, pageSize, sortBy,
					sortOrder);

		} else {
			response = personService.getAllPersonNameAndPerson(pageNumber, pageSize, sortBy, sortOrder);
		}

		return new ResponseEntity<Page<PersonNameDTO>>((response), HttpStatus.OK);

	}

	@GetMapping(value = "person/person-details", produces = "application/json")
	@Operation(summary = "get person detail by name", description = "Get person detail by name")
	public ResponseEntity<Page<PersonDTO>> getPersonDetail(@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName,
			@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
			@RequestParam(required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(required = false) String sortBy, @RequestParam(required = false) String sortOrder) {

		Page<PersonDTO> response;

		if ((firstName != null) && (lastName != null)) {
			response = personService.getPersonByFirstNameAndLastName(firstName, lastName, pageNumber, pageSize, sortBy,
					sortOrder);

		} else if (firstName != null) {
			response = personService.getPersonByFirstName(firstName, pageNumber, pageSize, sortBy, sortOrder);

		} else if (lastName != null) {
			response = personService.getPersonByLastName(lastName, pageNumber, pageSize, sortBy, sortOrder);

		} else {
			response = personService.getAllPerson(pageNumber, pageSize, sortBy, sortOrder);
		}

		return new ResponseEntity<Page<PersonDTO>>((response), HttpStatus.OK);

	}

	@PostMapping(value = "person/person-detail", consumes = "application/json")
	public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO personDTO) {
		PersonDTO response = personService.createPerson(personDTO);
		return new ResponseEntity<PersonDTO>(response, HttpStatus.CREATED);
	}

	@PutMapping(value = "person/{personId}/person-detail", consumes = "application/json")
	public ResponseEntity<PersonDTO> updatePerson(@PathVariable("personId") Long personId,
			@RequestBody PersonDTO personDTO) {
		if (personId.equals(personDTO.getId())) {
			PersonDTO response = personService.updatePerson(personDTO);
			return new ResponseEntity<PersonDTO>(response, HttpStatus.CREATED);

		} else {
			return new ResponseEntity<PersonDTO>(personDTO, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping(value = "person/{personId}/person-detail", produces = "application/json")
	public ResponseEntity<String> deletePerson(@PathVariable("personId") Long personId) {

		personService.deletePerson(personId);
		return new ResponseEntity<String>("Data deleted successfully", HttpStatus.OK);

	}

	@GetMapping(value = "person/{personId}/person-detail", produces = "application/json")
	public ResponseEntity<PersonDTO> getPerson(@PathVariable("personId") Long personId) {
		PersonDTO response = personService.getPersonById(personId);
		if (response.getId() != null) {
			return new ResponseEntity<PersonDTO>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<PersonDTO>(new PersonDTO(), HttpStatus.NOT_FOUND);

		}
	}

	@GetMapping(value = "person/person-detail", produces = "application/json")
	public ResponseEntity<PersonDTO> getPersonByPersonNameId(@RequestParam("personNameId") Long personNameId) {
		PersonDTO response = personService.getPersonByPersonNameId(personNameId);
		if ((response != null) && (response.getId() != null)) {
			return new ResponseEntity<PersonDTO>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<PersonDTO>(new PersonDTO(), HttpStatus.NOT_FOUND);

		}
	}

}
