package com.laptrinhjavaweb.dto;

import java.util.List;

public class CustomerDTO extends AbstractDTO<CustomerDTO>  {
	private String name;
	private String phoneNumber;
	private String email;
	private String need;

	private String status;
	//nhan vien quan li customer
	private String staffInCharge;
	private String userId;
	
	private String company;	
	private List<TransactionDTO> transactions;
	
	private String code;
	private String node;
	private String[] userIds;
	
	private List<UserDTO> staffList;
	
	public String[] getUserIds() {
		return userIds;
	}
	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
	}
	public List<UserDTO> getStaffList() {
		return staffList;
	}
	public void setStaffList(List<UserDTO> staffList) {
		this.staffList = staffList;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<TransactionDTO> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<TransactionDTO> transactions) {
		this.transactions = transactions;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getStaffInCharge() {
		return staffInCharge;
	}
	public void setStaffInCharge(String staffInCharge) {
		this.staffInCharge = staffInCharge;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNeed() {
		return need;
	}
	public void setNeed(String need) {
		this.need = need;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
