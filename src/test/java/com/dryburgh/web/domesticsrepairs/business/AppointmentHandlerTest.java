package com.dryburgh.web.domesticsrepairs.business;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.dryburgh.web.domesticsrepairs.business.appointments.AppointmentHandler;
import com.dryburgh.web.domesticsrepairs.business.appointments.TimeslotType;
import com.dryburgh.web.domesticsrepairs.business.engineers.EngineerHandler;

class AppointmentHandlerTest {

	@MockBean
	private AppointmentHandler appointmentHandler;
	private EngineerHandler engineerHandler;
	
	@BeforeEach
	void setUp() throws Exception {
	}

}
