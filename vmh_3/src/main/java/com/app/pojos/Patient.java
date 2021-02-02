package com.app.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="patient")
public class Patient implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="p_id")
	private Integer patientid;
	
	@Column(length = 30,name="pName")
	private String patientName;
	
	@Column(name="p_age")
	private Integer age;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "gender", length = 20, nullable = false)
	private Gender gender;
	
	@Column(name="p_wght")
	private double weight;
	
	@Column(name="BG")
	private String bloodgroup;
	
	@Column(length = 30,name="pAddress")
	private String address;
	
	@Column(length=50,name="p_emailid",unique=true,nullable= false)//setting emalid as unique key
	private String patientemailID;
	
	@Column(name="cntct_no")
	private Long contactno;
	
	@Column(length=50,name="ptnt_uname",unique=true,nullable= false)
	private String ptntusername;
	
	@Column(length=50,name="ptnt_pwd",unique=true,nullable= false)
	private String ptntpassword;
	
	
	
	
	public String getPtntusername() {
		return ptntusername;
	}
	public void setPtntusername(String ptntusername) {
		this.ptntusername = ptntusername;
	}
	public String getPtntpassword() {
		return ptntpassword;
	}
	public void setPtntpassword(String ptntpassword) {
		this.ptntpassword = ptntpassword;
	}
	//for appointment table 
	@JsonIgnore
	@OneToMany(mappedBy="pAppointments", cascade = CascadeType.ALL)
	private List<Appointment> appmtnts=new ArrayList<>();
	
	
	public List<Appointment> getAppmtnts() {
		return appmtnts;
	}
	public void setAppmtnts(List<Appointment> appmtnts) {
		this.appmtnts = appmtnts;
	}
		
	
	public Patient() {
		System.out.println("in ctor of "+getClass().getName());
	}
	public Integer getPatientid() {
		return patientid;
	}
	public void setPatientid(Integer patientid) {
		this.patientid = patientid;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getBloodgroup() {
		return bloodgroup;
	}
	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPatientemailID() {
		return patientemailID;
	}
	public void setPatientemailID(String patientemailID) {
		this.patientemailID = patientemailID;
	}
	
	public Long getContactno() {
		return contactno;
	}
	public void setContactno(Long contactno) {
		this.contactno = contactno;
	}
	@Override
	public String toString() {
		return "Patient [patientid=" + patientid + ", patientName=" + patientName + ", age=" + age + ", gender="
				+ gender + ", weight=" + weight + ", bloodgroup=" + bloodgroup + ", address=" + address
				+ ", patientemailID=" + patientemailID + ", contactno=" + contactno + ", ptntusername=" + ptntusername
				+ ", ptntpassword=" + ptntpassword + "]";
	}
	
	
}
