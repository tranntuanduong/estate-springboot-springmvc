package com.laptrinhjavaweb.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.builder.UserSearchBuilder;
import com.laptrinhjavaweb.dto.UserDTO;

@Service
public interface IUserService {
	List<UserDTO> findAll(UserSearchBuilder builder, Pageable pageable);
	List<UserDTO> findAllStaffs(Long buildingId, Long customerId);
}
 