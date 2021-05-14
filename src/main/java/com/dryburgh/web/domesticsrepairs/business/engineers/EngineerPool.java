package com.dryburgh.web.domesticsrepairs.business.engineers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dryburgh.web.domesticsrepairs.business.holidays.HolidayService;
import com.dryburgh.web.domesticsrepairs.data.entity.Appointment;
import com.dryburgh.web.domesticsrepairs.data.entity.Engineer;

@Service
public class EngineerPool {

	private final EngineerService engineerService;
	private final HolidayService holidayService;
	private List<Long> availableEngineers = new ArrayList<>();
	
	@Autowired
	public EngineerPool(EngineerService engineerService, HolidayService holidayService) {
		this.engineerService = engineerService;
		this.holidayService = holidayService;
	}
	
	public Long getAvailableEngineerForAppointment(Appointment appointment) {
		List<Engineer> engineers = engineerService.getAllEngineers();
		List<Long> engineersOnHoliday = holidayService.getEningeersOnHoliday(appointment.getAppointmentDay(), appointment.getAppointmentDay());
		excludeEngineersOnHoliday(engineersOnHoliday, engineers, appointment);
		return null;
	}
	

	private void excludeEngineersOnHoliday(List<Long> engineersOnHoliday, List<Engineer> engineers,
			Appointment appointment) {
		for(Engineer engineer: engineers) {
			if(!filterThroughEngineersOnHoliday(engineersOnHoliday, engineer)) {
				availableEngineers.add(engineer.getEngineerId());
			}
		}
	}

	private boolean filterThroughEngineersOnHoliday(List<Long> engineersOnHoliday, Engineer engineer) {
		boolean engineerOnHoliday = false;
		for(Long engineerId : engineersOnHoliday) {
			engineerOnHoliday = isEngineerOnHoliday(engineer, engineerId);
		}
		return engineerOnHoliday;
	}

	private boolean isEngineerOnHoliday(Engineer engineer, Long engineerId) {
		if(engineerId.equals(engineer.getEngineerId())) {
			return true;
		}
		return false;
	}
	
}
