package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;

@Component
public class UserConverter {
	public UserDTO convertToDTO(UserEntity userEntity) {
		ModelMapper modelMapper = new ModelMapper();
		UserDTO result = modelMapper.map(userEntity, UserDTO.class);
		return result;
	}
	
	public UserEntity convertToEntity(UserDTO userDTO) {
		ModelMapper modelMapper = new ModelMapper();
		UserEntity result = modelMapper.map(userDTO, UserEntity.class);
		
		return result;
	}
}
