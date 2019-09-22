package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.entity.TransactionEntity;

@Component
public class TransactionConverter {
	public TransactionDTO convertToDTO(TransactionEntity transactionEntity) {
		ModelMapper modelMapper = new ModelMapper();
		TransactionDTO result = modelMapper.map(transactionEntity, TransactionDTO.class);
		return result;
	}
	
	public TransactionEntity convertToEntity(TransactionDTO transactionDTO) {
		ModelMapper modelMapper = new ModelMapper();
		TransactionEntity result = modelMapper.map(transactionDTO, TransactionEntity.class);
		
		return result;
	}
}
