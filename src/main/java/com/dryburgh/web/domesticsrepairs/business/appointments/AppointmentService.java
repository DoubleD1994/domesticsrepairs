package com.dryburgh.web.domesticsrepairs.business.appointments;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dryburgh.web.domesticsrepairs.business.engineers.EngineerService;
import com.dryburgh.web.domesticsrepairs.business.holidays.HolidayService;
import com.dryburgh.web.domesticsrepairs.data.entity.Appointment;
import com.dryburgh.web.domesticsrepairs.data.repository.AppointmentRepository;

@Service
public class AppointmentService {

	private final AppointmentRepository appointmentRepository;

	private final EngineerService engineerService;
	private final HolidayService holidayService;

	@Autowired
	public AppointmentService(EngineerService engineerService, HolidayService holidayService,
			AppointmentRepository appointmentRepository) {
		this.engineerService = engineerService;
		this.holidayService = holidayService;
		this.appointmentRepository = appointmentRepository;
	}

	public List<Appointment> getAllAppointments() {
		Iterable<Appointment> appointments = appointmentRepository.findAll();
		List<Appointment> appointmentsList = new ArrayList<>();
		appointments.forEach(appointment -> {
			appointmentsList.add(appointment);
		});
		return appointmentsList;
	}

	public Appointment getAppointmentByAppointmentId(long appointmentId) {
		return appointmentRepository.findById(appointmentId).get();
	}

	public Appointment createNewAppointment(Appointment appointment) {
		return appointmentRepository.save(appointment);
	}

	public void updateAppointment(Long appointmentId, Appointment appointment) {
		appointmentRepository.updateAppointment(appointmentId, appointment.getEngineerId(),
				appointment.getCustomerName(), appointment.getCustomerAddress(), appointment.getCustomerPhoneNumber(), 
				appointment.getCustomerEmail(), appointment.getTimeslotType(), appointment.getAppointmentDay());
	}

	public void deleteAppointment(long appointmentId) {
		appointmentRepository.deleteById(appointmentId);
	}

}
