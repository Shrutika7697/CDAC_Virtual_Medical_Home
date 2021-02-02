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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "doctor_list")
public class Doctor implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dr_id")
	private Integer drid;

	@Column(length = 30, name = "drName")
	private String drName;

	@Column(length = 30, name = "designation")
	private String designation;

	@Column(name = "gender", nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "dr_cost")
	private double consultationCost;

	@Column(length = 60, name = "dr_expce")
	private String experience;

	@Column(length = 50, name = "dr_emailid", unique = true, nullable = false)
	private String emailID;

	@Column(length = 50, name = "dr_uname", unique = true, nullable = false)
	private String drusername;

	@Column(length = 50, name = "dr_pwd", unique = true, nullable = false)
	private String drpassword;

	@ManyToOne
	@JoinColumn(name = "specializationId", nullable = false)
	private Specialization specializtnDr;

	// for slot table
	@JsonIgnore
	@OneToMany(mappedBy = "doctorSlots", cascade = CascadeType.ALL)
	private List<Slot> slots = new ArrayList<>();

	// for feedback table
	@JsonIgnore
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
	private List<FeedBack> drFdbckList = new ArrayList<>();

	// for appointment table
	@JsonIgnore
	@OneToMany(mappedBy = "doctorAptmnts", cascade = CascadeType.ALL)
	private List<Appointment> alldrAptmnts = new ArrayList<>();

	public Doctor() {
		System.out.println("in ctor of " + getClass().getName());
	}

	public String getDrusername() {
		return drusername;
	}

	public void setDrusername(String drusername) {
		this.drusername = drusername;
	}

	public String getDrpassword() {
		return drpassword;
	}

	public void setDrpassword(String drpassword) {
		this.drpassword = drpassword;
	}

	public Specialization getSpecializtnDr() {
		return specializtnDr;
	}

	public void setSpecializtnDr(Specialization specializtnDr) {
		this.specializtnDr = specializtnDr;
	}

	public List<Slot> getSlots() {
		System.out.println("abcd");
		return slots;
	}

	public void setSlots(List<Slot> slots) {
		this.slots = slots;
	}

	public List<FeedBack> getDrFdbckList() {
		return drFdbckList;
	}

	public void setDrFdbckList(List<FeedBack> drFdbckList) {
		this.drFdbckList = drFdbckList;
	}

	public List<Appointment> getAlldrAptmnts() {
		return alldrAptmnts;
	}

	public void setAlldrAptmnts(List<Appointment> alldrAptmnts) {
		this.alldrAptmnts = alldrAptmnts;
	}

	public Integer getDrid() {
		return drid;
	}

	public void setDrid(Integer drid) {
		this.drid = drid;
	}

	public String getDrName() {
		return drName;
	}

	public void setDrName(String drName) {
		this.drName = drName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public double getConsultationCost() {
		return consultationCost;
	}

	public void setConsultationCost(double consultationCost) {
		this.consultationCost = consultationCost;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	@Override
	public String toString() {
		return "Doctor [drid=" + drid + ", drName=" + drName + ", designation=" + designation + ", gender=" + gender
				+ ", consultationCost=" + consultationCost + ", experience=" + experience + ", emailID=" + emailID
				+ ", drusername=" + drusername + ", drpassword=" + drpassword + ", slots=" + slots + ", drFdbckList="
				+ drFdbckList + ", alldrAptmnts=" + alldrAptmnts + "]";
	}

}
