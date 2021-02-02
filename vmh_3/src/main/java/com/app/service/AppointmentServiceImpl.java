package com.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.Repository.AppointmentRepository;

import com.app.pojos.Appointment;


@Service
@Transactional
public class AppointmentServiceImpl implements IAppointmentService {
	
	@Autowired
	private AppointmentRepository appRepo;


	@Override
	public Appointment updateAppointmentDetails(int apptmntID, Appointment a) throws Exception {
		
				Optional<Appointment> p = appRepo.findById(apptmntID);
				if (p.isPresent()) {
					
					Appointment app = p.get();
					app.setPrescriptions(a.getPrescriptions());
					
					return app;
	}
				throw new Exception("Invalid Product ID");
	}


	@Override
	public Appointment updateIllness(int apptmntID, Appointment a) throws Exception {
		Optional<Appointment> p = appRepo.findById(apptmntID);
		if (p.isPresent()) {
			
			Appointment app = p.get();
			app.setIllness(a.getIllness());
			
			return app;
}
		throw new Exception("Invalid Product ID");
}
}

