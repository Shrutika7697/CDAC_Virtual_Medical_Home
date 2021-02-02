package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.Repository.PatientRepository;
import com.app.pojos.Patient;

@Service
@Transactional
public class PatientServiceImpl implements IPatientService {
	
	@Autowired
	private PatientRepository dao;
	
	@Override
	 public Patient addPtntDetails(Patient ptnt)
	{
		return dao.save(ptnt);
	}

	@Override
	public Patient fetchPatientByEmailID(String tempEmailId) {
		 return dao.findByPatientemailID(tempEmailId);
		
	}

	@Override
	public Patient fetchPatientByPtntusernameAndPtntpassword(String tempusername, String temppassword) {
		// TODO Auto-generated method stub
		return dao.findByPtntusernameAndPtntpassword(tempusername,temppassword);
	}

}
