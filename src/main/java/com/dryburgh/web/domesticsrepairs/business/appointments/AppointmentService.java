package com.dryburgh.web.domesticsrepairs.business.appointments;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dryburgh.web.domesticsrepairs.business.engineers.EngineerPool;
import com.dryburgh.web.domesticsrepairs.data.entity.Appointment;
import com.dryburgh.web.domesticsrepairs.data.repository.AppointmentRepository;

@Service
public class AppointmentService {

	private final AppointmentRepository appointmentRepository;
	private final EngineerPool engineerPool;
	

	@Autowired
	public AppointmentService(AppointmentRepository appointmentRepository, EngineerPool engineerPool) {
		this.appointmentRepository = appointmentRepository;
		this.engineerPool = engineerPool;
	}

	public List<Appointment> getAllAppointments() {
		Iterable<Appointment> appointments = appointmentRepository.findAll();
		return addAppointmentsToList(appointments);
	}
	
	public Appointment getAppointmentByAppointmentId(long appointmentId) {
		return appointmentRepository.findById(appointmentId).get();
	}
	
	public List<Appointment> getHolidayByEngineerId(long engineerId) {
		Iterable<Appointment> appointments = appointmentRepository.getAppointmentsByEngineerId(engineerId);
		return addAppointmentsToList(appointments);
	}
	
	public List<Appointment> getAppointmentsByDates(LocalDate startDate, LocalDate endDate) {
		Iterable<Appointment> appointments = appointmentRepository.getAppointmentsByDates(startDate, endDate);
		return addAppointmentsToList(appointments);
	}
	
	public List<Appointment> getEngineerAppointmentsByDates(long engineerId, LocalDate startDate, LocalDate endDate) {
		Iterable<Appointment> appointments = appointmentRepository.getEngineerAppointmentsByDates(engineerId, startDate, endDate);
		return addAppointmentsToList(appointments);
	}

	public Appointment createNewAppointment(Appointment appointment) {
		engineerPool.getAvailableEngineerForAppointment(appointment);
		return appointmentRepository.save(appointment);
	}

	public void updateAppointment(Long appointmentId, Appointment appointment) {
		appointmentRepository.updateAppointment(appointmentId, appointment.getEngineerId(),
				appointment.getCustomerName(), appointment.getCustomerAddress(), appointment.getCustomerPhoneNumber(), 
				appointment.getCustomerEmail(), appointment.getTimeslotType(), appointment.getAppointmentDay());
	}
	
	public void completeWorkOnAppointment(Long appointmentId, Double charge, String workDone) {
		appointmentRepository.completeWorkOnAppointment(appointmentId, charge, workDone);
	}

	public void deleteAppointment(long appointmentId) {
		appointmentRepository.deleteById(appointmentId);
	}
	
	private List<Appointment> addAppointmentsToList(Iterable<Appointment> appointments) {
		List<Appointment> appointmentsList = new ArrayList<>();
		appointments.forEach(appointment -> {
			appointmentsList.add(appointment);
		});
		return appointmentsList;
	}
}
