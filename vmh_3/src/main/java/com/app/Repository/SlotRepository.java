package com.app.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Slot;

public interface SlotRepository extends  JpaRepository< Slot ,Integer> {

	
    
	Optional<Slot> findBySlotDate(LocalDate parse);

	//List<Slot> findByDoctorSlots(int drid);

	//Object findSlots(LocalDate slotDate);



}
