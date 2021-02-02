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

import com.app.Repository.SpecializationRepository;
import com.app.cust_excs.ResourceNotFoundException;
import com.app.dto.ErrorResponse;
import com.app.dto.ResponseDTO;
import com.app.pojos.Specialization;
import com.app.service.ISpeclztnService;

@RestController
@RequestMapping("/specialization")
@CrossOrigin(origins = "http://localhost:4200")
@Validated
public class SpecializationController {

	@Autowired
	private SpecializationRepository spcltnRepo;

	@Autowired
	private ISpeclztnService service;

	// using repo
	@GetMapping
	public ResponseEntity<?> getAllSpecializationDetails() {
		List<Specialization> spclztns = spcltnRepo.findAll();
		
		return ResponseEntity.ok(spclztns);
	}

	// using repository
	
	@GetMapping("/{spclztnID}")
	public ResponseEntity<?> getSpecializtnDetails(@PathVariable int spclztnID) {
		System.out.println("in get emp dtls " + spclztnID);
		Optional<Specialization> optional = spcltnRepo.findById(spclztnID);
		if (optional.isPresent())
			
			return ResponseEntity.ok(optional.get());
		
		ErrorResponse resp = new ErrorResponse("Emp Id Invalid", "Must Supply valid Emp Id");
		return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
	}

	// using repo
	@PostMapping
	public ResponseEntity<?> addSpclztnDetails(@RequestBody Specialization s) {
		System.out.println("in add emp " + s);
		return new ResponseEntity<>(spcltnRepo.save(s), HttpStatus.CREATED);
	}

	// using repo
	@DeleteMapping("/{spclztnID}")
	public ResponseEntity<?> deleteEmpDetails(@PathVariable int spclztnID) {
		System.out.println("in delete emp " + spclztnID);
		
		Optional<Specialization> optional = spcltnRepo.findById(spclztnID);
		if (optional.isPresent()) {
			spcltnRepo.deleteById(spclztnID);
			return new ResponseEntity<>(new ResponseDTO("Emp rec deleted with ID " + spclztnID), HttpStatus.OK);
		} else
			throw new ResourceNotFoundException("Emp ID Invalid : rec deletion failed");
		// throw new RuntimeException("my own err mesg");

	}

}
