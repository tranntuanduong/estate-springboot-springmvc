package com.laptrinhjavaweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.builder.UserSearchBuilder;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.IUserService;

@Controller(value = "userControllerOfAdmin")
public class UserController {
	@Autowired
	private IUserService UserService;
	
	@RequestMapping(value = "/staffList", method = RequestMethod.GET)
	public ModelAndView homePage(@ModelAttribute("model") UserDTO model) {
		ModelAndView mav = new ModelAndView("admin/building/staffList");
		UserSearchBuilder builder = initUserBuilder(model);
		UserDTO users = new UserDTO();
		users.setListResult(UserService.findAll(builder, null));
		mav.addObject("users", users);
		return mav;
	}
	

	
	private UserSearchBuilder initUserBuilder(UserDTO model) {
		UserSearchBuilder builder = new UserSearchBuilder.Builder()
					.setFullName(model.getFullName()).setRole(model.getRole())
					.setBuildingId(model.getBuildingId()).setCustomerId(model.getCustomerId())
					.build();
		return builder;
	}
}
