package com.laptrinhjavaweb.repository.custom.impl;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Pageable;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.custom.CustomerRepositoryCustome;

public class CustomerRepositoryImpl implements CustomerRepositoryCustome {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<CustomerEntity> findAll(CustomerSearchBuilder builder, Pageable pageable) {
		StringBuilder sql = new StringBuilder("SELECT C.company, C.email, C.name, C.need, C.node, C.phonenumber, C.status,");
		sql.append(" C.id, C.createdby, C.createddate, C.modifiedby, C.modifieddate");
		sql.append(" FROM customer C ");
		sql.append(" LEFT JOIN customer_staff on C.id = customer_staff.customerid");
		sql.append(" LEFT JOIN user ON user.id = customer_staff.userid where 1 = 1");
		//build map search 
		Map<String, Object> properties = builderMapSearch(builder);
		sql = createSQLFindAll(properties, sql);
		//build where clause
		StringBuilder whereClause = buildWhereClause(builder);
		sql.append(whereClause.toString());
		sql.append(" GROUP BY C.id");
		Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
		if(pageable != null) {
			query.setFirstResult((int)pageable.getOffset());
			query.setMaxResults(pageable.getPageSize());
		}
		return query.getResultList();
	}
	
	@Override
	public Long count(CustomerSearchBuilder builder) {
		try {
			StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM");
			sql.append(" (SELECT C.id FROM customer AS C ");
			sql.append(" LEFT JOIN customer_staff on C.id = customer_staff.customerid");
			sql.append(" LEFT JOIN user ON user.id = customer_staff.userid WHERE 1 = 1");
			//build map search 
			Map<String, Object> properties = builderMapSearch(builder);
			sql = createSQLFindAll(properties, sql);
			//build where clause
			StringBuilder whereClause = buildWhereClause(builder);
			sql.append(whereClause);
			sql.append(" GROUP BY C.id) AS COUNT");
			Query query = entityManager.createNativeQuery(sql.toString());
			//not work
			//entityManager.createNativeQuery(sql.toString(), CustomerEntity.class); 
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
	

	private StringBuilder buildWhereClause(CustomerSearchBuilder builder) {
		StringBuilder whereClause = new StringBuilder();
		if(builder.getUserIds().length > 0) {
			whereClause.append(" AND (userid="+builder.getUserIds()[0]+"");
			for(String userid : builder.getUserIds()) {
				if(!userid.equals(builder.getUserIds()[0])) {
					whereClause.append(" OR userid="+userid+"");
				}
			}
			whereClause.append(")");
		}
		if(StringUtils.isNotBlank(builder.getUserId())) {
			whereClause.append(" AND userid="+builder.getUserId());
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
				} else if(values[j] instanceof Long) {
					sql.append(" AND"+params[j]+"="+values[j]+"");
				}
				//etc
			}
		}
		return sql;
	}

	private Map<String, Object> builderMapSearch(CustomerSearchBuilder builder) {
		Map<String, Object> results = new HashMap<>();
		Field[] fields = CustomerSearchBuilder.class.getDeclaredFields();
		try {
			for(Field field : fields) {
				field.setAccessible(true);
				if(!field.getName().equals("userIds") && !field.getName().equals("userId")) {
					if(StringUtils.isNotBlank((String) field.get(builder))) {
						results.put(field.getName().toLowerCase(), field.get(builder));
					}
				}
			}
		} catch(IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return results;
	}




	
}
