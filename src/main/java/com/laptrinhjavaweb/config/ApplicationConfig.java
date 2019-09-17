package com.laptrinhjavaweb.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "com.laptrinhjavaweb")
public class ApplicationConfig {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
