package com.app.controller;

import java.time.LocalDate;

import java.util.List;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.Repository.DoctorRepository;
import com.app.Repository.SpecializationRepository;
import com.app.cust_excs.ResourceNotFoundException;
import com.app.dto.ErrorResponse;
import com.app.dto.ResponseDTO;
import com.app.pojos.Admin;
import com.app.pojos.Doctor;

import com.app.pojos.Slot;
import com.app.pojos.Specialization;
import com.app.service.IDoctorService;

@RestController
@RequestMapping("/doctor")
@CrossOrigin(origins = "http://localhost:4200")
@Validated
public class DoctorController {

	@Autowired
	private DoctorRepository drRepo;

	@Autowired
	private IDoctorService service;

	@Autowired
	private SpecializationRepository spcltnRepo;

	// RESTful end point or API end point or API provider
	@GetMapping
	public ResponseEntity<?> listAllDoctor() {
		System.out.println("in list all doctor");
		// invoke service layer's method : controller --> service impl (p) --->JPA
		// repo's impl class(SC)
		List<Doctor> doctors = service.getAllDoctor();
		if (doctors.isEmpty())// chck size
			// empty product list : set sts code : HTTP 204 (no contents)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);// HttpStatus is a ENUM
		// in case of non empty list : OK, send the list
		return new ResponseEntity<>(doctors, HttpStatus.OK);
	}

	// using repository
	@GetMapping("/{doctorID}")
	public ResponseEntity<?> getDoctorDetails(@PathVariable int doctorID) {
		System.out.println("in get emp dtls " + doctorID);
		Optional<Doctor> optional = drRepo.findById(doctorID);
		if (optional.isPresent())
			// return new ResponseEntity<>(optional.get(), HttpStatus.OK);
			return ResponseEntity.ok(optional.get());
		// invalid id
		ErrorResponse resp = new ErrorResponse("Emp Id Invalid", "Must Supply valid Emp Id");
		return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/name/{doctorName}")
	public ResponseEntity<?> getDrDetails(@PathVariable String doctorName) {
		System.out.println("in get emp dtls " + doctorName);
		Optional<Doctor> optional = drRepo.findByDrName(doctorName);
		if (optional.isPresent())
			// return new ResponseEntity<>(optional.get(), HttpStatus.OK);
			return ResponseEntity.ok(optional.get());
		// invalid id
		ErrorResponse resp = new ErrorResponse("Emp Id Invalid", "Must Supply valid Emp Id");
		return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
	}

	/*
	@GetMapping("/date/{slotDate}")
	public ResponseEntity<?> getSlotDetailsByDate(@PathVariable String slotDate) {
		System.out.println("in get emp dtls " + slotDate);

		List<Slot> optional = service.getSlotByDate(LocalDate.parse(slotDate));
		if (optional == null)
			// return new ResponseEntity<>(optional.get(), HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		// invalid id
		// ErrorResponse resp = new ErrorResponse("Emp Id Invalid", "Must Supply valid
		// Emp Id");
		return new ResponseEntity<>(optional, HttpStatus.OK);

	}
	*/

	// using repo
	@PostMapping("/updateDoctor")
	public ResponseEntity<?> addDoctoDetails(@RequestBody Doctor e) {
		System.out.println("in add emp " + e);
		return new ResponseEntity<>(drRepo.save(e), HttpStatus.CREATED);
	}

	@PostMapping("/registerDr")
	public Doctor registerDr(@RequestBody Doctor doctor) throws Exception {
		String tempEmailId = doctor.getEmailID();
		if (tempEmailId != null && !"".equals(tempEmailId)) {
			Doctor drobj = service.fetchDoctorByEmailID(tempEmailId);
			if (drobj != null) {
				throw new Exception("Doctor with" + tempEmailId + " is already exist");

				// return "Doctor with"+tempEmailId+" is already exist";

			}

		}
		Doctor drobj = null;
		drobj = service.addDrDetails(doctor);
		return drobj;
		// return "Successfully registered";
	}

	@PostMapping("/login")
	public Doctor loginDoctor(@RequestBody Doctor doctor) throws Exception {
		String tempusername = doctor.getDrusername();
		String temppassword = doctor.getDrpassword();
		Doctor drobj = null;
		if (tempusername != null && temppassword != null) {
			drobj = service.fetchDoctorByDrusernameAndDrpassword(tempusername, temppassword);
		}
		if (drobj == null) {
			throw new Exception("Bad Credentials");
		}

		return drobj;
	}

	// using repo
	@DeleteMapping("/{drId}")
	public ResponseEntity<?> deleteEmpDetails(@PathVariable int drId) {
		System.out.println("in delete emp " + drId);
		// check if emp exists
		Optional<Doctor> optional = drRepo.findById(drId);
		if (optional.isPresent()) {
			drRepo.deleteById(drId);
			return new ResponseEntity<>(new ResponseDTO("Emp rec deleted with ID " + drId), HttpStatus.OK);
		} else
			throw new ResourceNotFoundException("Emp ID Invalid : rec deletion failed");
		// throw new RuntimeException("my own err mesg");

	}

	@PutMapping("/{drId}")
	public ResponseEntity<?> updateEmpDetails(@PathVariable int drId, @RequestBody Doctor dr) {
		System.out.println("in update emp " + drId + " " + dr);
		// check if emp exists
		Optional<Doctor> optional = drRepo.findById(drId);
		if (optional.isPresent()) {
			// emp id valid : update the same
			Doctor existingDr = optional.get();// DETACHED
			System.out.println("existing emp " + existingDr);
			existingDr.setDrName(dr.getDrName());
			existingDr.setEmailID(dr.getEmailID());
			existingDr.setExperience(dr.getExperience());
			existingDr.setGender(dr.getGender());
			existingDr.setConsultationCost(dr.getConsultationCost());
			existingDr.setDesignation(dr.getDesignation());
			existingDr.setSpecializtnDr(dr.getSpecializtnDr());
			existingDr.setDrusername(dr.getDrusername());
			existingDr.setDrpassword(dr.getDrpassword());

			// update detached POJO
			return new ResponseEntity<>(drRepo.save(existingDr), HttpStatus.OK);
			// save or update (insert: transient(value of ID : default
			// or non default value BUT existing on DB -- update
		} else
			throw new ResourceNotFoundException("Emp ID Invalid");

	}

}
