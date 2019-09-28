package com.laptrinhjavaweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.DataUtils;

@Controller(value = "customerControllerOfAdmin")
public class CustomerController {
	@Autowired private ICustomerService customerService;
	@Autowired private IUserService userService;
	
	@RequestMapping(value = "/admin/customer", method = RequestMethod.GET)
	public ModelAndView homePage(@ModelAttribute("model") CustomerDTO model) {
		ModelAndView mav = new ModelAndView("/admin/customer/list");
		CustomerSearchBuilder customerSearchBuilder = initCustomerSearchBuilder(model);
		Pageable pageable = new PageRequest(model.getPage() - 1, model.getMaxPageItem());
		model.setTotalItems(customerService.count(customerSearchBuilder));
		model.setTotalPage((int)Math.ceil((double)model.getTotalItems() / model.getMaxPageItem()));
		model.setListResult(customerService.findAll(customerSearchBuilder, pageable));		
		
		//get staff list
		List<UserDTO> staffs = userService.findAllStaffs(null, null);
		mav.addObject("staffs", staffs);
		return mav;
	}
	
	@RequestMapping(value = "/admin/customer/edit", method = RequestMethod.GET)
	public ModelAndView editPage(@ModelAttribute("model") CustomerDTO model) {
		ModelAndView mav = new ModelAndView("/admin/customer/edit");
		if(model.getId() != null) {
			CustomerDTO customerDTO = customerService.findById(model.getId());
			mav.addObject("customer", customerDTO);
		}
		//get staff list
		List<UserDTO> staffs = userService.findAllStaffs(null, model.getId());
		mav.addObject("staffs", staffs);
		mav.addObject("transactions", DataUtils.getTransactions());
		return mav;
	}
	private CustomerSearchBuilder initCustomerSearchBuilder(CustomerDTO model) {
		CustomerSearchBuilder builder = new CustomerSearchBuilder.Builder()
					.setName(model.getName()).setEmail(model.getEmail())
					.setPhoneNumber(model.getPhoneNumber())
					.setUserIds(model.getUserIds()).setUserId(model.getUserId())
					.build();
		return builder;
	}
	
}
