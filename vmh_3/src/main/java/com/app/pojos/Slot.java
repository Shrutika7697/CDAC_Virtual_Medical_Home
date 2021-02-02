package com.app.pojos;

import java.io.Serializable;
import java.time.LocalDate;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="slot")
public class Slot implements Serializable  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="slotid")
	private Integer slotid;
	
	@Column(length = 30,name="slotname")
	private String slotname;
	
	public String getSlotname() {
		return slotname;
	}

	public void setSlotname(String slotname) {
		this.slotname = slotname;
	}

	@Column(name = "slot_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonProperty(value = "slot-date")
	private LocalDate slotDate;
	
	
	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "slot_status", length = 20, nullable = false)
	private Status slotstatus;

	

	public Slot() {
		System.out.println("in ctor of "+getClass().getName());
	}

	public Integer getSlotid() {
		return slotid;
	}

	public void setSlotid(Integer slotid) {
		this.slotid = slotid;
	}

	public LocalDate getSlotDate() {
		return slotDate;
	}

	public void setSlotDate(LocalDate slotDate) {
		this.slotDate = slotDate;
	}

	@ManyToOne
	@JoinColumn(name="dr_id",nullable=false)
	private Doctor doctorSlots;
	
	
	
	public Doctor getDoctorSlots() {
		return doctorSlots;
	}

	public void setDoctorSlots(Doctor doctorSlots) {
		this.doctorSlots = doctorSlots;
	}

	public Status getSlotstatus() {
		return slotstatus;
	}

	public void setSlotstatus(Status slotstatus) {
		this.slotstatus = slotstatus;
	}

	@Override
	public String toString() {
		return "Slot [slotid=" + slotid + ", slotname=" + slotname + ", slotDate=" + slotDate + ", slotstatus="
				+ slotstatus + "]";
	}

	
	
	

}
