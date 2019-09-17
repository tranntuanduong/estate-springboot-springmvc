package com.laptrinhjavaweb.dto;

public class TransactionDTO extends AbstractDTO<TransactionDTO>{

	private Long customerId;
	private String code;
	private String node;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	
}
