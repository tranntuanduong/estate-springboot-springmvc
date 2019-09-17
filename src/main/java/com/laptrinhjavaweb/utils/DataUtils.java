package com.laptrinhjavaweb.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.laptrinhjavaweb.enums.BuildingTypeEnum;
import com.laptrinhjavaweb.enums.DistrictEnum;
import com.laptrinhjavaweb.enums.TransactionEnum;

public class DataUtils {
	public static Map<String, String> getBuildingType() {
		Map<String, String> result = new HashMap<String, String>();
		for(BuildingTypeEnum item : BuildingTypeEnum.values()) {
			result.put(item.name(), item.getValue());
		}
		return result;
	}
	
	public static Map<String, String> getDistricts() {
//		Map<String, String> result = new HashMap<String, String>();
		Map<String, String> result = new TreeMap<String, String>();
		for(DistrictEnum item : DistrictEnum.values()) {
			result.put(item.name(), item.getValue());
		}
		return result;
	}
	
	public static Map<String, String> getTransactions() {
		Map<String, String> result = new TreeMap<String, String>();
		for(TransactionEnum item : TransactionEnum.values()) {
			result.put(item.name(), item.getValue());
		}
		return result;
	}
}
