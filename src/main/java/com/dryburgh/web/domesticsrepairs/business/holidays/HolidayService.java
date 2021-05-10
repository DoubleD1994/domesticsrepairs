package com.dryburgh.web.domesticsrepairs.business.holidays;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dryburgh.web.domesticsrepairs.data.entity.Holiday;
import com.dryburgh.web.domesticsrepairs.data.repository.HolidayRepository;

@Service
public class HolidayService {

	private final HolidayRepository holidayRepository;
	
	@Autowired
	public HolidayService(HolidayRepository holidayRepository) {
		this.holidayRepository = holidayRepository;
	}
	
	public List<Holiday> getAllHolidays() {
		Iterable<Holiday> holidays = holidayRepository.findAll();
		return addHolidaysToList(holidays);
	}
	
	public List<Holiday> getHolidayByEngineerId(long engineerId){
		Iterable<Holiday> holidays = holidayRepository.getHolidayByEngineerId(engineerId);
		return addHolidaysToList(holidays);
	}
	
	public List<Holiday> getHolidaysByDates(LocalDate startDate, LocalDate endDate) {
		Iterable<Holiday> holidays = holidayRepository.getHolidayByDates(startDate, endDate);
		return addHolidaysToList(holidays);
	}

	public Holiday createNewHoliday(Holiday newHoliday) {
		return holidayRepository.save(newHoliday);
	}

	public Holiday getHolidayByHolidayId(long holidayId) {
		return holidayRepository.findById(holidayId).get();
	}

	public void updateHoliday(long holidayId, Holiday updateHoliday) {
		holidayRepository.updateHoliday(holidayId, updateHoliday.getHolidayStartDate(), updateHoliday.getHolidayEndDate());
	}

	public void deleteHoliday(long holidayId) {
		holidayRepository.deleteById(holidayId);
	}
	
	private List<Holiday> addHolidaysToList(Iterable<Holiday> holidays) {
		List<Holiday> holidayList = new ArrayList<>();
		holidays.forEach(holiday -> {holidayList.add(holiday);});
		return holidayList;
	}
}
