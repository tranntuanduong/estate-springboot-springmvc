package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.TransactionRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CustomerConverter customerConverter;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	
	
	@Override
	public List<CustomerDTO> findAll(CustomerSearchBuilder builder, Pageable pageable) {
		List<CustomerEntity> customerEntities = customerRepository.findAll(builder, pageable);
		List<CustomerDTO> results = new ArrayList<>();
		for(CustomerEntity customerEntity : customerEntities) {		
			CustomerDTO customerDTO = customerConverter.convertToDTO(customerEntity);
			StringBuilder staffList = new StringBuilder();
			if(customerEntity.getStaffs() != null) {
				for(UserEntity user : customerEntity.getStaffs()) {
					if(staffList.length() > 0) {			
						staffList.append(","+user.getFullName());
					} else {
						staffList.append(user.getFullName());
					}
				}
			}
			customerDTO.setStaffInCharge(staffList.toString());
			results.add(customerDTO);
		}
		return results;
	}

	@Override
	@Transactional
	public void chooseStaffInCharge(CustomerDTO newCustomerDTO) {
		CustomerEntity customer = customerRepository.findOne(newCustomerDTO.getId());
		//set staffs
		List<UserEntity> staffs = new ArrayList<>();
		for(Long id : newCustomerDTO.getIds()) {
			UserEntity userEntity = userRepository.findOne(id);
			staffs.add(userEntity);
		}
		customer.setStaffs(staffs);
		customer = customerRepository.save(customer);
	}

	@Override
	@Transactional
	public CustomerDTO findById(Long id) {
		CustomerEntity customerEntity = customerRepository.findOne(id);
		CustomerDTO customerDTO = customerConverter.convertToDTO(customerEntity);
//		if(customerEntity.getUser() != null) {
//			customerDTO.setUserId(customerEntity.getUser().getId());
//		}
		//sort by transactionId
		Collections.sort(customerDTO.getTransactions(), new CompareTransaction());
		return customerDTO;
	}

	@Override
	@Transactional
	public CustomerDTO update(CustomerDTO model) {
		CustomerEntity oldCustomer = customerRepository.findOne(model.getId());
		CustomerEntity newCustomer = customerConverter.convertToEntity(model);
		newCustomer.setCreatedBy(oldCustomer.getCreatedBy());
		newCustomer.setCreatedDate(oldCustomer.getCreatedDate());
		newCustomer.setModifiedBy(oldCustomer.getModifiedBy());
		newCustomer.setModifiedDate(oldCustomer.getModifiedDate());
		//staffs
		List<UserEntity> staffs = new ArrayList<>();
		for(Long id : model.getIds()) {
			UserEntity staff = userRepository.findOne(id);
			staffs.add(staff);
		}
		newCustomer.setStaffs(staffs);
		newCustomer = customerRepository.save(newCustomer);
		return customerConverter.convertToDTO(newCustomer);
	}

	//sort by transactionId
	public static class CompareTransaction implements Comparator<TransactionDTO> {
		@Override
		public int compare(TransactionDTO tran1, TransactionDTO tran2) {
			return tran1.getId().compareTo(tran2.getId());
		}
	}

	@Override
	@Transactional
	public CustomerDTO save(CustomerDTO customerDTO) {
		CustomerEntity customerEntity = customerConverter.convertToEntity(customerDTO);
		//tam thoi de the nay
		customerEntity.setCreatedBy("duong dep zai");
		customerEntity.setCreatedDate(new Date());
		//staffs
		List<UserEntity> staffs = new ArrayList<>();
		for(Long id : customerDTO.getIds()) {
			UserEntity userEntity = userRepository.findOne(id);
			staffs.add(userEntity);
		}
		customerEntity.setStaffs(staffs);
		customerEntity = customerRepository.save(customerEntity);
		return customerConverter.convertToDTO(customerEntity);
	}

	@Override
	@Transactional //remember
	public void delete(Long[] ids) {	
		for(Long id : ids) {
			CustomerEntity customerEntity = customerRepository.findOne(id);
			//delete customer_staff
			for(UserEntity staff : customerEntity.getStaffs()) {
				staff.getCustomers().remove(customerEntity);
			}
			//delete transaction
			transactionRepository.deleteByCustomerId(id);
			//delete customer
			customerRepository.delete(customerEntity);
		}
	}

	@Override
	public int count(CustomerSearchBuilder builder) {
		return customerRepository.count(builder).intValue();
	}

}
