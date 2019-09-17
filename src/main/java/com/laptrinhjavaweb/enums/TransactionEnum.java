package com.laptrinhjavaweb.enums;

public enum TransactionEnum {
	CUSTOMER_CARE ("Chăm sóc khách hàng"),
	GUIDE ("Dẫn đi xem");
	
	private String value;
	TransactionEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
