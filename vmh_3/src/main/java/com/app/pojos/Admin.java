package com.app.pojos;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="admin")
public class Admin {
	
	//id usrnme passwrd 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="admin_id")
	private Integer adminid;
	@Column(length=20,name="admin_usrnme",unique=true,nullable=false)
	private String adminUsername;
	@Column(length=20,unique=true,nullable = false)
	private String adminPassword;
	
	public Admin() {
		System.out.println("in ctor of "+getClass().getName());
	}
	
	public Integer getAdminid() {
		return adminid;
	}
	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}
	public String getAdminUsername() {
		return adminUsername;
	}
	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	@Override
	public String toString() {
		return "Admin [adminid=" + adminid + ", adminUsername=" + adminUsername + ", adminPassword=" + adminPassword
				+ "]";
	}
	
	

}
