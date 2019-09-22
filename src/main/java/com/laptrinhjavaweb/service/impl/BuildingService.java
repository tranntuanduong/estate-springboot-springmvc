package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IBuildingService;

@Service
public class BuildingService implements IBuildingService{
	
	@Autowired
	private BuildingRepository buildingRepository;
	@Autowired
	private RentAreaRepository rentAreaRepository;
	@Autowired
	private BuildingConverter buildingConverter;
	@Autowired
	private UserRepository userRepository;
	
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
	
	@Override
	@Transactional
	public BuildingDTO save(BuildingDTO newBuilding) {

		BuildingEntity buildingEntity = buildingConverter.convertToEntity(newBuilding);
		buildingEntity.setCreatedBy("duong dep trai ahihi");
		buildingEntity.setCreatedDate(new Date());	
		if(newBuilding.getBuildingTypes().length > 0) {
			buildingEntity.setType(StringUtils.join(newBuilding.getBuildingTypes(), ","));
		}
		//save rentarea
		List<RentAreaEntity> areas = new ArrayList<>();
		if(StringUtils.isNotBlank(newBuilding.getRentArea())) {
			for(String item : newBuilding.getRentArea().split(",")) {
				RentAreaEntity rentArea = new RentAreaEntity();
				rentArea.setValue(item);
				rentArea.setBuilding(buildingEntity);
				areas.add(rentArea);		
			}	 
		}
		buildingEntity.setAreas(areas);
		//save staffs
		List<UserEntity> staffs = new ArrayList<UserEntity>();
		for(Long id : newBuilding.getIds()) {
			UserEntity staff = userRepository.findOne(id);
			staffs.add(staff);
		}
		buildingEntity.setStaffs(staffs);
		buildingEntity = buildingRepository.save(buildingEntity);
		return buildingConverter.convertToDTO(buildingEntity);
	}
	@Override
	@Transactional
	public BuildingDTO update(BuildingDTO model) {
		BuildingEntity oldBuilding = buildingRepository.findOne(model.getId());
		BuildingEntity newBuilding = buildingConverter.convertToEntity(model);
		if(model.getBuildingTypes().length > 0) {
			newBuilding.setType(StringUtils.join(model.getBuildingTypes(),","));
		}
		newBuilding.setCreatedBy(oldBuilding.getCreatedBy());
		newBuilding.setCreatedDate(oldBuilding.getCreatedDate());
		newBuilding.setModifiedBy(oldBuilding.getModifiedBy());
		newBuilding.setModifiedDate(oldBuilding.getModifiedDate());
		newBuilding.setId(model.getId());
		//delete buildingAreas
		rentAreaRepository.deleteByBuildingId(model.getId());
		//add new buildingAreas
		List<RentAreaEntity> areas = new ArrayList<>();
		if(StringUtils.isNotBlank(model.getRentArea())) {
			for(String item : model.getRentArea().split(",")) {
				RentAreaEntity rentArea = new RentAreaEntity();
				rentArea.setValue(item);
				rentArea.setBuilding(newBuilding);
				areas.add(rentArea);		
			}	 
		}
		newBuilding.setAreas(areas);
		//add staffs
		List<UserEntity> staffs = new ArrayList<UserEntity>();
		for(Long id : model.getIds()) {
			UserEntity staff = userRepository.findOne(model.getId());
			staffs.add(staff);
		}
		newBuilding.setStaffs(staffs);
		newBuilding = buildingRepository.save(newBuilding);
		return buildingConverter.convertToDTO(newBuilding);
	}
	@Override
	@Transactional
	public void delete(Long[] ids) {
		for(Long id : ids) {
			//remove rentarea
			rentAreaRepository.deleteByBuildingId(id);	
			//remove assignment
			BuildingEntity buildingEntity = buildingRepository.findOne(id);
			for(UserEntity user : buildingEntity.getStaffs()) {
				user.getBuildings().remove(buildingEntity);
			}
			buildingRepository.delete(buildingEntity);
		}
	}

	@Override
	public BuildingDTO findById(Long id) {
		BuildingEntity buildingEntity = buildingRepository.findOne(id);
		return buildingConverter.convertToDTO(buildingEntity);
	}

	@Override
	@Transactional
	public void toHandOver(BuildingDTO model) {
		buildingRepository.deleteBuiding_UserByBuildingId(model.getId());
		for(Long userId : model.getIds()) {
			buildingRepository.toHandOverNative(model.getId(), userId);
		}
	}
	

	
}
