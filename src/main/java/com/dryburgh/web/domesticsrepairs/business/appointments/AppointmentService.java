package com.dryburgh.web.domesticsrepairs.business.appointments;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dryburgh.web.domesticsrepairs.business.engineers.EngineerPool;
import com.dryburgh.web.domesticsrepairs.business.utils.IterableHandler;
import com.dryburgh.web.domesticsrepairs.data.entity.Appointment;
import com.dryburgh.web.domesticsrepairs.data.repository.AppointmentRepository;

@Service
public class AppointmentService {

	private final AppointmentRepository appointmentRepository;
	private final EngineerPool engineerPool;
	private final IterableHandler<Appointment> iterableHandler;

	@Autowired
	public AppointmentService(AppointmentRepository appointmentRepository, EngineerPool engineerPool,
			IterableHandler<Appointment> iterableHandler) {
		this.appointmentRepository = appointmentRepository;
		this.engineerPool = engineerPool;
		this.iterableHandler = iterableHandler;
	}

	public List<Appointment> getAllAppointments() {
		Iterable<Appointment> appointments = appointmentRepository.findAll();
		return iterableHandler.addObjectToList(appointments);
	}

	public Appointment getAppointmentByAppointmentId(long appointmentId) {
		return appointmentRepository.findById(appointmentId).get();
	}

	public List<Appointment> getHolidayByEngineerId(long engineerId) {
		Iterable<Appointment> appointments = appointmentRepository.getAppointmentsByEngineerId(engineerId);
		return iterableHandler.addObjectToList(appointments);
	}

	public List<Appointment> getAppointmentsByDates(LocalDate startDate, LocalDate endDate) {
		Iterable<Appointment> appointments = appointmentRepository.getAppointmentsByDates(startDate, endDate);
		return iterableHandler.addObjectToList(appointments);
	}

	public List<Appointment> getEngineerAppointmentsByDates(long engineerId, LocalDate startDate, LocalDate endDate) {
		Iterable<Appointment> appointments = appointmentRepository.getEngineerAppointmentsByDates(engineerId, startDate,
				endDate);
		return iterableHandler.addObjectToList(appointments);
	}

	public List<LocalDate> getAvailableAppointments(String timeslotType) {
		Long maxNumberOfAppointments = 1l; // engineerService.getEngineerCount();
		Iterable<LocalDate> unavailableDates = appointmentRepository.getUnavailableDates(
				LocalDate.now().plusDays(1), LocalDate.now().plusDays(14), timeslotType,
				maxNumberOfAppointments);
		return filterUnavailableDates(unavailableDates);
	}

	public Appointment createNewAppointment(Appointment appointment) {
		checkAppointmentIsInFuture(appointment);
		appointment.setEngineerId(engineerPool.getAvailableEngineerForAppointment(appointment,
				getEngineersWithMaxAppointments(appointment)));
		return appointmentRepository.save(appointment);
	}

	public void updateAppointment(Long appointmentId, Appointment appointment) {
		checkAppointmentIsInFuture(appointment);
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

	private List<Long> getEngineersWithMaxAppointments(Appointment appointment) {
		Iterable<Long> engineerIds = appointmentRepository
				.getEngineersWithMaxAppointments(appointment.getAppointmentDay(), appointment.getTimeslotType());
		List<Long> engineersList = new ArrayList<>();
		engineerIds.forEach(engineerId -> {
			engineersList.add(engineerId);
		});
		return engineersList;
	}

	private List<LocalDate> filterUnavailableDates(Iterable<LocalDate> unavailableDates) {
		List<LocalDate> availableDates = new ArrayList<>();
		datesForNextTwoWeeks(availableDates, unavailableDates);
		return availableDates;
	}

	private void datesForNextTwoWeeks(List<LocalDate> availableDates, Iterable<LocalDate> unavailableDates) {
		for (int i = 1; i < 14; i++) {
			if (!dateIsUnavailable(LocalDate.now().plusDays(i), unavailableDates)) {
				availableDates.add(LocalDate.now().plusDays(i));
			}
		}
	}

	private boolean dateIsUnavailable(LocalDate plusDays, Iterable<LocalDate> unavailableDates) {
		for (LocalDate date : unavailableDates) {
			if (plusDays.equals(date)) {
				return true;
			}
		}
		return false;
	}

	private void checkAppointmentIsInFuture(Appointment appointment) {
		if (appointment.getAppointmentDay().isBefore(LocalDate.now().plusDays(1))) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Date of appointment must be at least one day in the future");
		}
	}
}
