package com.gyanesh.hc.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.gyanesh.hc.common.Constants;

/**
 * This class builds the page request based on the input parameters from the
 * Rest client applications.
 * 
 * @author gyanesh.sharma
 *
 */
public class PaginationHelper {

	public static Pageable buildPageRequest(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

		Pageable pageRquest;

		if (sortBy == null) {
			pageRquest = PageRequest.of(pageNumber, pageSize);
		} else if (sortOrder == null) {
			pageRquest = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		} else {
			if (sortOrder.equals(Constants.DESCENDING)) {
				pageRquest = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
			} else {
				// By default sorting order is ascending.
				pageRquest = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
			}
		}
		return pageRquest;
	}
}
