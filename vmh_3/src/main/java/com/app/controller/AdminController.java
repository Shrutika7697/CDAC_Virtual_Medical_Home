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

import com.app.Repository.AdminRepository;
import com.app.cust_excs.ResourceNotFoundException;
import com.app.dto.ErrorResponse;
import com.app.dto.ResponseDTO;
import com.app.pojos.Admin;
import com.app.pojos.Patient;
import com.app.service.IAdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
@Validated
public class AdminController {

	@Autowired
	private IAdminService service;

	@Autowired
	private AdminRepository dao;


	// using repo
	@GetMapping
	public ResponseEntity<?> getAllAdminDetails() {
		List<Admin> admins = dao.findAll();
		// return new ResponseEntity<>(emps, HttpStatus.OK);
		return ResponseEntity.ok(admins);// sts code : 200 , body : list of emps
	}

	

	// using repository
	// get specific emp details
	@GetMapping("/{adminId}")
	public ResponseEntity<?> getEmpDetails(@PathVariable int adminId) {
		System.out.println("in get emp dtls " + adminId);
		Optional<Admin> optional = dao.findById(adminId);
		if (optional.isPresent())
			// return new ResponseEntity<>(optional.get(), HttpStatus.OK);
			return ResponseEntity.ok(optional.get());
		// invalid id
		ErrorResponse resp = new ErrorResponse("Emp Id Invalid", "Must Supply valid Emp Id");
		return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
	}


	// using repo
	@PostMapping
	public ResponseEntity<?> addAdminDetails(@RequestBody Admin e) {
		System.out.println("in add emp " + e);
		return new ResponseEntity<>(dao.save(e), HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
    public Admin  loginPatient(@RequestBody Admin admin) throws Exception
    {
    	String tempusername = admin.getAdminUsername();
    	String temppassword = admin.getAdminPassword();
    	Admin aobj=null;
    	if(tempusername != null && temppassword != null)
    	{
    		aobj = service.fetchAdminByAdminUsernameAndAdminPassword(tempusername,temppassword);
    	}
    	if(aobj == null)
    	{
    		throw new Exception ("Bad Credentials");
    	}
    	
    return aobj;
    }

	// using repo
	// delete emp details
	@DeleteMapping("/{adminId}")
	public ResponseEntity<?> deleteEmpDetails(@PathVariable int adminId) {
		System.out.println("in delete emp " + adminId);
		// check if emp exists
		Optional<Admin> optional = dao.findById(adminId);
		if (optional.isPresent()) {
			dao.deleteById(adminId);
			return new ResponseEntity<>(new ResponseDTO("Emp rec deleted with ID " + adminId), HttpStatus.OK);
		} else
			throw new ResourceNotFoundException("Emp ID Invalid : rec deletion failed");
		

	}

	// using repository
	@PutMapping("/{adminId}")
	public ResponseEntity<?> updateEmpDetails(@PathVariable int adminId, @RequestBody Admin admin) {
		System.out.println("in update emp " + adminId + " " + admin);
		// check if emp exists
		Optional<Admin> optional = dao.findById(adminId);
		if (optional.isPresent()) {
			// emp id valid : update the same
			Admin existingAdmin = optional.get();// DETACHED
			System.out.println("existing emp " + existingAdmin);
			// existingAdmin.setAdminid(admin.getAdminid());
			existingAdmin.setAdminUsername(admin.getAdminUsername());
			existingAdmin.setAdminPassword(admin.getAdminPassword());
			// update detached POJO
			return new ResponseEntity<>(dao.save(existingAdmin), HttpStatus.OK);
			// save or update (insert: transient(value of ID : default
			// or non default value BUT existing on DB -- update
		} else
			throw new ResourceNotFoundException("Emp ID Invalid");

	}
	
	
	
	
	
	
	
	
	

}
