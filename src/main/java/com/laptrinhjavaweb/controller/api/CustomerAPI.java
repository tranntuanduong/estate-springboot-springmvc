package com.laptrinhjavaweb.controller.api;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.service.ITransactionService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CustomerAPI {
	@Autowired
	private ICustomerService customerService;
	@Autowired 
	private ITransactionService transactionService;
	
	@GetMapping(value = {"/api/customer"})
	public List<CustomerDTO> findAll(@RequestParam Map<String, Object> customerQuery) {
		CustomerSearchBuilder builder = initCustomerSearchBuilder(customerQuery);
		Pageable pageable = new PageRequest(Integer.valueOf((String)customerQuery.get("page")) - 1, 
				Integer.valueOf((String)customerQuery.get("maxPageItem")));
		return customerService.findAll(builder, pageable);
	}
	
//	@GetMapping(value = {"/api/customer/total"})
//	public TotalItem getTotalItems(@RequestParam Map<String, Object> customerQuery) {
//		CustomerSearchBuilder builder= initCustomerSearchBuilder(customerQuery);
//		return new TotalItem(customerService.count(builder));
//	}

	private CustomerSearchBuilder initCustomerSearchBuilder(Map<String, Object> customerQuery) {
		String[] ids = new String[]{};
		if(StringUtils.isNotBlank((String) customerQuery.get("userIds"))) {
			ids = ((String)customerQuery.get("userIds")).split(",");
		}
		CustomerSearchBuilder builder = new CustomerSearchBuilder.Builder()
					.setName((String)customerQuery.get("name"))
					.setEmail((String)customerQuery.get("email"))
					.setPhoneNumber((String)customerQuery.get("phoneNumber"))
					.setStaffInCharge((String)customerQuery.get("staffInCharge"))
					.setUserIds(ids)
					.setUserId((String)customerQuery.get("userId"))
					.build();
		return builder;
	}
	
	@PostMapping(value = "/api/customer/staffInCharge")
	public void chooseStaffInCharge(@RequestBody CustomerDTO model) {
		customerService.chooseStaffInCharge(model);
	} 
	
	@PostMapping(value = "/api/customer")
	public CustomerDTO saveCustomer(@RequestBody CustomerDTO model) {
		 return customerService.save(model);
	} 
	
	@DeleteMapping(value = {"/api/customer"})
	public void deleteCustomer(@RequestBody Long[] ids) {
		customerService.delete(ids);
	}
	
	
	@GetMapping(value = {"/api/customer/findById"})
	public CustomerDTO findById(@RequestParam Long id) {
		return customerService.findById(id);
	}
	
	@PutMapping(value = {"/api/customer"})
	public CustomerDTO  updateCustomer(@RequestBody CustomerDTO model) {
		return customerService.update(model);
	} 
				
	@PostMapping(value = {"/api/customer/transaction"})
	public void saveCustomerTransaction(@RequestBody TransactionDTO model) {
		transactionService.save(model);
	} 
	
}
