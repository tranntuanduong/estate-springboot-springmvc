package com.laptrinhjavaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingRepositoryCustom {
	@Modifying
	@Query( value = "insert into assignment(building_id, user_id) values(?1, ?2)",
			nativeQuery = true)
	void toHandOverNative(Long buildingId, Long userId);
	
	@Modifying	
	@Query( value = "delete from assignment where building_id = ?1",
			nativeQuery = true)
	void deleteBuiding_UserByBuildingId(Long buildingId);
	
}
