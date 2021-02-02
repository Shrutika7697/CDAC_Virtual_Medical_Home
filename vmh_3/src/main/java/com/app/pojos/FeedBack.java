package com.app.pojos;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="feedBack")
public class FeedBack  implements Serializable  {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="f_id")
	private Integer feedbackid;
	
	@Column(length = 80,name="fdbck_details")
	private String feedbackDetails;
	
	@Column(name="rating")
	private Integer rating;
	
	public FeedBack() {
		System.out.println("in ctor of "+getClass().getName());
	}

	public Integer getFeedbackid() {
		return feedbackid;
	}

	public void setFeedbackid(Integer feedbackid) {
		this.feedbackid = feedbackid;
	}

	public String getFeedbackDetails() {
		return feedbackDetails;
	}

	public void setFeedbackDetails(String feedbackDetails) {
		this.feedbackDetails = feedbackDetails;
	}
	
	@ManyToOne
	@JoinColumn(name = "dr_id", nullable = false)
	private Doctor doctor;
	
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "FeedBack [feedbackid=" + feedbackid + ", feedbackDetails=" + feedbackDetails + ", doctor=" + doctor
				+ "]";
	}

}
