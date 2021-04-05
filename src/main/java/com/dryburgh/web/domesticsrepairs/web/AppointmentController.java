package com.dryburgh.web.domesticsrepairs.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
}
