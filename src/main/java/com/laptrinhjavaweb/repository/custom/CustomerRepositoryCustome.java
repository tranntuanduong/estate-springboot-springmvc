package com.laptrinhjavaweb.repository.custom;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.entity.CustomerEntity;

public interface CustomerRepositoryCustome {
	List<CustomerEntity> findAll(CustomerSearchBuilder builder, Pageable pageable);
	Long count(CustomerSearchBuilder builder);
}
