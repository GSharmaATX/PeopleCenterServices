package com.gyanesh.hc.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gyanesh.hc.dto.PersonConnectionDTO;
import com.gyanesh.hc.dto.PersonNameDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO class for the Person entity. 
 * @author gyanesh.sharma
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class PersonDTO extends BaseDTO<String> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String birthCity;

	private String birthCountryCd;

	private String birthStateCd;

	private LocalDate dob;

	private String ethnicity;

	private String eyeColorCd;

	private String fatherName;

	private String hairColorCd;

	private String motherName;

	private String statusCd;

	// Source/parent connections
	private List<PersonConnectionDTO> personConnections1;

	// Related/child connections
	private List<PersonConnectionDTO> personConnections2;

	@JsonManagedReference
	private List<PersonNameDTO> personNames;


}
