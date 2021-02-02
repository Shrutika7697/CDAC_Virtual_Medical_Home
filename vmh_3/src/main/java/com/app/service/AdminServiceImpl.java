package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.Repository.AdminRepository;
import com.app.pojos.Admin;


@Service
@Transactional
public class AdminServiceImpl implements IAdminService {
	
	@Autowired
	private AdminRepository dao;
	
	@Override
	public List<Admin> getAllAdmin() {
		System.out.println("dao imple class " + dao.getClass().getName());
		return dao.findAll();
	}
	
	@Override
	public Optional<Admin> getAdminDetails(Integer adminId) {
		// TODO Auto-generated method stub
		return dao.findById(adminId);
	}


	@Override
	public Admin addAdminDetails(Admin transientPOJO) {
		// TODO Auto-generated method stub
		System.out.println(transientPOJO);
		return dao.save(transientPOJO);
	}

	@Override
	public Admin fetchAdminByAdminUsernameAndAdminPassword(String tempusername, String temppassword) {
		// TODO Auto-generated method stub
		return dao.findByAdminUsernameAndAdminPassword(tempusername,temppassword);
	}

}
