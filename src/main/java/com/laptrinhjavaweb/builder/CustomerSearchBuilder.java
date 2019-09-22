package com.laptrinhjavaweb.builder;

public class CustomerSearchBuilder {
	private String name;
	private String email;
	private String phoneNumber;
	private String userId;
	private String staffInCharge;
	private String[] userIds;
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getUserId() {
		return userId;
	}
	
	public String[] getUserIds() {
		return userIds;
	}
	public String getStaffInCharge() {
		return staffInCharge;
	}
	public CustomerSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.email = builder.email;
		this.phoneNumber = builder.phoneNumber;
		this.userId = builder.userId;
		this.staffInCharge = builder.staffInCharge;
		this.userIds = builder.userIds;
	}
	
	public static class Builder {
		private String name;
		private String email;
		private String phoneNumber;
		private String userId;
		private String staffInCharge;
		private String[] userIds;
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}
		public Builder setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}
		public Builder setUserId(String userid) {
			this.userId = userid;
			return this;
		}
		public Builder setStaffInCharge(String staffInCharge) {
			this.staffInCharge = staffInCharge;
			return this;
		}
		
		
		public Builder setUserIds(String[] userIds) {
			this.userIds = userIds;
			return this;
		}
		public CustomerSearchBuilder build() {
			return new CustomerSearchBuilder(this);
		}
		
	}
	
}
