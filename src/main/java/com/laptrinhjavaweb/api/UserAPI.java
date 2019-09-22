package com.laptrinhjavaweb.api;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.builder.UserSearchBuilder;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.IUserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserAPI {
	
	@Autowired
	private IUserService userService;

	@GetMapping(value = {"/api/user/assignment"})
	public List<UserDTO> findAll(@RequestParam Map<String, Object> userQuery) {
		UserSearchBuilder builder = initUserSearchBuilder(userQuery);
		if(StringUtils.isNotBlank((String) userQuery.get("page"))) {
			Pageable pageable = new PageRequest(Integer.valueOf((String)userQuery.get("page")) - 1, 
					Integer.valueOf((String)userQuery.get("maxPageItem")));
			return userService.findAll(builder, pageable);
		} else {
			return userService.findAll(builder, null);
		}
	}
	
	private UserSearchBuilder initUserSearchBuilder(Map<String, Object> userQuery) {
		UserSearchBuilder builder = new UserSearchBuilder.Builder()
				.setBuildingId((String)userQuery.get("buildingId"))
				.setFullName((String)userQuery.get("fullName"))
				.setRole((String)userQuery.get("role"))
				.setCustomerId((String)userQuery.get("customerId"))
				.build();
		return builder;
	}

	
	
}
