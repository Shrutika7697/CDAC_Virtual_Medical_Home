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

import com.app.Repository.LoginRepository;
import com.app.cust_excs.ResourceNotFoundException;
import com.app.dto.ErrorResponse;
import com.app.dto.ResponseDTO;
import com.app.pojos.Login;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200")
@Validated
public class LoginController {

	@Autowired
	private LoginRepository lgnRepo;

	// using repo
	@GetMapping
	public ResponseEntity<?> getAllLgnDetails() {
		List<Login> logins = lgnRepo.findAll();
		// return new ResponseEntity<>(emps, HttpStatus.OK);
		return ResponseEntity.ok(logins);// sts code : 200 , body : list of emps
	}

	// using repository
	@GetMapping("/{lgnID}")
	public ResponseEntity<?> getLgnDetails(@PathVariable int lgnID) {
		System.out.println("in get emp dtls " + lgnID);
		Optional<Login> optional = lgnRepo.findById(lgnID);
		if (optional.isPresent())
			// return new ResponseEntity<>(optional.get(), HttpStatus.OK);
			return ResponseEntity.ok(optional.get());
		// invalid id
		ErrorResponse resp = new ErrorResponse("Emp Id Invalid", "Must Supply valid Emp Id");
		return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
	}

	
	// using repo
	@PostMapping
	public ResponseEntity<?> addLoginDetails(@RequestBody Login e) {
		System.out.println("in add emp " + e);
		return new ResponseEntity<>(lgnRepo.save(e), HttpStatus.CREATED);
	}

	// using repo
	@DeleteMapping("/{lgnId}")
	public ResponseEntity<?> deleteEmpDetails(@PathVariable int lgnId) {
		System.out.println("in delete emp " + lgnId);
		// check if emp exists
		Optional<Login> optional = lgnRepo.findById(lgnId);
		if (optional.isPresent()) {
			lgnRepo.deleteById(lgnId);
			return new ResponseEntity<>(new ResponseDTO("Emp rec deleted with ID " + lgnId), HttpStatus.OK);
		} else
			throw new ResourceNotFoundException("Emp ID Invalid : rec deletion failed");
		// throw new RuntimeException("my own err mesg");

	}

	// using repository
	@PutMapping("/{lgnId}")
	public ResponseEntity<?> updateEmpDetails(@PathVariable int lgnId, @RequestBody Login login) {
		System.out.println("in update emp " + lgnId + " " + login);
		// check if emp exists
		Optional<Login> optional = lgnRepo.findById(lgnId);
		if (optional.isPresent()) {
			// emp id valid : update the same
			Login existingLogin = optional.get();// DETACHED
			System.out.println("existing emp " + existingLogin);
			// existingAdmin.setAdminid(admin.getAdminid());
			existingLogin.setLoginusrnme(login.getLoginusrnme());
			existingLogin.setLoginpwd(login.getLoginpwd());
			existingLogin.setRole(login.getRole());
			// update detached POJO
			return new ResponseEntity<>(lgnRepo.save(existingLogin), HttpStatus.OK);
			// save or update (insert: transient(value of ID : default
			// or non default value BUT existing on DB -- update
		} else
			throw new ResourceNotFoundException("Emp ID Invalid");

	}

}
