package com.gyanesh.hc.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author gyanesh.sharma
 * This abstract class have common fields and methods used by all DTO classes. 
 * @param <U>
 */
public abstract class BaseDTO<U> {

	private U createdBy;
	private LocalDateTime createdDate;
	private U lastModifiedBy;
	private LocalDateTime lastModifiedDate;
	private Integer version;

	public U getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(U createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public U getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(U lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}

}
