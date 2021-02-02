package com.app.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="specialization")
public class Specialization implements Serializable  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="specializationId")
	private Integer specializationId;
	
	@Column(length = 30,name="spclztn_name",unique=true)
	private String specializationName;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "specializtnDr", cascade = CascadeType.ALL)
	private List<Doctor> doctors=new ArrayList<>();
	

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	public Specialization() {
		System.out.println("in ctor of "+getClass().getName());
	}
	
    public Specialization(Integer specializationId, String specializationName) {
		super();
		this.specializationId = specializationId;
		this.specializationName = specializationName;
	}

	public Integer getSpecializationId() {
		return specializationId;
	}

	public void setSpecializationId(Integer specializationId) {
		this.specializationId = specializationId;
	}

	public String getSpecializationName() {
		return specializationName;
	}

	public void setSpecializationName(String specializationName) {
		this.specializationName = specializationName;
	}

	@Override
	public String toString() {
		return "Specialization [specializationId=" + specializationId + ", specializationName=" + specializationName
				+ ", doctors=" + doctors + "]";
	}

	
	

}
