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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Repository.FeedBackRepository;
import com.app.cust_excs.ResourceNotFoundException;
import com.app.dto.ErrorResponse;
import com.app.dto.ResponseDTO;
import com.app.pojos.Appointment;
import com.app.pojos.FeedBack;

@RestController
@RequestMapping("/feedback")
@CrossOrigin(origins = "http://localhost:4200")
@Validated
public class FeedBackController {

	@Autowired
	private FeedBackRepository fdbckRepo;

	// using repo
	@GetMapping
	public ResponseEntity<?> getAllFeedBckDetails() {
		List<FeedBack> fdbck = fdbckRepo.findAll();
		
		return ResponseEntity.ok(fdbck);
	}

	// using repository
	@GetMapping("/{fdbckId}")
	public ResponseEntity<?> getEmpDetails(@PathVariable int fdbckId) {
		System.out.println("in get emp dtls " + fdbckId);
		Optional<FeedBack> optional = fdbckRepo.findById(fdbckId);
		if (optional.isPresent())
			
			return ResponseEntity.ok(optional.get());
		
		ErrorResponse resp = new ErrorResponse("Emp Id Invalid", "Must Supply valid Emp Id");
		return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
	}

	// using repo
	@PostMapping
	public ResponseEntity<?> addFdbckDetails(@RequestBody FeedBack e) {
		System.out.println("in add emp " + e);
		return new ResponseEntity<>(fdbckRepo.save(e), HttpStatus.CREATED);
	}

	// using repo
	@DeleteMapping("/{fdbckId}")
	public ResponseEntity<?> deleteEmpDetails(@PathVariable int fdbckId) {
		System.out.println("in delete emp " + fdbckId);
	
		Optional<FeedBack> optional = fdbckRepo.findById(fdbckId);
		if (optional.isPresent()) {
			fdbckRepo.deleteById(fdbckId);
			return new ResponseEntity<>(new ResponseDTO("Emp rec deleted with ID " + fdbckId), HttpStatus.OK);
		} else
			throw new ResourceNotFoundException("Emp ID Invalid : rec deletion failed");
		// throw new RuntimeException("my own err mesg");

	}

}
