package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.service.IBuildingService;

@Service
public class BuildingService implements IBuildingService{
	
	@Autowired
	private BuildingRepository buildingRepository;

	@Autowired
	private BuildingConverter buildingConverter;

	
	@Override
	public List<BuildingDTO> findAll(BuildingSearchBuilder builder, Pageable pageable) {
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(builder, pageable);
//		List<BuildingDTO> results = buildingEntities.stream()
//													.map(item -> buildingConverter.convertToDTO(item)).collect(Collectors.toList());
		List<BuildingDTO> results = new ArrayList<>();
		
		for(BuildingEntity buildingEntity : buildingEntities) {
			StringBuilder staffList = new StringBuilder();
			BuildingDTO buildingDTO = buildingConverter.convertToDTO(buildingEntity);
			if(buildingEntity.getStaffs() != null) {
				for(UserEntity staff : buildingEntity.getStaffs()) {
					if(staffList.length() > 0) {
						staffList.append(","+staff.getFullName());
					} else {
						staffList.append(staff.getFullName());
					}
				}
				buildingDTO.setStaffInCharge(staffList.toString());
			}
			results.add(buildingDTO);
		}
		return results;
	}
	
	@Override
	public int count(BuildingSearchBuilder builder) {
		return buildingRepository.count(builder).intValue();
	}
		
}