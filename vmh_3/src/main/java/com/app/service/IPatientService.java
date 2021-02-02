package com.app.service;

import com.app.pojos.Patient;

public interface IPatientService {

	Patient addPtntDetails(Patient ptnt);

	Patient fetchPatientByEmailID(String tempEmailId);

	Patient fetchPatientByPtntusernameAndPtntpassword(String tempusername, String temppassword);

}
