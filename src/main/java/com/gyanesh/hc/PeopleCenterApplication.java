package com.gyanesh.hc;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Spring boot main application. 
 * @author gyanesh.sharma
 *
 */
@SpringBootApplication
public class PeopleCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeopleCenterApplication.class, args);
	}

	
	@Bean 
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper(); 
		modelMapper.getConfiguration().setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
		return modelMapper; 
	}
}

