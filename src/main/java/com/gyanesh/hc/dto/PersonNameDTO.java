package com.gyanesh.hc.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO class for the PersonName entity. 
 * @author gyanesh.sharma
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class PersonNameDTO extends BaseDTO<String> implements Serializable {
	private Long id;

	private LocalDate beginDate;

	private LocalDate endDate;

	private String firstName;

	private String lastName;

	private String middleName;

	private String statusCd;

	@JsonBackReference
	private PersonDTO person;

}
