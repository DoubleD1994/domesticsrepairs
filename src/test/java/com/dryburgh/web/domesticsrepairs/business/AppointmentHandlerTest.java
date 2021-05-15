package com.dryburgh.web.domesticsrepairs.business;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.dryburgh.web.domesticsrepairs.business.appointments.AppointmentService;
import com.dryburgh.web.domesticsrepairs.business.engineers.EngineerService;

class AppointmentHandlerTest {

	@MockBean
	private AppointmentService appointmentHandler;
	private EngineerService engineerHandler;
	
	@BeforeEach
	void setUp() throws Exception {
	}

}
