package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.builder.UserSearchBuilder;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IUserService;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserConverter userConverter;
	@Override
	public List<UserDTO> findAll(UserSearchBuilder builder, Pageable pageable) {
		List<UserEntity> userEntities = userRepository.findAll(builder, pageable);
		List<UserDTO> results = new ArrayList<>();
		
		for(UserEntity userEntity : userEntities) {
			UserDTO userDTO = userConverter.convertToDTO(userEntity);
			//check for building
			for(BuildingDTO building : userDTO.getBuildings()) {
				if(builder.getBuildingId() != null) {
					if(building.getId() == Long.parseLong(builder.getBuildingId())) {
						userDTO.setBuildingChecked("checked");
					}
				}
			}
			//check for customer
			for(CustomerDTO customer : userDTO.getCustomers()) {
				if(builder.getCustomerId() != null) {
					if(customer.getId() == Long.parseLong(builder.getCustomerId())) {
						userDTO.setCustomerChecked("checked");
					}
				}
			}
			results.add(userDTO);
		}
		
		return results;
	}
	@Override
	public List<UserDTO> findAllStaffs(Long buildingId, Long customerId) {
		List<UserEntity> userEntities = userRepository.findByStatusAndRolesCode(SystemConstant.ACTIVE_STATUS, SystemConstant.ROLE_STAFF);
		List<UserDTO> result = new ArrayList<>();
		for(UserEntity userEntity : userEntities) {
//			userDTOs.add(userConverter.convertToDTO(userEntity));
			UserDTO userDTO = userConverter.convertToDTO(userEntity);
			//check for building
			for(BuildingDTO building : userDTO.getBuildings()) {
				if(buildingId != null) {
					if(building.getId() == buildingId) {
						userDTO.setBuildingChecked("checked");
					}
				}
 			}
			//check for customer
			for(CustomerDTO customer : userDTO.getCustomers()) {
				if(customerId != null) {
					if(customer.getId() == customerId) {
						userDTO.setCustomerChecked("checked");
					}
				}
			}
			result.add(userDTO);
		}
		return result;
	}
}
