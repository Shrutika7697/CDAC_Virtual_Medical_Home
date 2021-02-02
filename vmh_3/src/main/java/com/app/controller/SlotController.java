package com.app.controller;

import java.time.LocalDate;
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

import com.app.Repository.DoctorRepository;
import com.app.Repository.SlotRepository;
import com.app.cust_excs.ResourceNotFoundException;
import com.app.dto.ErrorResponse;
import com.app.dto.ResponseDTO;
import com.app.pojos.Doctor;
import com.app.pojos.Slot;
import com.app.pojos.Status;
import com.app.service.ISlotService;

@RestController
@RequestMapping("/slot")
@CrossOrigin(origins = "http://localhost:4200")
@Validated
public class SlotController {

	@Autowired
	private SlotRepository slotRepo;

	@Autowired
	private ISlotService service;
	
	@Autowired
	private DoctorRepository drRepo;

	// using repo
	@GetMapping
	public ResponseEntity<?> getAllSlotDetails() {
		List<Slot> slotlist = slotRepo.findAll();
		
		return ResponseEntity.ok(slotlist);
	}

	// using repository
	
	@GetMapping("/{slotID}")
	public ResponseEntity<?> getSlotDetailsById(@PathVariable int slotID) {
		System.out.println("in get emp dtls " + slotID);
		Optional<Slot> optional = slotRepo.findById(slotID);
		if (optional.isPresent())
			
			return ResponseEntity.ok(optional.get());
		
		ErrorResponse resp = new ErrorResponse("Emp Id Invalid", "Must Supply valid Emp Id");
		return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/date/{slotDate}")
	public ResponseEntity<?> getSlotDetailsByDate(@PathVariable String slotDate) {
		System.out.println("in get emp dtls " + slotDate);
		
		Slot s= new Slot();
		Enum<Status> sp=s.getSlotstatus().AVAILABLE;
	
		List<Slot> optional = service.getSlotByDate(LocalDate.parse(slotDate),sp);
		
		if (optional == null)
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<>(optional, HttpStatus.OK);
			}
			

	// using repo
	@PostMapping
	public ResponseEntity<?> addslotDetails(@RequestBody Slot p) {
		System.out.println("in add emp " + p);
		return new ResponseEntity<>(slotRepo.save(p), HttpStatus.CREATED);
	}

	// using repo
	@DeleteMapping("/{slotID}")
	public ResponseEntity<?> deleteEmpDetails(@PathVariable int slotID) {
		System.out.println("in delete emp " + slotID);
		
		Optional<Slot> optional = slotRepo.findById(slotID);
		if (optional.isPresent()) {
			slotRepo.deleteById(slotID);
			return new ResponseEntity<>(new ResponseDTO("Emp rec deleted with ID " + slotID), HttpStatus.OK);
		} else
			throw new ResourceNotFoundException("Emp ID Invalid : rec deletion failed");
		

	}
	
	@PutMapping("/{slotID}")
	public ResponseEntity<?> updateEmpDetails(@PathVariable int slotID, @RequestBody Slot s) {
		System.out.println("in update slot " + slotID + " " + s);
		
		Optional<Slot> optional = slotRepo.findById(slotID);
		if (optional.isPresent()) {
			
			Slot existingSlot = optional.get();// DETACHED
			System.out.println("existing emp " + existingSlot);
			existingSlot.setSlotstatus(s.getSlotstatus());
			

			
			return new ResponseEntity<>(slotRepo.save(existingSlot), HttpStatus.OK);
			
		} else
			throw new ResourceNotFoundException("Slot ID Invalid");

	}

}
