package com.app.pojos;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="appointment")
public class Appointment  implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="aptmnt_id")
	private Integer appointmentid;
	
	@ManyToOne
	@JoinColumn(name="dr_id",nullable=false)
	private Doctor doctorAptmnts;
	
	@ManyToOne
	@JoinColumn(name="p_id",nullable=false)
	private Patient pAppointments;
	
	public Doctor getDoctorAptmnts() {
		return doctorAptmnts;
	}

	public void setDoctorAptmnts(Doctor doctorAptmnts) {
		this.doctorAptmnts = doctorAptmnts;
	}
	
	public Patient getpAppointments() {
		return pAppointments;
	}

	public void setpAppointments(Patient pAppointments) {
		this.pAppointments = pAppointments;
	}

	@Column(length = 90,name="illness")
	private String illness;
	
	@Column(length = 90,name="prescriptions")
	private String prescriptions;

	public Appointment() {
		System.out.println("in ctor of "+getClass().getName());
	}

	public Integer getAppointmentid() {
		return appointmentid;
	}

	public void setAppointmentid(Integer appointmentid) {
		this.appointmentid = appointmentid;
	}

	public String getIllness() {
		return illness;
	}

	public void setIllness(String illness) {
		this.illness = illness;
	}

	public String getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(String prescriptions) {
		this.prescriptions = prescriptions;
	}

	@Override
	public String toString() {
		return "Appointment [appointmentid=" + appointmentid + ", doctorAptmnts=" + doctorAptmnts 
				+ ", pAppointments=" + pAppointments + ", illness=" + illness + ", prescriptions=" + prescriptions
				+ "]";
	}

}
