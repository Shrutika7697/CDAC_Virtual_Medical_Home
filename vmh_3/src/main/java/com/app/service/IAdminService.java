package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.pojos.Admin;



public interface IAdminService {
	
	
	// list all admin
		List<Admin> getAllAdmin();
		
		// get admin details by id
		Optional<Admin> getAdminDetails(Integer adminId);

	// add new admin details
		Admin addAdminDetails(Admin transientPOJO);

		Admin fetchAdminByAdminUsernameAndAdminPassword(String tempusername, String temppassword);


}
