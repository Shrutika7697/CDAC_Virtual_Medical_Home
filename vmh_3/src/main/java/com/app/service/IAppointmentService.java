package com.app.service;

import com.app.pojos.Appointment;

public interface IAppointmentService {

	Appointment updateAppointmentDetails(int apptmntID, Appointment a) throws Exception;

	//Appointment updateIllnessDetails(int apptmntID, Appointment a) throws Exception;

	Appointment updateIllness(int apptmntID, Appointment a) throws Exception;

}
