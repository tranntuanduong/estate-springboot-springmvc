package com.laptrinhjavaweb.repository.custom;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;

public interface BuildingRepositoryCustom {
	List<BuildingEntity> findAll(BuildingSearchBuilder builder, Pageable pageable);
	Long count(BuildingSearchBuilder builder);
}
