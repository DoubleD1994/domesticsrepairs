package com.dryburgh.web.domesticsrepairs.business.holidays;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dryburgh.web.domesticsrepairs.business.utils.IterableHandler;
import com.dryburgh.web.domesticsrepairs.data.entity.Holiday;
import com.dryburgh.web.domesticsrepairs.data.repository.HolidayRepository;

@Service
public class HolidayService {

	private final HolidayRepository holidayRepository;
	private final IterableHandler<Holiday> iterableHandler;
	
	@Autowired
	public HolidayService(HolidayRepository holidayRepository, IterableHandler<Holiday> iterableHandler) {
		this.holidayRepository = holidayRepository;
		this.iterableHandler = iterableHandler;
	}
	
	public List<Holiday> getAllHolidays() {
		Iterable<Holiday> holidays = holidayRepository.findAll();
		return iterableHandler.addObjectToList(holidays);
	}
	
	public List<Holiday> getHolidayByEngineerId(long engineerId){
		Iterable<Holiday> holidays = holidayRepository.getHolidayByEngineerId(engineerId);
		return iterableHandler.addObjectToList(holidays);
	}
	
	public List<Holiday> getHolidaysByDates(LocalDate startDate, LocalDate endDate) {
		Iterable<Holiday> holidays = holidayRepository.getHolidayByDates(startDate, endDate);
		return iterableHandler.addObjectToList(holidays);
	}
	
	public List<Long> getEningeersOnHoliday(LocalDate startDate, LocalDate endDate) {
		Iterable<Long> engineerIds = holidayRepository.getEngineersOnHoliday(startDate, endDate);
		List<Long> engineersList = new ArrayList<>();
		engineerIds.forEach(engineerId -> {engineersList.add(engineerId);});
		return engineersList;
	}

	public Holiday createNewHoliday(Holiday newHoliday) {
		validateHolidayDates(newHoliday.getHolidayStartDate(), newHoliday.getHolidayEndDate());
		return holidayRepository.save(newHoliday);
	}

	public Holiday getHolidayByHolidayId(long holidayId) {
		return holidayRepository.findById(holidayId).get();
	}

	public void updateHoliday(long holidayId, Holiday updateHoliday) {
		validateHolidayDates(updateHoliday.getHolidayStartDate(), updateHoliday.getHolidayEndDate());
		holidayRepository.updateHoliday(holidayId, updateHoliday.getHolidayStartDate(), updateHoliday.getHolidayEndDate());
	}

	public void deleteHoliday(long holidayId) {
		holidayRepository.deleteById(holidayId);
	}
	
	private void validateHolidayDates(LocalDate holidayStartDate, LocalDate holidayEndDate) {
		checkHolidayStartDateIsInFuture(holidayStartDate);
		checkHolidayEndDateIsNotBeforeHolidayStartDate(holidayStartDate, holidayEndDate);
	}
	
	private void checkHolidayEndDateIsNotBeforeHolidayStartDate(LocalDate holidayStartDate, LocalDate holidayEndDate) {
		if(holidayEndDate.isBefore(holidayStartDate)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "holiday end date can't be before start date.");
		}
	}

	private void checkHolidayStartDateIsInFuture(LocalDate holidayStartDate) {
		if(holidayStartDate.isBefore(LocalDate.now().plusDays(1))) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "holiday start date must be at least one day in the future");
		}
	}
}
