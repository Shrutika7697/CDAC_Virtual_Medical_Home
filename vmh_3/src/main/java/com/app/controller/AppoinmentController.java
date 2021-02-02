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

import com.app.Repository.AppointmentRepository;
import com.app.cust_excs.ResourceNotFoundException;
import com.app.dto.ErrorResponse;
import com.app.dto.ResponseDTO;
import com.app.pojos.Appointment;
import com.app.pojos.Doctor;

import com.app.pojos.Slot;
import com.app.service.IAppointmentService;


@RestController
@RequestMapping("/appointment")
@CrossOrigin(origins = "http://localhost:4200")
@Validated
public class AppoinmentController {

	@Autowired
	private AppointmentRepository appRepo;
	
	@Autowired
	private IAppointmentService service;
	
	

	// using repo
	@GetMapping
	public ResponseEntity<?> getAllApptmntDetails() {
		List<Appointment> applist = appRepo.findAll();
		
		return ResponseEntity.ok(applist);
	}

	// using repository
	
	@GetMapping("/{appID}")
	public ResponseEntity<?> getApptmntDetails(@PathVariable int appID) {
		System.out.println("in get emp dtls " + appID);
		Optional<Appointment> optional = appRepo.findById(appID);
		if (optional.isPresent())
			
			return ResponseEntity.ok(optional.get());
		
		ErrorResponse resp = new ErrorResponse("Emp Id Invalid", "Must Supply valid Emp Id");
		return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
	}
	

	// using repo
	@PostMapping
	public ResponseEntity<?> addAppointmentsDetails(@RequestBody Appointment e) {
		System.out.println("in add emp " + e);
		return new ResponseEntity<>(appRepo.save(e), HttpStatus.CREATED);
	}

	
	@DeleteMapping("/{apptmntID}")
	public ResponseEntity<?> deleteEmpDetails(@PathVariable int apptmntID) {
		System.out.println("in delete emp " + apptmntID);
		
		Optional<Appointment> optional = appRepo.findById(apptmntID);
		if (optional.isPresent()) {
			appRepo.deleteById(apptmntID);
			return new ResponseEntity<>(new ResponseDTO("Emp rec deleted with ID " + apptmntID), HttpStatus.OK);
		} else
			throw new ResourceNotFoundException("Emp ID Invalid : rec deletion failed");
		// throw new RuntimeException("my own err mesg");

	}
	
	@PutMapping("/prescription/{apptmntID}")
	public ResponseEntity<?> updatePrescriptions(@PathVariable int apptmntID, @RequestBody Appointment a) throws Exception {
		System.out.println("in update of appointments " + apptmntID + " " + a);
		try {
			Appointment updatedDetails = service.updateAppointmentDetails(apptmntID, a);
			return new ResponseEntity<>(updatedDetails, HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/illness/{apptmntID}")
	public ResponseEntity<?> updateIllness(@PathVariable int apptmntID, @RequestBody Appointment a) throws Exception {
		System.out.println("in update of appointments " + apptmntID + " " + a);
		try {
			Appointment updatedDetails = service.updateIllness(apptmntID, a);
			return new ResponseEntity<>(updatedDetails, HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
