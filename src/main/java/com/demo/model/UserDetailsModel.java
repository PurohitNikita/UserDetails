package com.demo.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UserDetailsModel")
public class UserDetailsModel {
	
	@Id
	private String id;
	private String fname;
	private String lname;
	private String dob;
	private String city;
	private String mobileNumber;
	private boolean isDeleted; // Used for soft delete. 
	//set this boolean true if deleted. can be recovered by setting false in later stages
	
	public String getFname() {
		return fname;
	}
	
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getLname() {
		return lname;
	}
	
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public String getDob() {
		return dob;
	}
	
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	

}
