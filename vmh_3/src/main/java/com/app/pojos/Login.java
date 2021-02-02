package com.app.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="login")
public class Login implements Serializable  {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="login_id")
	private Integer loginid;
	
	@Column(length=20,name="username",unique=true,nullable=false)
	private String loginusrnme;
	
	@Column(length=20,name="password",unique=true,nullable = false)
	private String loginpwd;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role", length = 20, nullable = false)
	private Role role;

	public Login() {
		System.out.println("in ctor of "+getClass().getName());
	}

	public Integer getLoginid() {
		return loginid;
	}

	public void setLoginid(Integer loginid) {
		this.loginid = loginid;
	}

	public String getLoginusrnme() {
		return loginusrnme;
	}

	public void setLoginusrnme(String loginusrnme) {
		this.loginusrnme = loginusrnme;
	}

	public String getLoginpwd() {
		return loginpwd;
	}

	public void setLoginpwd(String loginpwd) {
		this.loginpwd = loginpwd;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Login [loginid=" + loginid + ", loginusrnme=" + loginusrnme + ", loginpwd=" + loginpwd + ", role="
				+ role + "]";
	}
	

}
