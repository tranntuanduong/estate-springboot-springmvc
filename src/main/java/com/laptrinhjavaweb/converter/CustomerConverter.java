package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;

@Component
public class CustomerConverter {
	public CustomerDTO convertToDTO(CustomerEntity customerEntity) {
		ModelMapper modelMapper = new ModelMapper();
		CustomerDTO result = modelMapper.map(customerEntity, CustomerDTO.class);
		return result;
	}
	
	public CustomerEntity convertToEntity(CustomerDTO customerDTO) {
		ModelMapper modelMapper = new ModelMapper();
		CustomerEntity result = modelMapper.map(customerDTO, CustomerEntity.class);
		
		return result;
	}
}
