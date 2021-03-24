package com.dryburgh.web.domesticsrepairs.business.appointments;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dryburgh.web.domesticsrepairs.business.engineers.EngineerHandler;
import com.dryburgh.web.domesticsrepairs.data.entity.Appointment;

@Service
public class AppointmentHandler {

	private final EngineerHandler engineerHandler;

	private List<Appointment> appointments = new ArrayList<>();

	@Autowired
	public AppointmentHandler(EngineerHandler engineerHandler) {
		this.engineerHandler = engineerHandler;
	}

	public long bookAppointment(String customerName, String customerAddress, String customerPhoneNumber,
			String customerEmail, TimeslotType timeslot, LocalDate appointmentDate) {
		Appointment newAppointment = new Appointment(
				engineerHandler.getAvailableEngineerForAppointment().getEngineerId(), customerName, customerAddress,
				customerPhoneNumber, customerEmail, timeslot, appointmentDate);
		appointments.add(newAppointment);
		return newAppointment.getAppointmentId();
	}

	public long getEngineerForAppointment(long appointmentID) {
		return appointments.get(0).getAppointmentId();
	}

	public EngineerHandler getEngineerHandler() {
		return engineerHandler;
	}
}
