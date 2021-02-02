package com.app.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Patient;

public interface PatientRepository extends  JpaRepository< Patient ,Integer>{

	 Optional<Patient> findByPatientName(String ptntName);

	public Patient findByPatientemailID(String tempEmailId);

	 public Patient findByPtntusernameAndPtntpassword(String tempusername, String temppassword);
	
	

}
