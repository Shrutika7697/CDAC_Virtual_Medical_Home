package com.app.service;

import java.time.LocalDate;
import java.util.List;


import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.Repository.DoctorRepository;
import com.app.pojos.Admin;
import com.app.pojos.Doctor;
import com.app.pojos.Slot;



@Service
@Transactional
public class DoctorServiceImpl implements IDoctorService {

	@Autowired
	private DoctorRepository dao;
	
	@Override
	public Optional<Doctor> getDrDetails(String drName) {
		// TODO Auto-generated method stub
		return dao.findByDrName(drName);
	}
	
	
	
	@Autowired
	private EntityManager manager;
	
	
	@Override
	public Doctor authenticateUser(String drusername, String drpassword) {
		System.out.println("drUserName : " + drusername + " drPassword : " +drpassword);
		Doctor t = null;
		String jpql = "select t from Doctor t where t.tdrusername=:dunme and t.tdrpassword=:pass";
       // String jpql="select dr_uname,dr_pwd from doctor_list where doctor_list.dr_uname=:dunme and doctor_list.dr_pwd=:pass ";
		t = manager.createQuery(jpql, Doctor.class)
				.setParameter("dunme", drusername).setParameter("pass", drpassword)
				.getSingleResult();

		return t;

	}
	
	
	public Doctor fetchDoctorByEmailID(String email)
	{
		 return dao.findByEmailID(email);
	}
	
	@Override
	public Doctor addDrDetails(Doctor transientPOJO) {
		// TODO Auto-generated method stub
		return dao.save(transientPOJO);
	}



	@Override
	public List<Doctor> getAllDr() {
		System.out.println("dao imple class " + dao.getClass().getName());
		return dao.findAll();
	}


	@Override
	public Doctor fetchDoctorByDrusernameAndDrpassword(String tempusername, String temppassword) {
		// TODO Auto-generated method stub
		//return null;
		return dao.findByDrusernameAndDrpassword(tempusername,temppassword);
	}


	@Override
	public List<Doctor> getAllDoctor() {
		// TODO Auto-generated method stub
		 return dao.findAll();
	}


	@Override
	public List<Slot> getSlotByDate(LocalDate parse) {
		List<Slot> t = null;
		String jpql = "select t.slotname from Slot t where t.slotDate=:slotd";
       // String jpql="select dr_uname,dr_pwd from doctor_list where doctor_list.dr_uname=:dunme and doctor_list.dr_pwd=:pass ";
		t = manager.createQuery(jpql, Slot.class)
				.setParameter("slotd", parse).setMaxResults(5)
			.getResultList();

		return t;
	}






	

}
