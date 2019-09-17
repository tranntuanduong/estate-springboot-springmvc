package com.laptrinhjavaweb.repository.custom.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Pageable;

import com.laptrinhjavaweb.builder.UserSearchBuilder;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.custom.UserRepositoryCustom;

public class UserRepositoryImpl implements UserRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<UserEntity> findAll(UserSearchBuilder builder, Pageable pageable) {
		try {
			StringBuilder  sql = new StringBuilder("SELECT * FROM user");
			sql.append(" left join user_role on user.id = user_role.user_id left join role on role.id = user_role.role_id  WHERE 1 = 1");	
			//build map search
			Map<String, Object> properties = buildMapSearch(builder);
			sql = createSQLFindAll(properties, sql);
			sql.append(" group by user.id");
			Query query = entityManager.createNativeQuery(sql.toString(), UserEntity.class);
			if(pageable != null) {
				query.setFirstResult((int) pageable.getOffset());
				query.setMaxResults(pageable.getPageSize());
			}
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
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
				//buildingId and customerId for check box
				if(!params[j].equals("buildingid") && !params[j].equals("customerid")) {
					if(values[j] instanceof String) {
						if(params[j].equals("role")) {
							sql.append(" AND code LIKE LOWER('%"+values[j]+"%')");
						} else {
							sql.append(" AND LOWER("+params[j]+") LIKE LOWER('%"+values[j]+"%')");
						}
					} else if(values[j] instanceof Long) {
						sql.append(" AND "+params[j]+"="+values[j]);
					}
					//etc
				}
			}
		}
		return sql;
	}

	private Map<String, Object> buildMapSearch(UserSearchBuilder builder) {
		Map<String, Object> results = new HashMap<>();
		try {
			Field[] fields = UserSearchBuilder.class.getDeclaredFields();
			for(Field field : fields) {
				field.setAccessible(true);
				if(StringUtils.isNotBlank((String) field.get(builder))) {
					results.put(field.getName().toLowerCase(), field.get(builder));		
				}
			}
		} catch(IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return results;
	}

}
