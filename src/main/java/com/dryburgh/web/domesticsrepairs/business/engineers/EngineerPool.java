package com.dryburgh.web.domesticsrepairs.business.engineers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dryburgh.web.domesticsrepairs.business.holidays.HolidayService;
import com.dryburgh.web.domesticsrepairs.data.entity.Appointment;
import com.dryburgh.web.domesticsrepairs.data.entity.Engineer;

@Service
public class EngineerPool {

	private final EngineerService engineerService;
	private final HolidayService holidayService;
	private List<Long> availableEngineers;
	
	@Autowired
	public EngineerPool(EngineerService engineerService, HolidayService holidayService) {
		this.engineerService = engineerService;
		this.holidayService = holidayService;
	}
	
	public Long getAvailableEngineerForAppointment(Appointment appointment, List<Long> engineersWithMaxAppointments) {
		availableEngineers = new ArrayList<>();
		removeUnavailableEngineers(appointment, engineersWithMaxAppointments);
		return availableEngineer();
	}
	
	private Long availableEngineer() {
		Random r = new Random();
		return availableEngineers.get(r.nextInt(availableEngineers.size()));
	}

	private void removeUnavailableEngineers(Appointment appointment, List<Long> engineersWithMaxAppointments) {
		List<Engineer> engineers = engineerService.getAllEngineers();
		List<Long> engineersOnHoliday = holidayService.getEningeersOnHoliday(appointment.getAppointmentDay(), appointment.getAppointmentDay());
		excludeEngineersOnHoliday(engineersOnHoliday, engineers, appointment);
		excludeEngineersWithMaxAppointments(engineersWithMaxAppointments, engineers);
	}

	private void excludeEngineersWithMaxAppointments(List<Long> engineersWithMaxAppointments, List<Engineer> engineers) {
		for(Engineer engineer : engineers) {
			if(filterThroughBusyEngineers(engineersWithMaxAppointments, engineer)) {
				availableEngineers.remove(engineer.getEngineerId());
			}
		}
	}

	private void excludeEngineersOnHoliday(List<Long> engineersOnHoliday, List<Engineer> engineers,
			Appointment appointment) {
		for(Engineer engineer: engineers) {
			if(!filterThroughBusyEngineers(engineersOnHoliday, engineer)) {
				availableEngineers.add(engineer.getEngineerId());
			}
		}
	}

	private boolean filterThroughBusyEngineers(List<Long> engineersThatAreBusy, Engineer engineer) {
		boolean engineerOnHoliday = false;
		for(Long engineerId : engineersThatAreBusy) {
			engineerOnHoliday = isEngineerAvailable(engineer, engineerId);
		}
		return engineerOnHoliday;
	}

	private boolean isEngineerAvailable(Engineer engineer, Long engineerId) {
		if(engineerId.equals(engineer.getEngineerId())) {
			return true;
		}
		return false;
	}
	
}
