package com.app.service;

import java.time.LocalDate;
import java.util.List;


import java.util.Optional;

import com.app.pojos.Admin;
import com.app.pojos.Doctor;
import com.app.pojos.Slot;



public interface IDoctorService {
	
	// list all dr
		List<Doctor> getAllDr();

		Doctor authenticateUser(String drusername,String drpassword);
		
	
		Optional<Doctor> getDrDetails(String drName);

	
			Doctor addDrDetails(Doctor transientPOJO);

			Doctor fetchDoctorByEmailID(String tempEmailId);

			Doctor fetchDoctorByDrusernameAndDrpassword(String tempusername, String temppassword);

			List<Doctor> getAllDoctor();

			List<Slot> getSlotByDate(LocalDate parse);

}
