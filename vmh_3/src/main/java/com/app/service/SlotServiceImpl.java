package com.app.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.Repository.SlotRepository;
import com.app.pojos.Doctor;
import com.app.pojos.Slot;
import com.app.pojos.Status;

@Service
@Transactional
public class SlotServiceImpl implements ISlotService {
	
	@Autowired
	private SlotRepository slotRepo;
	
	@Autowired
	private EntityManager manager;

	

	@Override
	public Optional<Slot> getAllSlots(String slotDate) {
		// TODO Auto-generated method stub
		return slotRepo.findBySlotDate(LocalDate.parse(slotDate));
		
	}
	
	

	@Override
	public List<Slot> getSlotByDate(LocalDate slotDate, Enum<Status> sp) {
		//System.out.println("drUserName : " + drusername + " drPassword : " +drpassword);
				List<Slot> t = null;
				String jpql = "select t from Slot t where t.slotDate=:slotd and t.slotstatus=:stts ";
		       // String jpql="select dr_uname,dr_pwd from doctor_list where doctor_list.dr_uname=:dunme and doctor_list.dr_pwd=:pass ";
				t = manager.createQuery(jpql, Slot.class)
						.setParameter("slotd", slotDate).setParameter("stts",sp)
					.getResultList();

				return t;
	}

	
}
