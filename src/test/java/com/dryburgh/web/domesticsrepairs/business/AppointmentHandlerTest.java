package com.dryburgh.web.domesticsrepairs.business;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.dryburgh.web.domesticsrepairs.business.appointments.AppointmentService;
import com.dryburgh.web.domesticsrepairs.business.appointments.TimeslotType;
import com.dryburgh.web.domesticsrepairs.business.engineers.EngineerService;

class AppointmentHandlerTest {

	@MockBean
	private AppointmentService appointmentHandler;
	private EngineerService engineerHandler;
	
	@BeforeEach
	void setUp() throws Exception {
	}

}
