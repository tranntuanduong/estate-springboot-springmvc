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
		try {
			StringBuilder sql = new StringBuilder("SELECT * FROM customer as C ");
			if(StringUtils.isNotBlank(builder.getUserId())) {
				sql.append("INNER JOIN customer_staff AS CS ON CS.customerid = C.id ");
			}
			sql.append("WHERE 1=1 ");
			Map<String, Object> properties = buildMapSearch(builder);
			sql = createSQLFindAll(sql, properties);
			StringBuilder whereClause = createWhereClause(builder);
			sql.append(whereClause);
			Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
			if(pageable != null) {
				query.setFirstResult((int) pageable.getOffset());
				query.setMaxResults(pageable.getPageSize());
			}
			return query.getResultList();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Long count(CustomerSearchBuilder builder) {
		try {
			StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM customer as C ");
			if(StringUtils.isNotBlank(builder.getUserId())) {
				sql.append("INNER JOIN customer_staff AS CS ON CS.customerid = C.id ");
			}
			sql.append("WHERE 1=1 ");
			Map<String, Object> properties = buildMapSearch(builder);
			sql = createSQLFindAll(sql, properties);
			StringBuilder whereClause = createWhereClause(builder);
			sql.append(whereClause);
			Query query = entityManager.createNativeQuery(sql.toString());
			List<BigInteger> resultList = query.getResultList();	
			if(resultList.size() != 0) {
				return Long.parseLong(resultList.get(0).toString(), 10);
			} else {
				return 0L;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private StringBuilder createWhereClause(CustomerSearchBuilder builder) {
		StringBuilder whereClause = new StringBuilder();
		if(StringUtils.isNotBlank(builder.getUserId())) {
			whereClause.append("AND CS.userid = "+builder.getUserId()+" ");
		}
		return whereClause;
	}

	private StringBuilder createSQLFindAll(StringBuilder sql, Map<String, Object> properties) {
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
					sql.append(" AND LOWER("+params[j]+") LIKE LOWER('%"+values[j]+"%') ");
				} else if(values[j] instanceof Long) {
					sql.append(" AND "+params[j]+"="+values[j]+" ");
				}
				//etc
			}
		}
		return sql;
	}

	private Map<String, Object> buildMapSearch(CustomerSearchBuilder builder) {
		Map<String, Object> results = new HashMap<>();
		Field[] fields = CustomerSearchBuilder.class.getDeclaredFields();
		try {
			for(Field field : fields) {
				field.setAccessible(true);
				if(!field.getName().equals("userIds") && !field.getName().equals("userId")) {
					if(StringUtils.isNotBlank((String)field.get(builder))) {			
						if(field.getName().equals("phoneNumber")) {
							results.put(field.getName().toLowerCase(), Long.parseLong((String)field.get(builder)));
						} else {
							results.put(field.getName().toLowerCase(), field.get(builder));
						}
					}
				}
			}
		} catch(IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return results;
	}
}
