package com.app.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Doctor;


public interface DoctorRepository  extends  JpaRepository< Doctor ,Integer>{

	Optional<Doctor> findByDrName(String doctorName);
	

	public Doctor findByEmailID(String email);

	 public Doctor findByDrusernameAndDrpassword(String tempusername, String temppassword);
		
}
