package com.gyanesh.hc.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO class for the PersonConnection entity. 
 * @author gyanesh.sharma
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class PersonConnectionDTO extends BaseDTO<String> implements Serializable {

	private Long id;

	private LocalDate beginDate;

	private String connectionTypeCd;

	private LocalDate endDate;

	private String statusCd;

	private PersonDTO person1;

	private PersonDTO person2;

}
