package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.Repository.SpecializationRepository;
import com.app.pojos.Specialization;

@Service
@Transactional
public class SpclztnServiceImpl implements ISpeclztnService {

	@Autowired
	private SpecializationRepository spclztnRepo;
	
	
	@Override
	public Specialization addSpclztnDetails (Specialization transientPOJO) {
		// TODO Auto-generated method stub
				
		
		return spclztnRepo.save(transientPOJO);
	}

}
