package com.laptrinhjavaweb.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.TransactionConverter;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.TransactionRepository;
import com.laptrinhjavaweb.service.ITransactionService;

@Service
public class TransactionService implements ITransactionService{
	@Autowired
	private TransactionConverter transactionConverter;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private TransactionRepository transactionRepository;
		
	@Override
	public void save(TransactionDTO model) {
		TransactionEntity transactionEntity = transactionConverter.convertToEntity(model);
		transactionEntity.setCreatedDate(new Date());
		transactionEntity.setCreatedBy("de tam the nay");
		CustomerEntity customerEntity = customerRepository.findOne(model.getCustomerId());
		transactionEntity.setCustomer(customerEntity);
		transactionRepository.save(transactionEntity);
	}

}
