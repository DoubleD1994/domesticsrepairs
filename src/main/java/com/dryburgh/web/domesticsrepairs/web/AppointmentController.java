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

import com.dryburgh.web.domesticsrepairs.business.appointments.AppointmentService;
import com.dryburgh.web.domesticsrepairs.data.entity.Appointment;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	private final AppointmentService appointmentService;
	
	@Autowired
	public AppointmentController(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Appointment> getAllAppointments(){
		return appointmentService.getAllAppointments();
	}
	
	@GetMapping("/{appointmentId}")
	@ResponseStatus(HttpStatus.OK)
	public Appointment getAppointmentByAppointmentId(@PathVariable(name="appointmentId") long appointmentId) {
		return appointmentService.getAppointmentByAppointmentId(appointmentId);
	}
	
	@GetMapping("/engineer/{engineerId}")
	@ResponseStatus(HttpStatus.OK)
	public List<Appointment> getAppointmentsByEngineerId(@PathVariable(name="engineerId") long engineerId){
		return appointmentService.getHolidayByEngineerId(engineerId);
	}
	
	@GetMapping("/specificDates")
	@ResponseStatus(HttpStatus.OK)
	public List<Appointment> getAppointmentsByDates(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
		return appointmentService.getAppointmentsByDates(LocalDate.parse(startDate), LocalDate.parse(endDate));
	}
	
	@GetMapping("/engineer/{engineerId}/specificDates")
	@ResponseStatus(HttpStatus.OK)
	public List<Appointment> getAppointmentsByDates(@PathVariable(name="engineerId") long engineerId, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
		return appointmentService.getEngineerAppointmentsByDates(engineerId, LocalDate.parse(startDate), LocalDate.parse(endDate));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Appointment createNewAppointment(@RequestBody Appointment appointment) {
		return appointmentService.createNewAppointment(appointment);
	}
	
	@PutMapping("/{appointmentId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public String updateAppointment(@PathVariable(name="appointmentId") long appointmentId, @RequestBody Appointment appointment) {
		appointmentService.updateAppointment(appointmentId, appointment);
		return "appointment updated";
	}
	
	@PutMapping("/{appointmentId}/completeWork")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public String completeWorkOnAppointment(@PathVariable(name="appointmentId") long appointmentId, @RequestBody Appointment appointment) {
		appointmentService.completeWorkOnAppointment(appointmentId, appointment.getCharge(), appointment.getWorkDone());
		return "appointment updated";
	}
	
	@DeleteMapping("/{appointmentId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public String deleteAppointment(@PathVariable(name="appointmentId") long appointmentId) {
		appointmentService.deleteAppointment(appointmentId);
		return "appointment deleted";
	}
}
