package com.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.app.pojos.Slot;
import com.app.pojos.Status;

public interface ISlotService {

	//List<Slot> getAllSlots();

	Optional<Slot> getAllSlots(String slotDate);

	//List<Slot> getSlotByDate(LocalDate slotDate);

	List<Slot> getSlotByDate(LocalDate slotDate, Enum<Status> sp);

	

	

}
