package com.gyanesh.hc.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gyanesh.hc.dto.PersonDTO;
import com.gyanesh.hc.dto.PersonNameDTO;
import com.gyanesh.hc.model.Person;
import com.gyanesh.hc.model.PersonName;
import com.gyanesh.hc.repository.PersonNameRepository;
import com.gyanesh.hc.repository.PersonRepository;
import com.gyanesh.hc.utils.GenericListConverter;
import com.gyanesh.hc.utils.PaginationHelper;

/**
 * This is a business service layer class. 
 * @author gyanesh.sharma
 *
 */
@Service
public class PersonService {

	@Autowired 
	private ModelMapper modelMapper; 

	@Autowired
	private PersonNameRepository personNameRepo;

	@Autowired
	private PersonRepository personRepo;

	// Get Person names regardless of the association with the Person table.
	public Page<PersonNameDTO> getAllPersonNames(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

		Pageable pageRequest = PaginationHelper.buildPageRequest(pageNumber, pageSize, sortBy, sortOrder);

		Page<PersonName> personNamePage = personNameRepo.findAll(pageRequest);
		Page<PersonNameDTO> personNameDTOPage = personNamePage
				.map(personName -> this.modelMapper.map(personName, PersonNameDTO.class));
		return personNameDTOPage;
	}

	public Page<PersonNameDTO> getPersonNamesByFirstNameAndLastName(String firstName, String lastName, Integer pageNumber,
			Integer pageSize, String sortBy, String sortOrder) {

		Pageable pageRequest = PaginationHelper.buildPageRequest(pageNumber, pageSize, sortBy, sortOrder);

		Page<PersonName> personNamePage = personNameRepo.findByFirstNameLikeAndLastNameLike(firstName, lastName,
				pageRequest);
		Page<PersonNameDTO> personNameDTOPage = personNamePage
				.map(personName -> this.modelMapper.map(personName, PersonNameDTO.class));
		return personNameDTOPage;
	}

	public Page<PersonNameDTO> getPersonNamesByFirstName(String firstName, Integer pageNumber, Integer pageSize,
			String sortBy, String sortOrder) {

		Pageable pageRequest = PaginationHelper.buildPageRequest(pageNumber, pageSize, sortBy, sortOrder);

		Page<PersonName> personNamePage = personNameRepo.findByFirstNameLike(firstName, pageRequest);
		Page<PersonNameDTO> personNameDTOPage = personNamePage
				.map(personName -> this.modelMapper.map(personName, PersonNameDTO.class));
		return personNameDTOPage;
	}

	public Page<PersonNameDTO> getPersonNamesByLastName(String lastName, Integer pageNumber, Integer pageSize,
			String sortBy, String sortOrder) {

		Pageable pageRequest = PaginationHelper.buildPageRequest(pageNumber, pageSize, sortBy, sortOrder);

		Page<PersonName> personNamePage = personNameRepo.findByLastNameLike(lastName, pageRequest);
		Page<PersonNameDTO> personNameDTOPage = personNamePage
				.map(personName -> this.modelMapper.map(personName, PersonNameDTO.class));
		return personNameDTOPage;
	}

	public Page<PersonNameDTO> getPersonNameAndPersonByFirstName(String firstName, Integer pageNumber, Integer pageSize,
			String sortBy, String sortOrder) {

		Pageable pageRequest = PaginationHelper.buildPageRequest(pageNumber, pageSize, sortBy, sortOrder);

		Page<PersonName> personNamePage = personNameRepo.findPersonNameAndPersonByFirstNameLike(firstName, pageRequest);
		Page<PersonNameDTO> personNameDTOPage = personNamePage.map(personName -> this.modelMapper.map(personName, PersonNameDTO.class));
		return personNameDTOPage;
	}

	public Page<PersonNameDTO> getPersonNameAndPersonByLastName(String lastName, Integer pageNumber, Integer pageSize,
			String sortBy, String sortOrder) {

		Pageable pageRequest = PaginationHelper.buildPageRequest(pageNumber, pageSize, sortBy, sortOrder);

		Page<PersonName> personNamePage = personNameRepo.findPersonNameAndPersonByLastNameLike(lastName, pageRequest);
		Page<PersonNameDTO> personNameDTOPage = personNamePage.map(personName -> this.modelMapper.map(personName, PersonNameDTO.class));
		return personNameDTOPage;
	}

	public Page<PersonNameDTO> getPersonNameAndPersonByFirstNameAndLastName(String firstName, String lastName,
			Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

		Pageable pageRequest = PaginationHelper.buildPageRequest(pageNumber, pageSize, sortBy, sortOrder);

		Page<PersonName> personNamePage = personNameRepo.findPersonNameAndPersonByFirstNameLikeAndLastNameLike(firstName,
				lastName, pageRequest);
		Page<PersonNameDTO> personNameDTOPage = personNamePage.map(personName -> this.modelMapper.map(personName, PersonNameDTO.class));
		return personNameDTOPage;
	}

	public Page<PersonNameDTO> getAllPersonNameAndPerson(Integer pageNumber, Integer pageSize, String sortBy,
			String sortOrder) {

		Pageable pageRequest = PaginationHelper.buildPageRequest(pageNumber, pageSize, sortBy, sortOrder);

		Page<PersonName> personNamePage = personNameRepo.findAllPersonNameAndPerson(pageRequest);
		Page<PersonNameDTO> personNameDTOPage = personNamePage
				.map(personName -> this.modelMapper.map(personName, PersonNameDTO.class));
		return personNameDTOPage;
	}

	// Get Person by Person name information
	public Page<PersonDTO> getPersonByFirstName(String firstName, Integer pageNumber, Integer pageSize, String sortBy,
			String sortOrder) {

		Pageable pageRequest = PaginationHelper.buildPageRequest(pageNumber, pageSize, sortBy, sortOrder);

		Page<Person> personPage = personRepo.findPersonByFirstNameLike(firstName, pageRequest);
		Page<PersonDTO> personDTOPage = personPage.map(person -> this.modelMapper.map(person, PersonDTO.class));
		return personDTOPage;
	}

	public Page<PersonDTO> getPersonByLastName(String lastName, Integer pageNumber, Integer pageSize, String sortBy,
			String sortOrder) {

		Pageable pageRequest = PaginationHelper.buildPageRequest(pageNumber, pageSize, sortBy, sortOrder);

		Page<Person> personPage = personRepo.findPersonByLastNameLike(lastName, pageRequest);
		Page<PersonDTO> personDTOPage = personPage.map(person -> this.modelMapper.map(person, PersonDTO.class));
		return personDTOPage;
	}

	public Page<PersonDTO> getPersonByFirstNameAndLastName(String firstName, String lastName, Integer pageNumber,
			Integer pageSize, String sortBy, String sortOrder) {

		Pageable pageRequest = PaginationHelper.buildPageRequest(pageNumber, pageSize, sortBy, sortOrder);

		Page<Person> personPage = personRepo.findPersonByFirstNameLikeAndLastNameLike(firstName, lastName, pageRequest);
		Page<PersonDTO> personDTOPage = personPage.map(person -> this.modelMapper.map(person, PersonDTO.class));
		return personDTOPage;
	}

	public Page<PersonDTO> getAllPerson(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

		Pageable pageRequest = PaginationHelper.buildPageRequest(pageNumber, pageSize, sortBy, sortOrder);

		Page<Person> personPage = personRepo.findAll(pageRequest);
		Page<PersonDTO> personDTOPage = personPage.map(person -> this.modelMapper.map(person, PersonDTO.class));
		return personDTOPage;
	}

	public PersonDTO createPerson(PersonDTO personDTO) {

		Person person = this.modelMapper.map(personDTO, Person.class);
		person = personRepo.save(person);
		// The above save creates Person and PersonNames due to CascadeType.ALL setting. 
		return modelMapper.map(person, PersonDTO.class);
	}


	public PersonDTO updatePerson(PersonDTO personDTO) {

		// Currently there is no difference in the code for create and update methods.
		Person person = this.modelMapper.map(personDTO, Person.class);
		person = personRepo.save(person);
		// The above save creates Person and PersonNames due to CascadeType.ALL setting. 
		return modelMapper.map(person, PersonDTO.class);
	}

	public PersonDTO getPersonById(Long personId) {
		Person person = personRepo.getById(personId);
		if (person != null) {
			return modelMapper.map(person, PersonDTO.class);
					
		} else return null; 
	}

	public PersonDTO getPersonByPersonNameId(Long PersonNameId) {
		Person person = personRepo.findPersonByPersonNameId(PersonNameId);
		if (person != null) {
			return modelMapper.map(person, PersonDTO.class);
					
		} else return null;
		
	}
	
	public void deletePerson(Long personId) {
		// Any additional business logic could be added here. 
		personRepo.deleteById(personId);
	}
}
