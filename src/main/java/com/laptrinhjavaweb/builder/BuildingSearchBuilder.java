	package com.laptrinhjavaweb.builder;

public class BuildingSearchBuilder {
	 private String name;
	 private String ward;
	 private String street;
	 private String buildingArea;
	 private String numberOfBasement;
	 private String rentArea;
	 private String costRentFrom;
	 private String costRentTo;
	 private String areaRentFrom;
	 private String areaRentTo;
	 private String[] buildingTypes = new String[] {};
	 private String district;
	 private String user_id;
	public String getBuildingArea() {
		return buildingArea;
	}
	public String getName() {
		return name;
	}
	public String getWard() {
		return ward;
	}
	public String getStreet() {
		return street;
	}
	 
	 public String getCostRentFrom() {
		return costRentFrom;
	}
	public String getCostRentTo() {
		return costRentTo;
	}
	public String getAreaRentFrom() {
		return areaRentFrom;
	}
	public String getAreaRentTo() {
		return areaRentTo;
	}
	
	public String getDistrict() {
		return district;
	}
	
	public String getNumberOfBasement() {
		return numberOfBasement;
	}
	public String getRentArea() {
		return rentArea;
	}
	
	 public String[] getBuildingTypes() {
		return buildingTypes;
	}

	public String getUser_id() {
		return user_id;
	}
	public BuildingSearchBuilder(Builder builder) {
		 this.name = builder.name;
		 this.ward = builder.ward;
		 this.street = builder.street;
		 this.numberOfBasement = builder.numberOfBasement;
		 this.rentArea = builder.rentArea;
		 this.costRentFrom = builder.costRentFrom;
		 this. costRentTo = builder.costRentTo;
		 this.areaRentFrom = builder.areaRentForm;
		 this.areaRentTo = builder.areaRentTo;
		 this.buildingTypes = builder.buildingTypes;
		 this.district = builder.district;
		 this.buildingArea = builder.buildingArea;
		 this.user_id = builder.user_id;
	 }
	 


	public static class Builder {
		 private String name;
		 private String ward;
		 private String street;
		 private String numberOfBasement;
		 private String buildingArea;
		 private String rentArea;
		 private String costRentFrom;
		 private String costRentTo;
		 private String areaRentForm;
		 private String areaRentTo;
		 private String[] buildingTypes = new String[] {};
		 private String district;
		 private String user_id;
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		
		public Builder setUser_id(String user_id) {
			this.user_id = user_id;
			return this;
		}

		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}
		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}
		
		public Builder setBuildingArea(String buildingArea) {
			this.buildingArea = buildingArea;
			return this;
		}
		public Builder setDistrict(String district) {
			this.district = district;
			return this;
		}
		public Builder setNumberOfBasement(String numberOfBasement) {
			this.numberOfBasement = numberOfBasement;
			return this;
		}
		
		public Builder setRentArea(String rentArea) {
			this.rentArea = rentArea;
			return this;
		}
		public Builder setCostRentFrom(String costRentFrom) {
			this.costRentFrom = costRentFrom;
			return this;
		}
		public Builder setCostRentTo(String costRentTo) {
			this.costRentTo = costRentTo;
			return this;
		}
		public Builder setAreaRentForm(String areaRentForm) {
			this.areaRentForm = areaRentForm;
			return this;
		}
		public Builder setAreaRentTo(String areaRentTo) {
			this.areaRentTo = areaRentTo;
			return this;
		}
		public Builder setBuildingTypes(String[] buildingTypes) {
			this.buildingTypes = buildingTypes;
			return this;
		}
		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}
	 }
	 
}
