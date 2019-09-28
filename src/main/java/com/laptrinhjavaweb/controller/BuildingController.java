package com.laptrinhjavaweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.DataUtils;

@Controller(value = "buildingControllerOfAdmin")
public class BuildingController {
	@Autowired
	private IBuildingService buildingService;
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/admin/building", method = RequestMethod.GET)
	public ModelAndView homePage(@ModelAttribute("model") BuildingDTO model) {
		ModelAndView mav = new ModelAndView("/admin/building/list");
		BuildingSearchBuilder buildingSearchBuilder = initBuildingBuilder(model);
		Pageable pageable = new PageRequest(model.getPage() - 1, model.getMaxPageItem());
		model.setTotalItems(buildingService.count(buildingSearchBuilder));
		model.setTotalPage((int)Math.ceil((double)model.getTotalItems() / model.getMaxPageItem()));
		model.setListResult(buildingService.findAll(buildingSearchBuilder, pageable));		
		mav.addObject("districts", DataUtils.getDistricts());
		mav.addObject("buildingtypes", DataUtils.getBuildingType());

		//get staff list
		List<UserDTO> staffs = userService.findAllStaffs(null, null);
		mav.addObject("staffs", staffs);
		return mav;
	}
	
	
	@RequestMapping(value = "/admin/building/edit", method = RequestMethod.GET)
	public ModelAndView editPage(@ModelAttribute("model") BuildingDTO model) {
		ModelAndView mav = new ModelAndView("/admin/building/edit");	
		if(model.getId() != null) {
			//building
			BuildingDTO buildingDTO = buildingService.findById(model.getId());
			mav.addObject("building", buildingDTO);
		}
		//get staff list		
		//new Code
		List<UserDTO> staffs = userService.findAllStaffs(model.getId(), null);
		
		mav.addObject("staffs", staffs);
		mav.addObject("districts", DataUtils.getDistricts());
		mav.addObject("buildingtypes", DataUtils.getBuildingType());
		return mav;
	}


	private BuildingSearchBuilder initBuildingBuilder(BuildingDTO model) {
		BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder()
				.setName(model.getName()).setWard(model.getWard()).setDistrict(model.getDistrict())
				.setCostRentFrom(model.getCostRentFrom()).setCostRentTo(model.getCostRentTo())
				.setAreaRentForm(model.getAreaRentForm()).setAreaRentTo(model.getAreaRentTo())
				.setNumberOfBasement(model.getNumberOfBasement()).setRentArea(model.getRentArea())
				.setStreet(model.getStreet()).setBuildingTypes(model.getBuildingTypes())
				.setBuildingArea(model.getBuildingArea()).setUser_id(model.getUser_id())
				.build();
		return builder;
	}

}
