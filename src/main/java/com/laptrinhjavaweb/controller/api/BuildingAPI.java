package com.laptrinhjavaweb.controller.api;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.service.IBuildingService;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping()
public class BuildingAPI {
	@Autowired
	private IBuildingService buildingService;
	
	
	
	@PostMapping(value = {"/api/building"})
	public BuildingDTO saveBuilding(@RequestBody BuildingDTO model) {
		return buildingService.save(model);
	} 
	
	@PostMapping(value = {"/api/building/handover"})
	public void toHandOver(@RequestBody BuildingDTO model) {
		buildingService.toHandOver(model);
	} 
	
	@PutMapping(value = {"/api/building"})
	public BuildingDTO updateBuilding(@RequestBody BuildingDTO model) {
		return buildingService.update(model);
	} 
	
	@DeleteMapping(value = {"/api/building"})
	public void deleteBuilding(@RequestBody Long[] ids) {
		buildingService.delete(ids);
	}
	
	@GetMapping(value = {"/api/building"})
	public List<BuildingDTO> findAll(@RequestParam Map<String, Object> buildingQuery) {
		BuildingSearchBuilder builder = initBuildingBuilder(buildingQuery);
		Pageable pageable = new PageRequest(Integer.valueOf((String)buildingQuery.get("page")) - 1, 
											Integer.valueOf((String)buildingQuery.get("maxPageItem")));
		return buildingService.findAll(builder, pageable);
	} 	
	
	private BuildingSearchBuilder initBuildingBuilder(Map<String, Object> buildingQuery) {
		String[] buildingTypes = new String[]{};
		if(StringUtils.isNotBlank((String) buildingQuery.get("buildingTypes"))) {
			buildingTypes = ((String) buildingQuery.get("buildingTypes")).split(",");
		}
		BuildingSearchBuilder buider = new BuildingSearchBuilder.Builder()
				.setName((String) buildingQuery.get("name"))
				.setBuildingArea((String) buildingQuery.get("buildingArea"))
				.setDistrict((String) buildingQuery.get("district"))
				.setWard((String) buildingQuery.get("ward"))
				.setStreet((String) buildingQuery.get("street"))
				.setNumberOfBasement((String) buildingQuery.get("numberOfBasement"))
//				.setAreaRentFrom((String) buildingQuery.get("areaRentFrom"))
				.setAreaRentTo((String) buildingQuery.get("areaRentTo"))
				.setCostRentFrom((String) buildingQuery.get("costRentFrom"))
				.setCostRentTo((String) buildingQuery.get("costRentTo"))
				.setUser_id((String) buildingQuery.get("user_id"))
				.setBuildingTypes(buildingTypes).build();
				
		return buider;
	}

//	@GetMapping(value = {"/api/building/total"})
//	public TotalItem getTotalItems(@RequestParam Map<String, Object> buildingQuery) {
//		BuildingSearchBuilder builder = initBuildingBuilder(buildingQuery);
//		return new TotalItem(buildingService.count(builder));
//	} 	
	
	@GetMapping(value = {"/api/building/findAll"})
	public BuildingDTO findById(@RequestParam Map<String, Object> buildingQuery) {
		return buildingService.findById(Long.valueOf((String)buildingQuery.get("id")));
	} 	
}




















