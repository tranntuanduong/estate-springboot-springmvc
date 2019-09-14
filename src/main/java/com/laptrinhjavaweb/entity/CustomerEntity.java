package com.laptrinhjavaweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class CustomerEntity extends BaseEntity {
	@Column (name = "name")
	private String name;
	
	@Column (name = "phonenumber")
	private String phoneNumber;
	
	@Column (name = "email")
	private String email;
	
	@Column (name = "need")
	private String need;

	
	@Column (name = "status")
	private String status;
	
	@Column (name = "company")
	private String company;
	
	@Column (name = "node")
	private String node;
	
	@ManyToMany
	@JoinTable(name = "customer_staff",
	    joinColumns = @JoinColumn(name = "customerid"),
	    inverseJoinColumns = @JoinColumn(name = "userid")
	)
	private List<UserEntity> staffs = new ArrayList<>();

	@OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private List<TransactionEntity> transactions = new ArrayList<>();

	public List<TransactionEntity> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<TransactionEntity> transactions) {
		this.transactions = transactions;
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

	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	
	public List<UserEntity> getStaffs() {
		return staffs;
	}
	public void setStaffs(List<UserEntity> staffs) {
		this.staffs = staffs;
	}
}
