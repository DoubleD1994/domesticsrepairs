package com.dryburgh.web.domesticsrepairs.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/{holidayId}")
	@ResponseStatus(HttpStatus.OK)
	public Holiday getHolidayByHolidayId(@PathVariable(name="holidayId") long holidayId) {
		return holidayService.getHolidayByHolidayId(holidayId);
	}
	
	@GetMapping("/engineer/{engineerId}")
	@ResponseStatus(HttpStatus.OK)
	public List<Holiday> getHolidaysByEngineerId(@PathVariable(name="engineerId") long engineerId){
		return holidayService.getHolidayByEngineerId(engineerId);
	}
	
	@GetMapping("/specificDates")
	@ResponseStatus(HttpStatus.OK)
	public List<Holiday> getHolidaysByDates(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
		return holidayService.getHolidaysByDates(LocalDate.parse(startDate), LocalDate.parse(endDate));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Holiday createNewHoliday(@RequestBody Holiday newHoliday) {
		return holidayService.createNewHoliday(newHoliday);
	}
	
	@PutMapping("/{holidayId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public String updateHoliday(@PathVariable(name="holidayId") long holidayId, @RequestBody Holiday updateHoliday) {
		holidayService.updateHoliday(holidayId, updateHoliday);
		return "Holiday Updated";
	}
	
	@DeleteMapping("/{holidayId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public String deleteHoliday(@PathVariable(name="holidayId") long holidayId) {
		holidayService.deleteHoliday(holidayId);
		return "Holiday Deleted";
	}
}
