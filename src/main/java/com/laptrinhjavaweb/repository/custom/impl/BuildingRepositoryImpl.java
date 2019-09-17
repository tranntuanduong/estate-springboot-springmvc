package com.laptrinhjavaweb.repository.custom.impl;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom{
	
	@PersistenceContext
	private EntityManager entityManager;

	
	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder builder, Pageable pageable) {
		try {
			StringBuilder sql = new StringBuilder(" SELECT * FROM building AS A");
			sql.append(" LEFT JOIN assignment on assignment.building_id=A.id");
			sql.append(" LEFT JOIN user on assignment.user_id=user.id");
			sql.append(" WHERE 1=1");
			Map<String, Object> properties = buildMapSearch(builder);
			sql = createSQLFindAll(properties, sql);
			StringBuilder whereClause = createWhereClause(builder);
			sql.append(whereClause);
			sql.append(" GROUP BY A.id");
			Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
			if(pageable != null) {
				query.setFirstResult((int) pageable.getOffset());
				query.setMaxResults(pageable.getPageSize());
			}
		return query.getResultList();
		} catch(Exception e) {
			System.out.println(e);
		}
		return new ArrayList<>();
	}
	
	
	@Override
	public Long count(BuildingSearchBuilder builder) {
		try {
			StringBuilder sql = new StringBuilder(" SELECT COUNT(*) FROM");
			sql.append(" (SELECT A.id FROM building AS A");
			sql.append(" LEFT JOIN assignment on assignment.building_id=A.id");
			sql.append(" LEFT JOIN user on assignment.user_id=user.id");
			sql.append(" WHERE 1=1");
			Map<String, Object> properties = buildMapSearch(builder);
			sql = createSQLFindAll(properties, sql);
			StringBuilder whereClause = createWhereClause(builder);
			sql.append(whereClause);
			sql.append(" GROUP BY A.id) AS COUNT");
			Query query = entityManager.createNativeQuery(sql.toString());
			List<BigInteger> resultList = query.getResultList();		
			if(resultList.size() != 0) {
				return Long.parseLong(resultList.get(0).toString(), 10);
			} else {
				return 0L;
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
	private Map<String, Object> buildMapSearch(BuildingSearchBuilder builder) {
		Map<String, Object> result = new HashMap<>();
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field field : fields) {
				if (!field.getName().equals("buildingTypes") && !field.getName().startsWith("costRent")
						&& !field.getName().startsWith("areaRent")) {
					field.setAccessible(true);
					if (StringUtils.isNotBlank((String) field.get(builder))) {					
						if(field.getName().equals("numberOfBasement") || field.getName().equals("buildingArea")
																	  || field.getName().equals("user_id")) {
							result.put(field.getName().toLowerCase(), Integer.parseInt((String)field.get(builder)));
						} else {
							result.put(field.getName().toLowerCase(), field.get(builder));
						}
					}
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	} 
	
	StringBuilder createWhereClause(BuildingSearchBuilder builder) {
		StringBuilder whereClause = new StringBuilder();
		if(StringUtils.isNotBlank(builder.getCostRentFrom())) {
			whereClause.append(" AND costrent >= "+builder.getCostRentFrom());
		}
		if(StringUtils.isNotBlank(builder.getCostRentTo())) {
			whereClause.append(" AND costrent <= "+builder.getCostRentTo());
		}
		if(StringUtils.isNotBlank(builder.getAreaRentFrom()) || StringUtils.isNotBlank(builder.getAreaRentTo())) {
			whereClause.append(" AND EXISTS (SELECT * FROM rentarea ra WHERE (ra.buildingid = A.id");
			if(StringUtils.isNotBlank(builder.getAreaRentFrom())) {
				whereClause.append(" AND ra.value >='"+builder.getAreaRentFrom()+"'");
			}
			if(StringUtils.isNotBlank(builder.getAreaRentTo())) {
				whereClause.append(" AND ra.value <='"+builder.getAreaRentTo()+"'");
			}
			whereClause.append("))");
		}
		if(builder.getBuildingTypes().length > 0) {
			whereClause.append(" AND (A.type LIKE '%"+builder.getBuildingTypes()[0]+"%'");
			 for(String type : builder.getBuildingTypes()) {	
				 if(!type.equals(builder.getBuildingTypes()[0])) {
					 whereClause.append(" OR A.type LIKE '%"+type+"%'");
				 }
			 }
			 //java8
//			 Arrays.stream(builder.getBuildingTypes()).filter(item -> !item.equals(builder.getBuildingTypes()[0]))
//			 .forEach(item ->  whereClase.append(" OR A.type LIKE '%"+item+"%'"));
			 whereClause.append(" )");
		}
		return whereClause;
	}

	private StringBuilder createSQLFindAll(Map<String, Object> properties, StringBuilder sql) {
		
		if(properties != null && properties.size() >= 1) {
			String[] params = new String[properties.size()];
			Object[] values = new Object[properties.size()];
			int i = 0;
			for(Map.Entry<?, ?> item : properties.entrySet()) {
				params[i] = (String) item.getKey();
				values[i] = item.getValue();
				i++;
			}
			for(int j = 0; j < params.length; j++) {
				if(values[j] instanceof String) {
					sql.append(" AND LOWER("+params[j]+") LIKE LOWER('%"+values[j]+"%')");
				} else if(values[j] instanceof Integer) {
					sql.append(" AND "+params[j]+" = "+values[j]+" ");
				} else if(values[j] instanceof Long) {
					sql.append(" AND "+params[j]+" = "+values[j]+" ");
				}
			}
		}
		return sql;
	}


	
}




















