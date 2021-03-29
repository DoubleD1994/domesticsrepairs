package com.dryburgh.web.domesticsrepairs.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dryburgh.web.domesticsrepairs.business.holidays.HolidayService;
import com.dryburgh.web.domesticsrepairs.data.entity.Holiday;

@RestController
@RequestMapping("/holidays")
public class HolidayController {

	private final HolidayService holidayService;
	
	@Autowired
	public HolidayController(HolidayService holidayService) {
		this.holidayService = holidayService;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Holiday> getHolidays() {
		return holidayService.getAllHolidays();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Holiday createNewHoliday(@RequestBody Holiday newHoliday) {
		return holidayService.createNewHoliday(newHoliday);
	}
	
}
