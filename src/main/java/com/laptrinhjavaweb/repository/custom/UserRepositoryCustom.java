package com.laptrinhjavaweb.repository.custom;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.laptrinhjavaweb.builder.UserSearchBuilder;
import com.laptrinhjavaweb.entity.UserEntity;

public interface UserRepositoryCustom {
	List<UserEntity> findAll(UserSearchBuilder builder, Pageable pageable);
}
