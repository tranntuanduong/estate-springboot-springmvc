package com.laptrinhjavaweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.service.IBuildingService;

@Controller(value = "test")
public class BuildingAPI {
	@Autowired
	private IBuildingService buildingService;
//	
//	@RequestMapping(value = "/api/building", method = RequestMethod.POST)
//	public BuildingDTO saveBuilding(@RequestBody BuildingDTO model) {
//		return buildingService.save(model);
//	} 
}
