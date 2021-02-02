package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Repository.PatientRepository;
import com.app.cust_excs.ResourceNotFoundException;
import com.app.dto.ErrorResponse;
import com.app.dto.ResponseDTO;
import com.app.pojos.Doctor;
import com.app.pojos.Patient;
import com.app.service.IPatientService;

@RestController
@RequestMapping("/patient")
@CrossOrigin(origins = "http://localhost:4200")
@Validated
public class PatientController {

	@Autowired
	private PatientRepository ptntRepo;
	
	@Autowired
	private IPatientService service;

	// using repo
	@GetMapping
	public ResponseEntity<?> getAllptntDetails() {
		List<Patient> patients = ptntRepo.findAll();
		
		return ResponseEntity.ok(patients);
	}

	// using repository
	@GetMapping("/{ptntID}")
	public ResponseEntity<?> getDoctorDetails(@PathVariable int ptntID) {
		System.out.println("in get emp dtls " + ptntID);
		Optional<Patient> optional = ptntRepo.findById(ptntID);
		if (optional.isPresent())
			
			return ResponseEntity.ok(optional.get());
		
		ErrorResponse resp = new ErrorResponse("Emp Id Invalid", "Must Supply valid Emp Id");
		return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
	}

	
	// using repo
		@PostMapping
		public ResponseEntity<?> addpatientDetails(@RequestBody Patient p) {
			System.out.println("in add emp " + p);
			return new ResponseEntity<>(ptntRepo.save(p), HttpStatus.CREATED);
		}
		
	
	 @PostMapping("/registerPtnt")
	    public Patient registerDr(@RequestBody Patient ptnt) throws Exception
	    {
	    	String tempEmailId= ptnt.getPatientemailID();
	    	if(tempEmailId !=null && !"".equals(tempEmailId)) {
	    		Patient pobj = service.fetchPatientByEmailID(tempEmailId);
	    		if(pobj != null)
	    		{
	    			throw new Exception("Doctor with"+tempEmailId+" is already exist");
	    			
	    			//return "Patient with"+tempEmailId+" is already exist";
	    			
	    		}
	    		
	    	}
	    	Patient pobj=null;
 		pobj=service.addPtntDetails(ptnt);
 		return pobj;
 		//return "Successfully registered";
	    }
	 
	 @PostMapping("/login")
	    public Patient  loginPatient(@RequestBody Patient ptnt) throws Exception
	    {
	    	String tempusername = ptnt.getPtntusername();
	    	String temppassword = ptnt.getPtntpassword();
	    	Patient pobj=null;
	    	if(tempusername != null && temppassword != null)
	    	{
	    		pobj = service.fetchPatientByPtntusernameAndPtntpassword(tempusername,temppassword);
	    	}
	    	if(pobj == null)
	    	{
	    		throw new Exception ("Bad Credentials");
	    	}
	    	
	    return pobj;
	    }
	
	
	
	
	// using repo
	@DeleteMapping("/{ptntID}")
	public ResponseEntity<?> deleteEmpDetails(@PathVariable int ptntID) {
		System.out.println("in delete emp " + ptntID);
		
		Optional<Patient> optional = ptntRepo.findById(ptntID);
		if (optional.isPresent()) {
			ptntRepo.deleteById(ptntID);
			return new ResponseEntity<>(new ResponseDTO("Emp rec deleted with ID " + ptntID), HttpStatus.OK);
		} else
			throw new ResourceNotFoundException("Emp ID Invalid : rec deletion failed");
		// throw new RuntimeException("my own err mesg");

	}
	
	@GetMapping("/pname/{ptntName}")
	public ResponseEntity<?> getPtntDetailsbyName(@PathVariable String ptntName) {
		System.out.println("in get org dtls " + ptntName);
		Optional<Patient> optional = ptntRepo.findByPatientName(ptntName);
		if (optional.isPresent())
	
			return ResponseEntity.ok(optional.get());
		
		ErrorResponse resp = new ErrorResponse("orgType Invalid", "Must Supply valid orgType");
		return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{ptntId}")
	public ResponseEntity<?> updateEmpDetails(@PathVariable int ptntId, @RequestBody  Patient p) {
		System.out.println("in update emp " + ptntId + " " + p);
		
		Optional<Patient> optional = ptntRepo.findById(ptntId);
		if (optional.isPresent()) {
			
			Patient existingPtnt = optional.get();// DETACHED
			System.out.println("existing emp " + existingPtnt);
			existingPtnt.setPatientName(p.getPatientName());
			existingPtnt.setPatientemailID(p.getPatientemailID());
			existingPtnt.setPtntpassword(p.getPtntpassword());
			existingPtnt.setPtntusername(p.getPtntusername());
			existingPtnt.setWeight(p.getWeight());
			existingPtnt.setAddress(p.getAddress());
			existingPtnt.setAge(p.getAge());
			existingPtnt.setBloodgroup(p.getBloodgroup());
			existingPtnt.setContactno(p.getContactno());
			existingPtnt.setGender(p.getGender());
			  
			
			return new ResponseEntity<>(ptntRepo.save(existingPtnt), HttpStatus.OK);
		
		} else
			throw new ResourceNotFoundException("Emp ID Invalid");

	}

	
	
	
	
	
	
	
	
	
	
	
	
}
