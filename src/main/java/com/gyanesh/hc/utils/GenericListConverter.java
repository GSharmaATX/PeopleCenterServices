package com.gyanesh.hc.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

/**
 * This is a generic converter for converting a list of BE objects into a list
 * of DTO objects and vice versa.
 * 
 * @author gyanesh.sharma
 *
 */
public class GenericListConverter {
	
	public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setPreferNestedProperties(false);
		return source.stream().map(element -> modelMapper.map(element, targetClass)).collect(Collectors.toList());
	}
}
