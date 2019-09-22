package com.laptrinhjavaweb.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.dto.CustomerDTO;

public interface ICustomerService {
	List<CustomerDTO> findAll(CustomerSearchBuilder builder, Pageable pageable);
	void chooseStaffInCharge(CustomerDTO newCustomerDTO);
	CustomerDTO findById(Long id);
	CustomerDTO update(CustomerDTO newCustomer);
	CustomerDTO save(CustomerDTO customerDTO);
	void delete(Long[] ids);
	int count(CustomerSearchBuilder builder);
}
