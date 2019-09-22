package com.laptrinhjavaweb.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;

@Service 
public interface IBuildingService {
	BuildingDTO save(BuildingDTO newBuilding);
	BuildingDTO update(BuildingDTO newBuilding);
	void delete(Long[] ids);
	
	List<BuildingDTO> findAll(BuildingSearchBuilder builder, Pageable pageable);
	int count(BuildingSearchBuilder builder);
	BuildingDTO findById(Long id);
	void toHandOver(BuildingDTO newBuilding);
	
}
 