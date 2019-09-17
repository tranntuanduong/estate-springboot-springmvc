package com.laptrinhjavaweb.builder;



public class UserSearchBuilder {
	 private String fullName;
	 private String role;
	 private String buildingId;
	 private String customerId;
	 private String action;
	 public String getName() {
		return fullName;
	}
	public String getRole() {
		return role;
	}
	
	public String getAction() {
		return action;
	}
	public String getCustomerId() {
		return customerId;
	}
	public String getBuildingId() {
		return buildingId;
	}
	public UserSearchBuilder(Builder builder) {
		 this.fullName = builder.fullName;
		 this.role = builder.role;
		 this.buildingId = builder.buildingId;
		 this.customerId = builder.customerId;
		 this.action = builder.action;
	 }
	 public static class Builder {
		 private String fullName;
		 private String role;
		 private String buildingId;
		 private String customerId;
		 private String action;
		 public Builder setFullName(String fullName) {
			this.fullName = fullName;
			return this;
		}

		public Builder setAction(String action) {
			this.action = action;
			return this;
		}

		public Builder setCustomerId(String customerId) {
			this.customerId = customerId;
			return this;
		}


		public Builder setRole(String role) {
			this.role = role;
			return this;
		}

		public Builder setBuildingId(String buildingId) {
			this.buildingId = buildingId;
			return this;
		}
		public UserSearchBuilder build() {
			return new UserSearchBuilder(this);
		}

	 }
	 
	 
}
