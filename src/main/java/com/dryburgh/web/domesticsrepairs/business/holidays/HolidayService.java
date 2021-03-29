package com.dryburgh.web.domesticsrepairs.business.holidays;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dryburgh.web.domesticsrepairs.business.engineers.EngineerService;
import com.dryburgh.web.domesticsrepairs.data.entity.Holiday;
import com.dryburgh.web.domesticsrepairs.data.repository.HolidayRepository;

@Service
public class HolidayService {

	private final HolidayRepository holidayRepository;
	private final EngineerService engineerService;
	
	@Autowired
	public HolidayService(HolidayRepository holidayRepository, EngineerService engineerService) {
		this.holidayRepository = holidayRepository;
		this.engineerService = engineerService;
	}
	
	public List<Holiday> getAllHolidays() {
		Iterable<Holiday> holidays = holidayRepository.findAll();
		List<Holiday> holidayList = new ArrayList<>();
		holidays.forEach(holiday -> {holidayList.add(holiday);});
		return holidayList;
	}

	public Holiday createNewHoliday(Holiday newHoliday) {
		return holidayRepository.save(newHoliday);
	}

}
